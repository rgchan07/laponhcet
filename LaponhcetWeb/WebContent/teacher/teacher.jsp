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
	TeacherDTO teacher = (TeacherDTO)session.getAttribute(TeacherDTO.SESSION_TEACHER);
	List<DTOBase> cityList = (ArrayList)session.getAttribute(CityDTO.SESSION_CITY_LIST);
	List<DTOBase> religionList = (ArrayList)session.getAttribute(ReligionDTO.SESSION_RELIGION_LIST);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
%>
<div class="col-sm-12 m-b-lg">
	<div class="col-sm-3 no-padding">
			<%=new FileInputWebControl().getFileInputWebControl(sessionInfo, "col-sm-12", false, "Profile Picture", "ProfilePict", false, teacher.getProfilePict(), 1024000, FileUtil.IMG_FILENAME_EXT_LIST, 240, 280, null, "") %>
		</div>
			<div class="col-sm-9">
				<div class="col-sm-3 no-padding">
   					<h5 class="m-t-lg"><strong>
   					<%
   					if(StringUtil.isEmpty(teacher.getCode())) {
   					%>
    					*ID is AUTO Generated
   					<%
   						}
   					else {
   					%>
   						ID: <b><%=teacher.getCode()%></b>
   					<%
   						}
   					%>
    			</strong></h5>
    			</div> 
					<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "RFID", "Scan RFID here", "Rfid", teacher.getRfid(), 20, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5 no-padding", false, "Cellphone No.", "Cellphone No.", "CpNumber", teacher.getCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "")%>
				<div class="col-sm-3 no-padding"></div>
					<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Program Graduated", "Program Graduated", "ProgramGraduated", teacher.getProgramGraduated(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5 no-padding", false, "Email Address", "Email Address", "EmailAddress", teacher.getEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%= TeacherUtil.getTableWithLabel(sessionInfo, "Academic Program", "AcademicProgram", true, academicProgramList, teacher.getAcademicProgramCodes(), "", 8, "border-left-right border-top-bottom p-xs b-r-md") %>
				</div>
				
				<div class="col-sm-12 no-padding">
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false,  "Salutation", "PrefixName", UserDTO.PREFIX_NAME_LIST, teacher.getPrefixName(), UserDTO.PREFIX_NAME_LIST, "", "", "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Last Name", "Last Name", "LastName", teacher.getLastName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 no-padding", true, "First Name", "First Name", "FirstName", teacher.getFirstName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Middle Name", "Middle Name", "MiddleName", teacher.getMiddleName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Suffix", "SuffixName", UserDTO.SUFFIX_NAME_LIST, teacher.getSuffixName(), UserDTO.SUFFIX_NAME_LIST, "", "", "") %>
				</div>
					<div class="col-sm-12 m-t-lg no-padding">
						<div class="col-sm-6">
							<div class="panel panel-default">
						    	<div class="panel-heading">
						            Permanent Address
						        </div>
						        <div class="panel-body">
						           	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street/Lot/Block", "Street", "StreetPermanent", teacher.getStreetPermanent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'")%>
									<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", true, "Barangay", "Barangay", "BarangayPermanent", teacher.getBarangayPermanent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'")%>
									<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6", true, "City", "CityPermanent", cityList, teacher.getCityPermanent(), "", "", "onchange='setPresentAddressToPermanentAddress()'")%>
						        </div>
						    </div>
						</div>
						<div class="col-sm-6">
						    <div class="panel panel-default">
						        <div class="panel-heading">
						            Present Address
						            &nbsp;&nbsp;<input type="checkbox" id="chkSameAsPermanent" name="chkSameAsPermanent" onclick="setPresentAddressToPermanentAddressOnCheckboxClick()"> Same as Permanent
						        </div>
						        <div class="panel-body">
						           	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street/Lot/Block", "Street", "StreetPresent", teacher.getStreetPresent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'")%>
									<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", true, "Barangay", "Barangay", "BarangayPresent", teacher.getBarangayPresent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'")%>
									<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6", true, "City", "CityPresent", cityList, teacher.getCityPresent(), "", "", "")%>
						        </div>
						    </div>
						</div>
					</div>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Birth Date", "Birth Date", "BirthDate", DateTimeUtil.getDateTimeToStr(teacher.getBirthDate(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true") %>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Gender", "Gender", new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, teacher.getGender(), new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, "", "", "") %>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", false, "Religion", "Religion", religionList, teacher.getReligion(), "", "", "")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", false, "Status", "MaritalStatus", new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, teacher.getMaritalStatus(), new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, "", "", "") %>

		<div class="col-sm-12">
	<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary", "align='center'") %>
	</div>			
</div>

<%
if(sessionInfo.isCurrentLinkDataEntry()) {
	if(!StringUtil.isEmpty(teacher.getStreetPermanent())) {
		if(teacher.getStreetPermanent().equalsIgnoreCase(teacher.getStreetPresent()) && teacher.getBarangayPermanent().equalsIgnoreCase(teacher.getBarangayPresent()) && teacher.getCityPermanent().getCode().equalsIgnoreCase(teacher.getCityPresent().getCode())) {
%>
	<script>
		document.getElementById("chkSameAsPermanent").checked = "checked";
		document.getElementById("txtStreetPresent").disabled = true;
		document.getElementById("txtBarangayPresent").disabled = true;
		document.getElementById("cboCityPresent").disabled = true;
	</script>
<%			
		}
		else {
%>
	<script>
		document.getElementById("chkSameAsPermanent").checked = "";
		document.getElementById("txtStreetPresent").disabled = false;
		document.getElementById("txtBarangayPresent").disabled = false;
		document.getElementById("cboCityPresent").disabled = false;
	</script>
<%			
		}
	}
}
%>

<script>
	function setPresentAddressToPermanentAddressOnCheckboxClick() {
		setPresentAddressToPermanentAddress();
		toggleEnable("txtStreetPresent");
		toggleEnable("txtBarangayPresent");
		toggleEnable("cboCityPresent");
	}
	
	function setPresentAddressToPermanentAddress() {
		if(document.getElementById("chkSameAsPermanent").checked) {
			document.getElementById("txtStreetPresent").value = document.getElementById("txtStreetPermanent").value;
			document.getElementById("txtBarangayPresent").value = document.getElementById("txtBarangayPermanent").value;
			document.getElementById("cboCityPresent").value = document.getElementById("cboCityPermanent").value;
		}
	}
	<%
	if(sessionInfo.isCurrentLinkAdd()) {
	%>
		document.getElementById("cboLevel").focus();
	<%
	}
	%>
</script>
<script>
	$(function(){
		$('input:checkbox').click(function(){
			getProgramGroupChecked();
		});
	});
</script>

<script>
	function getProgramGroupChecked(){
		var str = [];
		$.each($("input:checkbox[name='chkProgramCodes']:checked"), function(){            
			str.push($(this).val());
		});
		document.getElementById("txtCheckedAcademicProgramCodes").value = str.join("~");
	}
</script>