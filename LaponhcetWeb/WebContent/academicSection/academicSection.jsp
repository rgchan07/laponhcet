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
	AcademicSectionDTO section = (AcademicSectionDTO)session.getAttribute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
%>

<div class="ibox float-e-margins">
    <div class="ibox-content">
     	<div class="row">
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-8", true, "Academic Program", "Academic Program", academicProgramList, section.getAcademicProgram(), "", "", "") %>
		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Year Level", "Year Level", "YearLevel", StringUtil.getFormattedNum(section.getYearLevel(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_INTEGER, "") %>
		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Name", "Name", "Name", section.getName(), 90, WebControlBase.DATA_TYPE_STRING, "") %>
		<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %>
		</div>
	</div>
</div>    