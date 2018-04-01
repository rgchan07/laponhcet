<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@	page import="com.laponhcet.dao.*"%>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) session.getAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC);
	List<DTOBase> feeStudentSpecificList = (List<DTOBase>) session.getAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
	Pagination studentPagination = (Pagination) session.getAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
	List<DTOBase> academicYearList = (List<DTOBase>) session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
	List<DTOBase> semesterList = (List<DTOBase>) session.getAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
	List<DTOBase> feeList = (List<DTOBase>) session.getAttribute(FeeDTO.SESSION_FEE_LIST);
%>
<%
if(sessionInfo.isCurrentLinkUpdate() || sessionInfo.isCurrentLinkUpdateSubmit()) {
%>
	<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-6", false, "Search Student Name/Code", "", "SearchValue", FeeStudentSpecificUtil.getStudentName(feeStudentSpecific.getStudent()), 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value); showTable();\"") %>
	<div class="col-sm-6">
	<%
		if(!feeStudentSpecific.getStudent().getCode().isEmpty()) {
			if(feeStudentSpecific.getStudent().getAcademicProgram().getAcademicProgramGroup().getCode().equalsIgnoreCase(AcademicProgramGroupDTO.ACADEMIC_PROGRAM_GROUP_CODE_BASICEDUCATION)) {
	%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-12", true, "Academic Year", "AcademicYear", academicYearList, feeStudentSpecific.getAcademicYear(), "", "", "onchange=\"openLink('US0149')\";") %>
	<%
			}
			else {
	%>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-12", true, "Semester", "Semester", semesterList, feeStudentSpecific.getSemester(), "", "", "onchange=\"openLink('US0150')\";") %>
	<%	
			}
		}
	%>
	</div>
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Fee", "Fee", feeList, feeStudentSpecific.getFee(), "", "", "") %>
	<div class="col-sm-6">
		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8", true, "Amount", "Amount", "Amount", StringUtil.getFormattedNum(feeStudentSpecific.getAmount(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 10, TextBoxWebControl.DATA_TYPE_DOUBLE, "") %>
		<%=new LinkButtonWebControl(sessionInfo, "btn btn-primary", "fa fa-plus", true, "onclick=\"openLink('US0151')\"; style='margin-top: 41px;'").getLinkButtonWebControl()%>
	</div>	
<%
	if(feeStudentSpecificList.size()>0) {
%>
	<div id="divFeeStudentList" class="col-sm-12 m-t-lg">	
        <div class='col-sm-12 table-responsive'>
			 <table class="table table-striped table-hover" id="table1">
				<thead>
	               	<tr>
	                   	<th>Fee</th>
	                   	<th>Amount</th>
	                   	<th></th>
					</tr>
				</thead>
				<tbody>
				<%
				for(DTOBase studentSpecificObj: feeStudentSpecificList) {
					FeeStudentSpecificDTO feeStudent = (FeeStudentSpecificDTO) studentSpecificObj;
					FeeDTO fee = (FeeDTO) DTOUtil.getObjByCode(feeList, feeStudent.getFee().getCode());
				%>
				<tr>
					<td><%=fee.getName()%></td>
					<td style="text-align: right;"><%=feeStudent.getAmount()%></td>
					<td onclick="recordAction(<%=feeStudent.getId()%>, 'US0152')"><button class="btn btn-rounded btn-outline btn-danger fa fa-times pull-right" style=""></button></td>
				</tr>
				<%	
				}
				%>	
				</tbody>
			</table>
		</div>	
	</div>	
<%
	}
%>		
		<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>
<%	
}
%>
<div class="modal inmodal" id="tableModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none; left: auto; right: 10px;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header" style="text-align: left;">
            	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            	<h3>Search Result for: <i id="searchLabel"></i></h3>
            </div>
			<div class="">
			<%=new PaginationWebControl().getPaginationWebControl(sessionInfo, "", studentPagination, feeStudentSpecific.getStudent().getId()) %>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

<script>
	function showTable() {
		document.getElementById("searchLabel").innerHTML = document.getElementById("txtSearchValue").value;
		if(document.getElementById("txtSearchValue").value!="") {
			$("#tableModal").modal();
		}
	}
	
	disable();
	function disable(){
		if(<%=sessionInfo.isCurrentLinkUpdateSubmit() || sessionInfo.isCurrentLinkUpdateConfirm()%>) {
			document.getElementById("txtSearchValue").disabled = true;
			$("#table1 :button").attr("disabled", "disabled");
		}
	}
</script>
