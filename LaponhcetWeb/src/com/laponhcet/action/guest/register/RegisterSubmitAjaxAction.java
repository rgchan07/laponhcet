package com.laponhcet.action.guest.register;
import java.io.IOException;
import org.json.JSONObject;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class RegisterSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		RegisterDTO register = (RegisterDTO) getSessionAttribute(RegisterDTO.SESSION_REGISTER);
		JSONObject jsonObj = getJsonObj();
		// Last Name, First Name, Middle Name 
		if(register.getLastName().isEmpty()) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name", jsonObj);
		}
		else if(register.getFirstName().isEmpty()) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name", jsonObj);
		}
		else if(register.getMiddleName().isEmpty()) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Middle Name", jsonObj);
		}
		else if(new RegisterDAO().getRegisterByName(register.getLastName(), register.getFirstName(), register.getMiddleName()) != null) {
			RegisterDTO registerExist = new RegisterDAO().getRegisterByName(register.getLastName(), register.getFirstName(), register.getMiddleName());
			actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Last, First and Middle Name already found in the system with an email address of: " + registerExist.getEmailAddress() + ". Proceeding will create a duplicate record, unless this is a different one.", jsonObj);
		}
		// Prefix Name and Gender
		else if(!register.getPrefixName().isEmpty()) {
			if(register.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
				if(!register.isGenderFemaleByPrefixName(register.getPrefixName())) {
					if(register.isGenderMaleByPrefixName(register.getPrefixName())) {
						actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{register.getPrefixName(), register.getGender()}, jsonObj);
					}
				}
			}
			else if(register.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
				if(!register.isGenderMaleByPrefixName(register.getPrefixName())) {
					if(register.isGenderFemaleByPrefixName(register.getPrefixName())) {
						actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{register.getPrefixName(), register.getGender()}, jsonObj);
					}
				}
			}
		}
		else if(DateTimeUtil.getNumberOfMonths(register.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 216){
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date", jsonObj);
		}
		// Phone Number
		else if(!StringUtil.isValidCPNumber(register.getCpNumber())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Cellphone Number.", jsonObj);
		}
		//Email Address
		else if(StringUtil.isEmpty(register.getEmailAddress())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Email Address.", jsonObj);
		}
		else if(!WebUtil.isValidEmail(register.getEmailAddress())) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address is not valid!", jsonObj);
		}
		else if(StringUtil.isEmpty(register.getInstitutionConnectedWith())){
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Institution Connected With", jsonObj);
		}
		else if(StringUtil.isEmpty(register.getOccupation())){
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Occupation", jsonObj);
		}
	}
	
	protected void setSessionVars() {
		JSONObject jsonObj = getJsonObj();
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected void setInput() {
		RegisterDTO register = (RegisterDTO) getSessionAttribute(RegisterDTO.SESSION_REGISTER);
		register.setPrefixName(getRequestString("cboPrefixName"));
		register.setLastName(getRequestString("txtLastName"));
		register.setFirstName(getRequestString("txtFirstName"));
		register.setMiddleName(getRequestString("txtMiddleName"));
		register.setSuffixName(getRequestString("cboSuffixName"));
		
		register.setGender(getRequestString("rbGender"));
		register.setBirthDate(DateTimeUtil.getStrToDateTime(getRequestString("txtBirthDate"), "MM/dd/yyyy"));
		register.setCpNumber(getRequestString("txtCpNumber"));
		register.setEmailAddress(getRequestString("txtEmailAddress"));

		register.setInstitutionConnectedWith(getRequestString("txtInstitutionConnectedWith"));
		register.setOccupation(getRequestString("txtOccupation"));
	}
}

