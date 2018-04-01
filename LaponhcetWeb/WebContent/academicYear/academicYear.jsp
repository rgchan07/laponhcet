<%@page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	AcademicYearDTO academicYear = (AcademicYearDTO)session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> academicProgramGroupList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
%>
<div class="col-lg-12">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<input type="hidden" name="txtAcademicProgramCodes" id="txtAcademicProgramCodes" />
			<div class="row">
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Name", "YYYY-YYYY", "Name", academicYear.getName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Start Date", "Start Date", "DateStart", DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "End Date", "End Date", "DateEnd", DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true") %>
			</div>
			<div class="row">
			<%
			for(DTOBase obj: academicProgramGroupList) {
				AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)obj;
				List<DTOBase> academicProgramListByAcademicProgramGroup = AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode());
				if(academicProgramListByAcademicProgramGroup.size() >= 1) {
			%>
               	<div class="col-lg-4">
               		<br><br>
					<div class="panel panel-primary">
			        	<div class="panel-heading">
			            	<input type="checkbox" id="chkAcademicProgram<%=academicProgramGroup.getCode()%>" onchange="toggleCheckListByPrefixId(this)"> <%=academicProgramGroup.getName()%> 
			            </div>
			            <div class="panel-body">
			            	<%=WebUtil.getTable("table", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, "AcademicProgram" + academicProgramGroup.getCode(), academicProgramListByAcademicProgramGroup, academicYear.getAcademicProgramCodes().split("~"), StringUtil.getStrArr(academicProgramListByAcademicProgramGroup), "onchange=\"toggleCheckParent('chkAcademicProgram" + academicProgramGroup.getCode() + "', " + academicProgramListByAcademicProgramGroup.size() + ")\""), 2) %>
			            </div>
			        </div>
			   	</div>
			<%	
				}
			}
			%>
			</div>
			<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-b-lg", "btn btn-primary", "align='center'") %>
		</div>
	</div>
</div>
				
				
			
