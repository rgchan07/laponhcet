<%@page import="com.mytechnopal.webcontrol.ComboBoxWebControl"%>
<%@ page import="com.mytechnopal.*"%>
<%@ page import="com.mytechnopal.dto.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@page import="com.mytechnopal.webcontrol.*"%>
<%@page import="com.mytechnopal.*"%>
<%@ page import="com.laponhcet.dto.*"%>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
 	RegisterDTO register = (RegisterDTO) session.getAttribute(RegisterDTO.SESSION_REGISTER);
%>
<div class="row">
	<div class="col-lg-12 text-center">
		<div class="navy-line"></div>
		<h1>
			Membership <span class="navy"> Registration</span>
		</h1>
		<p>Approved registered member can avail seminars and other
			exclusive events</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="wrapper wrapper-content animated fadeInUp">
			<div class="form-group">
				<label class="col-sm-1 control-label">Name<font color='red'>*</font></label>
				<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-1", false, "", "PrefixName", UserDTO.PREFIX_NAME_LIST, register.getPrefixName(), UserDTO.PREFIX_NAME_LIST, "", "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", true, "", "Last Name", "LastName", register.getLastName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", true, "", "First Name", "FirstName", register.getFirstName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", true, "", "Middle Name", "MiddleName", register.getMiddleName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-1", false, "", "SuffixName", UserDTO.SUFFIX_NAME_LIST, register.getSuffixName(), UserDTO.SUFFIX_NAME_LIST, "", "", "") %>
				<%=new RadioButtonWebControl().getRadioButtonWebControl(null, "col-sm-2", true, "Gender", "Gender", new String[] {"MALE", "FEMALE"}, "MALE", new String[] {"MALE", "FEMALE"}, "", false)%>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-2", true, "Birthday", "Birthday", "BirthDate", DateTimeUtil.getDateTimeToStr(register.getBirthDate(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", true, "Cell Number", "Cell Number", "CpNumber", register.getCpNumber(), 11, TextBoxWebControl.DATA_TYPE_INTEGER, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-5", true, "Email Address", "Email Address", "EmailAddress", register.getEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-9", true, "School/Company/Institution Connected With", "School/Company/Institution Connected With", "InstitutionConnectedWith", register.getInstitutionConnectedWith(), 120, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", true, "Occupation/Position", "Occupation/Position", "Occupation", register.getOccupation(), 45, TextBoxWebControl.DATA_TYPE_STRING, "", "") %>
				<div class="col-sm-12" align="right">
					<br>
					<button id="btnRegisterSubmit" type="button"
						class="btn btn-primary" onclick="registerSubmit()">Submit</button>
					<button id="btnRegisterBack" type="button" class="btn btn-primary"
						style="display: none" onclick="registerBack()">Back</button>
					<button id="btnRegisterConfirm" type="button"
						class="btn btn-primary" style="display: none"
						onclick="registerConfirm()">Confirm</button>
				</div>
			</div>
		</div>
	</div>

	<script>
 function registerSubmit() {
 	$.ajax({
     	url: 'AjaxController?txtSelectedLink=G00003',
     	data: {
     		cboPrefixName: $('#cboPrefixName').val(),
     		txtLastName: $('#txtLastName').val(),
     		txtFirstName: $('#txtFirstName').val(),
     		txtMiddleName: $('#txtMiddleName').val(),
     		cboSuffixName: $('#cboSuffixName').val(),
     		rbGender: $('input[name=rbGender]:checked').val(),
     		txtBirthDate: $('#txtBirthDate').val(),
     		txtCpNumber: $('#txtCpNumber').val(),
     		txtEmailAddress: $('#txtEmailAddress').val(),
     		txtInstitutionConnectedWith: $('#txtInstitutionConnectedWith').val(),
 			txtOccupation: $('#txtOccupation').val()
     	},
     	method: 'POST',
     	dataType: 'JSON',
     	success: function(response) {
     		if(response.actionResponseType=="" || "response.actionResponseType==<%=ActionResponse.TYPE_INFO%>") {
     			$("#cboPrefixName").prop('disabled', true);
     			$("#txtLastName").prop('disabled', true);
     			$("#txtFirstName").prop('disabled', true);
     			$("#txtMiddleName").prop('disabled', true);
     			$("#cboSuffixName").prop('disabled', true);
     			$("#cboGender").prop('disabled', true);
     			$("#txtBirthDate").prop('disabled', true);
     			$("#txtCpNumber").prop('disabled', true);
     			$("#txtEmailAddress").prop('disabled', true);
     			$("#txtInstitutionConnectedWith").prop('disabled', true);
     			$("#txtOccupation").prop('disabled', true);
     			$("#btnRegisterSubmit").hide();
     			$("#btnRegisterBack").show();
     			$("#btnRegisterConfirm").show();
     		}
     		if(response.actionResponseMessageSWALMessage!="") {
     			swal(response.actionResponseMessageSWALTitle, response.actionResponseMessageSWALMessage, response.actionResponseMessageSWALType);
     		}
     	}
 	});	
 }

 function registerBack() {
 	$("#cboPrefixName").prop('disabled', false);
 	$("#txtLastName").prop('disabled', false);
 	$("#txtFirstName").prop('disabled', false);
 	$("#txtMiddleName").prop('disabled', false);
 	$("#cboSuffixName").prop('disabled', false);
 	$("#cboGender").prop('disabled', false);
 	$("#txtBirthDate").prop('disabled', false);
 	$("#txtCpNumber").prop('disabled', false);
 	$("#txtEmailAddress").prop('disabled', false);
 	$("#txtInstitutionConnectedWith").prop('disabled', false);
 	$("#txtOccupation").prop('disabled', false);
 	$("#btnRegisterSubmit").show();
 	$("#btnRegisterBack").hide();
 	$("#btnRegisterConfirm").hide();
 }

 function registerConfirm() {
 	$.ajax({
     	url: 'AjaxController?txtSelectedLink=G00004',
     	method: 'POST',
     	dataType: 'JSON',
     	success: function(response) {
     		registerBack();
     		if(response.actionResponseType == "<%=ActionResponse.TYPE_SUCCESS%>") {
     			swal(response.actionResponseMessageSWALTitle, response.actionResponseMessageSWALMessage, response.actionResponseMessageSWALType);
     			$('#cboPrefixName').val("");
         		$('#txtLastName').val("");
         		$('#txtFirstName').val("");
         		$('#txtMiddleName').val("");
         		$('#cboSuffixName').val("");
         		$('#rbGender').val("Male");
         		$('#txtBirthDate').val("");
         		$('#txtCpNumber').val("");
         		$('#txtEmailAddress').val("");
         		$('#txtInstitutionConnectedWith').val("");
     			$('#txtOccupation').val("");
     		}
     	}
 	});	
 }
</script>