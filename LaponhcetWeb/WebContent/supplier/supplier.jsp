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
	SupplierDTO supplier = (SupplierDTO)session.getAttribute(SupplierDTO.SESSION_SUPPLIER);
%>

<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Last Name", "Last Name", "LastName", supplier.getLastName(), 45, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "First Name", "First Name", "FirstName", supplier.getFirstName(), 45, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Middle Name", "Middle Name", "MiddleName", supplier.getMiddleName(), 45, WebControlBase.DATA_TYPE_STRING, "") %>

<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Date of Birth", "Birthday", "BirthDate", DateTimeUtil.getDateTimeToStr(supplier.getBirthDate(), "MM/dd/yyyy"), 10, WebControlBase.DATA_TYPE_DATE, "")  %>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>
