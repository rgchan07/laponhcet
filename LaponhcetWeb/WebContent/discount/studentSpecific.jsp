
<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.dao.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination studentPagination = (Pagination)session.getAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
	StudentDTO studentSelected = (StudentDTO) session.getAttribute(StudentDTO.SESSION_STUDENT);
	DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO)session.getAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT);
	List<DTOBase> programList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> academicYearList = (ArrayList<DTOBase>)session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
	List<DTOBase> semesterList = (ArrayList<DTOBase>)session.getAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
	List<DTOBase> discountTypeList = (ArrayList<DTOBase>)session.getAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_LIST);
	DiscountTypeDTO discountType = (DiscountTypeDTO)session.getAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
	
%>
<style>
#modal-form {
	top: 10%;
	left: 25%;
	outline: none;
}
</style>
<!----- Search Student for Discount ------>

<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
			<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-6 m-b", false, "SearchValue", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value); showTable(); getInputSearchValue(this.value);\"") %>

			
		
			</div>
		</div>
	</div>	
			
<!----------- End Search Student ---------------->
<!----------- Student Specific Add ---------------->
<%	
	if(!discount.getStudent().getCode().isEmpty()){
%>
			<div class="col-lg-6"><b>STUDENT :</b><%=discount.getStudent().getName(false, false, true)%>(<%=discount.getStudent().getCode()%>)</div>
			<div class="col-lg-6"><b>PROGRAM :</b><%=discount.getStudent().getAcademicProgram().getCode()%></div><br><br>

<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
	<%	
		if(discount.getStudent().getAcademicProgram().getAcademicProgramGroup().getCode().equalsIgnoreCase(AcademicProgramGroupDTO.ACADEMIC_PROGRAM_GROUP_CODE_BASICEDUCATION)){
	%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Academic Year", "AcademicYear", academicYearList, discount.getAcademicYear(), "Select Academic Year", "", "") %>
	<%	
	}else{
	%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "A.Y./Sem.", "Semester", semesterList, discount.getSemester().getAcademicYear(), "Select Semester", "", "") %>
	<%		
		}
	%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Discount Type", "DiscountType", discountTypeList, discount.getDiscountType(), "Select Discount Type", "", "onchange=openLink('US0179')") %>

			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", false, "Remarks", "Remarks", "Remarks", discount.getRemark(), 180, TextBoxWebControl.DATA_TYPE_STRING, "")%>
		
			<div class="col-sm-12 m-t-lg m-b-lg">
				<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-xl", "btn btn-primary", "align='center'") %>	
		</div>	
	<%
}
%>	  
	</div>
	</div>
</div>

<div class="modal fade" id="modal-form" tabindex="-1" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-body">
						 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<div class="row" style="padding-top: 20px;">
								<h3 class="m-l-xl" id="txtSearchResult"></h3> 
								
								<%=new PaginationWebControl().getPaginationWebControlList(sessionInfo, "col-sm-12", studentPagination, studentSelected.getId())%>
							</div>
							</div>
						</div>
					</div>
				</div>
				
	<%	
		if(discount.getDiscountType().isPercentage()){
	%>			
			<div class="col-lg-4">
				<div class="panel panel-primary">
					<div class="panel-heading">Discount Details</div>
					<div class="panel-body">
							<div class="col-xs-6"><h5 class="stats-label">FEE TYPE</h5></div>
							<div class="col-xs-6"><h5 class="stats-label">DISCOUNT %</h5></div>
							<div class="ibox-content">
						<div class="row">
								
						 <%
                        for(DTOBase discountTypePercentageObj: discount.getDiscountType().getDiscountTypePercentageList()) {
                        	DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountTypePercentageObj;
                        %>
                        	<div class="col-xs-6"><h5><%=discountTypePercentage.getFee().getName() %></h5></div>
							<div class="col-xs-6"><h5><%=StringUtil.getFormattedNum(discountTypePercentage.getPercent(), StringUtil.NUMERIC_STANDARD_FORMAT)%> %</h5></div>
								<%	
                        }
                        %>
						</div>
						</div>
					</div>
				</div>
			</div>
			<%
}
%>
	

<script>
	function showTable() {
		if($("#txtSearchValue").val()){
			$("#modal-form").modal();
			}
		}
	function discountStudentAction(code){	
		document.getElementById("txtSelectedRecord").value = code;
		$("#modal-form").hide()
		openLink("US0185");
	}
</script>

<script>
function getInputSearchValue(searchValue) {
	$("#txtSearchResult").html("<strong>Search Result for: " + searchValue + "</strong>");
	}
</script>