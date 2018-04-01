<%@page import="com.mytechnopal.base.WebControlBase"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="com.laponhcet.action.register.*" %>
<%@ page import="java.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	CourseDTO course = (CourseDTO)session.getAttribute(CourseDTO.SESSION_COURSE);
	List<DTOBase> courseGroupList = (ArrayList<DTOBase>)session.getAttribute(CourseGroupDTO.SESSION_COURSE_GROUP_LIST);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> academicProgramGroupList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
%>
<div class="col-lg-12">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<input type="hidden" name="txtAcademicProgramCodes" id="txtAcademicProgramCodes" />
			<div class="row">
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Code", "Code", "Code", course.getCode(), 20, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8",  true, "Description", "Description", "Description", course.getDescription(), 180, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true,"Credit Unit", "Credit Unit", "CreditUnit", StringUtil.getFormattedNum(course.getCreditUnit(), StringUtil.NUMERIC_STANDARD_FORMAT), 5, TextBoxWebControl.DATA_TYPE_DOUBLE, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false,"Pay Unit", "Pay Unit", "PayUnit", StringUtil.getFormattedNum(course.getPayUnit(), StringUtil.NUMERIC_STANDARD_FORMAT), 5, TextBoxWebControl.DATA_TYPE_DOUBLE, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false,"Lec. Hrs", "Lec. Hrs", "LectureHour", StringUtil.getFormattedNum(course.getLectureHour(), StringUtil.NUMERIC_STANDARD_FORMAT), 5, TextBoxWebControl.DATA_TYPE_DOUBLE, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Lab. Hrs", "Lab. Hrs", "LaboratoryHour", StringUtil.getFormattedNum(course.getLaboratoryHour(), StringUtil.NUMERIC_STANDARD_FORMAT), 5, TextBoxWebControl.DATA_TYPE_DOUBLE, "") %>
				<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Course Group", "CourseGroup", courseGroupList, course.getCourseGroup(), "Select Group", "", "") %>
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
				            	<%=WebUtil.getTable("table", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, "AcademicProgram" + academicProgramGroup.getCode(), academicProgramListByAcademicProgramGroup, course.getAcademicProgramCodes().split("~"), StringUtil.getStrArr(academicProgramListByAcademicProgramGroup), "onchange=\"toggleCheckParent('chkAcademicProgram" + academicProgramGroup.getCode() + "', " + academicProgramListByAcademicProgramGroup.size() + ")\""), 2) %>
				            </div>
				        </div>
				   	</div>
				<%	
					}
				}
				%>
				</div>
				<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %>   
			</div>
		</div>
	</div>
</div>