<%@page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="com.laponhcet.action.register.*" %>
<%@ page import="java.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	UnitDTO unit = (UnitDTO)session.getAttribute(UnitDTO.SESSION_UNIT);
%>

<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Name", "Name", "Name", unit.getName(), 45, WebControlBase.DATA_TYPE_STRING, "") %>

<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>
