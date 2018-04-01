<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	AcademicProgramDTO academicProgram = (AcademicProgramDTO) session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM);
	List<DTOBase> userTeacherList = (ArrayList) session.getAttribute(UserDTO.SESSION_USER_LIST);
	List<DTOBase> academicProgramGroupList = (ArrayList) session.getAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
	List<DTOBase> academicProgramSubgroupList = (ArrayList) session.getAttribute(AcademicProgramSubgroupDTO.SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST);
%>

<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Group", "AcademicProgramGroup",  academicProgramGroupList, academicProgram.getAcademicProgramGroup(), "", "", "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Subgroup", "AcademicProgramSubgroup", academicProgramSubgroupList, academicProgram.getAcademicProgramSubgroup(), "", "", "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Code", "Code", "Code", academicProgram.getCode(), 20, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5", true, "Name", "Name", "Name", academicProgram.getName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-7", false, "Major", "Major", "Major", academicProgram.getMajor(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Description", "Description", "Description", academicProgram.getDescription(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Grad. Year Level", "Grad. Year Level", "GraduationYearLevel", String.valueOf(academicProgram.getGraduationYearLevel()), 1, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onClick='this.select()'") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", false, "Head", "HeadUser", userTeacherList, academicProgram.getHeadUser(), "", "", "") %>
<%=new RadioButtonWebControl().getRadioButtonWebControl(sessionInfo, "col-sm-3", true, "Academic Term", "AcademicTerm", new String[] {AcademicProgramDTO.ACADEMIC_TERM_ANNUAL, AcademicProgramDTO.ACADEMIC_TERM_SEMESTRAL}, academicProgram.getAcademicTerm(), new String[] {AcademicProgramDTO.ACADEMIC_TERM_ANNUAL, AcademicProgramDTO.ACADEMIC_TERM_SEMESTRAL}, "", false) %>
<%=new FileInputWebControl().getFileInputWebControl(sessionInfo, "col-sm-4", false, "Logo", "Logo", true, academicProgram.getLogo(), 1024000, FileUtil.IMG_FILENAME_EXT_LIST , 200, 200, null, "") %>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary", "align='center'") %>  