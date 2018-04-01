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
	CourseGroupDTO courseGroup = (CourseGroupDTO)session.getAttribute(CourseGroupDTO.SESSION_COURSE_GROUP);
%>
<div class="col-lg-12">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
         	<div class="row">
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Name", "Name", "Name", courseGroup.getName(), 10, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8", true, "Description", "Description", "Description", courseGroup.getDescription(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary m-t-lg", "align='center'") %>
			</div>
        </div>
    </div>
</div>    