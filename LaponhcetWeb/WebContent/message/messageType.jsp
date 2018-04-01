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
	MessageTypeDTO messageType = (MessageTypeDTO)session.getAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE);
%>
<div class="col-lg-12">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
         	<div class="row">
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8", true, "Description", "Description", "Description", messageType.getDescription(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-4", "btn btn-primary m-t-xl", "align='left'") %>
			</div>
        </div>
    </div>
</div>    