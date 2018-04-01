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
	VenueDTO venue = (VenueDTO) session.getAttribute(VenueDTO.SESSION_VENUE);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> academicProgramGroupList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Name", "Name", "Name", venue.getName(), 20, WebControlBase.DATA_TYPE_STRING, "")%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Location", "Location", "Location", venue.getLocation(), 90, WebControlBase.DATA_TYPE_STRING, "")%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Concurrent Session", "Concurrent Session", "ConcurrentSession", StringUtil.getFormattedNum(venue.getConcurrentSession(), ""), 11, WebControlBase.DATA_TYPE_INTEGER, "")%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Max-Pax", "Max-Pax", "MaxPax", StringUtil.getFormattedNum(venue.getMaxPax(), ""), 11, WebControlBase.DATA_TYPE_INTEGER, "")%>
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
            	<%=WebUtil.getTable(new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, "AcademicProgram" + academicProgramGroup.getCode(), academicProgramListByAcademicProgramGroup, venue.getAcademicProgramCodes().split("~"), StringUtil.getStrArr(academicProgramListByAcademicProgramGroup), "onchange=\"toggleCheckParent('chkAcademicProgram" + academicProgramGroup.getCode() + "', " + academicProgramListByAcademicProgramGroup.size() + ")\""), 2) %>
            </div>
        </div>
   	</div>
<%	
	}
}
%>
</div>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %>   

