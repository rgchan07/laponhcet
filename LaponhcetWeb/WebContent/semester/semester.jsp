<%@ page import="com.mytechnopal.base.*"%>
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
	SemesterDTO semester = (SemesterDTO) session.getAttribute(SemesterDTO.SESSION_SEMESTER);
	List<DTOBase> academicYearList = (ArrayList)session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
%>

<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-5", true, "Academic Year", "AcademicYear", academicYearList, semester.getAcademicYear(), "", "", "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Name", "Name", StringUtil.getStrArrFromStr(SemesterDTO.SEMESTER_NAME_LIST), StringUtil.getNumToStr(semester.getName(), false), SemesterDTO.SEMESTER_DESCRIPTION_LIST, "", "", "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Date Start", "Date Start", "DateStart", DateTimeUtil.getDateTimeToStr(semester.getDateStart(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Date End", "Date End", "DateEnd", DateTimeUtil.getDateTimeToStr(semester.getDateEnd(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true") %>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-lg", "btn btn-primary", "align='center'") %>