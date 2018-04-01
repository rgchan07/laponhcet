package com.laponhcet.action.register;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class RegisterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		RegisterDTO register = (RegisterDTO) getSessionAttribute(RegisterDTO.SESSION_REGISTER);
		register.setPrefixName(getRequestString("cboPrefixName"));
		register.setLastName(getRequestString("txtLastName"));
		register.setFirstName(getRequestString("txtFirstName"));
		register.setMiddleName(getRequestString("txtMiddleName"));
		register.setSuffixName(getRequestString("cboSuffixName"));
		
		register.setGender(getRequestString("cboGender"));
		register.setBirthDate(DateTimeUtil.getStrToDateTime(getRequestString("txtBirthDate"), "MM/dd/yyyy"));
		register.setCpNumber(getRequestString("txtCpNumber"));
		register.setEmailAddress(getRequestString("txtEmailAddress", true));
		
		register.setInstitutionConnectedWith(getRequestString("txtInstitutionConnectedWith"));
		register.setOccupation(getRequestString("txtOccupation"));
	}

	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()){
			RegisterDTO register = (RegisterDTO) getSessionAttribute(RegisterDTO.SESSION_REGISTER);
			
			// Last Name, First Name, Middle Name 
			if(register.getLastName().isEmpty()) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name");
			}
			else if(register.getFirstName().isEmpty()) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name");
			}
			else if(register.getMiddleName().isEmpty()) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Middle Name");
			}
			else if(new RegisterDAO().getRegisterByName(register.getLastName(), register.getFirstName(), register.getMiddleName()) != null) {
				RegisterDTO registerExist = new RegisterDAO().getRegisterByName(register.getLastName(), register.getFirstName(), register.getMiddleName());
				actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Last, First and Middle Name already found in the system with an email address of: " + registerExist.getEmailAddress() + ". Proceeding will create a duplicate record, unless this is a different one.");
			}
			// Prefix Name and Gender
			else if(!register.getPrefixName().isEmpty()) {
				if(register.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
					if(!register.isGenderFemaleByPrefixName(register.getPrefixName())) {
						if(register.isGenderMaleByPrefixName(register.getPrefixName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{register.getPrefixName(), register.getGender()});
						}
					}
				}
				else if(register.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
					if(!register.isGenderMaleByPrefixName(register.getPrefixName())) {
						if(register.isGenderFemaleByPrefixName(register.getPrefixName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{register.getPrefixName(), register.getGender()});
						}
					}
				}
			}
			else if(DateTimeUtil.getNumberOfMonths(register.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 216){
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date");
			}
			// Phone Number
			else if(!StringUtil.isValidCPNumber(register.getCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Cellphone Number.");
			}
			//Email Address
			else if(StringUtil.isEmpty(register.getEmailAddress())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Email Address.");
			}
			else if(!WebUtil.isValidEmail(register.getEmailAddress())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address is not valid!");
			}
			else if(StringUtil.isEmpty(register.getInstitutionConnectedWith())){
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Institution Connected With");
			}
			else if(StringUtil.isEmpty(register.getOccupation())){
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Occupation");
			}
			else {
				RegisterDTO registerExist = new RegisterDAO().getRegisterByName(register.getLastName(), register.getFirstName(), register.getMiddleName());
				if(registerExist != null) {
					actionResponse.constructMessage(ActionResponse.TYPE_INFO, "There is already a member with the same last, first and middle name exist in the system.  Proceeding to add will create a duplicate entry.  Please make sure that they are not the same person.");
				}
			}
		}
	}
}
