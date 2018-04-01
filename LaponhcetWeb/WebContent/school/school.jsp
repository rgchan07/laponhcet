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
	SchoolDTO school = (SchoolDTO)session.getAttribute(SchoolDTO.SESSION_SCHOOL);
	List<DTOBase> cityList = (ArrayList<DTOBase>)session.getAttribute(CityDTO.SESSION_CITY_LIST);
%>

<div class="col-lg-12">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
         	<div class="row">
	        	<div class="col-sm-12">
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Name", "Name", "Name", school.getName(), 120, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Address1", "Address1", "Address1", school.getAddress1(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Address2", "Address2", "Address2", school.getAddress2(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "City", "City",  cityList, school.getCity(), "", "", "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Registrar OIC", "Registrar OIC", "RegistrarOIC", school.getRegistrarOIC(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8", false, "Website", "Website", "Website", school.getWebsite(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4 m-b", false, "Contact Number", "Contact Number", "ContactNumber", school.getContactNumber(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %>
				</div>
			</div>
        </div>
    </div>
</div>    