package com.laponhcet.action.teacher;

import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;

public class TeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		String easy = getRequestString("txtdisableOrNot");
		teacher.setProfilePict(getRequestString("txtProfilePict", true));
		teacher.setRfid(getRequestString("txtRfid"));
		teacher.setPrefixName(getRequestString("cboPrefixName"));
		teacher.setLastName(getRequestString("txtLastName"));
		teacher.setFirstName(getRequestString("txtFirstName"));
		teacher.setMiddleName(getRequestString("txtMiddleName"));
		teacher.setSuffixName(getRequestString("cboSuffixName"));
		teacher.setOtherTitle(getRequestString("txtOtherTitle"));
		teacher.setStreetPermanent(getRequestString("txtStreetPermanent"));
		teacher.setBarangayPermanent(getRequestString("txtBarangayPermanent"));
		teacher.setCityPermanent((CityDTO)DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST), getRequestInt("cboCityPermanent")));
		if(!easy.isEmpty()) {
			teacher.setStreetPresent(getRequestString("txtStreetPermanent"));
			teacher.setBarangayPresent(getRequestString("txtBarangayPermanent"));
			teacher.setCityPresent((CityDTO)DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST), getRequestInt("cboCityPermanent")));
		}else {
			teacher.setStreetPresent(getRequestString("txtStreetPresent"));
			teacher.setBarangayPresent(getRequestString("txtBarangayPresent"));
			teacher.setCityPresent((CityDTO)DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST), getRequestInt("cboCityPresent")));
		}
		teacher.setBirthDate(getRequestDateTime("txtBirthDate", "MM/dd/yyyy"));
		teacher.setGender(getRequestString("cboGender"));
		teacher.setReligion((ReligionDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST), getRequestInt("cboReligion")));
		teacher.setMaritalStatus(getRequestString("cboStatus"));
		teacher.setCpNumber(getRequestString("txtCpNumber"));
		teacher.setEmailAddress(getRequestString("txtEmailAddress"));
		teacher.setProgramGraduated(getRequestString("txtProgramGraduated"));
		teacher.setAcademicProgramCodes(getSelectedCheckBox(academicProgramList, "AcademicProgram"));
	}
	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()) {
			TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
			if(StringUtil.isEmpty(teacher.getLastName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Lastname");
			}else if(StringUtil.isEmpty(teacher.getFirstName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Firstname");
			}else if(StringUtil.isEmpty(teacher.getMiddleName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Middlename");
			}else if(StringUtil.isEmpty(teacher.getStreetPermanent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Street in Permanent Address");
			}else if(StringUtil.isEmpty(teacher.getBarangayPermanent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Barangay in Permanent Address");
			}else if(StringUtil.isEmpty(teacher.getStreetPresent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Street in Present Address");
			}else if(StringUtil.isEmpty(teacher.getBarangayPresent())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Barangay in Present Address");
			}else if(StringUtil.isEmpty(teacher.getCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Cellphone Number");
			}else if(!StringUtil.isValidCPNumber(teacher.getCpNumber())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Cellphone Number");
			}else if(!WebUtil.isValidEmail(teacher.getEmailAddress())) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email");
			}else if(StringUtil.isEmpty(teacher.getAcademicProgramCodes())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			}else {
				List<DTOBase> userList = (List<DTOBase>) new UserDAO().getUserListByLastNameFirstNameMiddleNameUserGroupCode(teacher.getLastName(), teacher.getFirstName(), teacher.getMiddleName(), UserGroupDTO.USER_GROUP_TEACHER_CODE);
				UserDTO userRfidExist = new UserDAO().getUserByRFId(teacher.getRfid());
				if(userList.size() > 0){
					UserDTO userNameExist = new UserDAO().getUserByCode(userList.get(0).getCode());
					if(sessionInfo.isPreviousLinkAdd()) {
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Name: " + userNameExist.getName(false, true, false) + " is already existing in the system" + " with a birthday of " + userNameExist.getBirthDate());	
					}else if(sessionInfo.isPreviousLinkUpdate()) {
						TeacherDTO teacherOrig = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER + "_ORIG");
						if(!teacherOrig.getName(false, true, false).equalsIgnoreCase(teacher.getName(false, true, false))) {
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Name: " + userNameExist.getName(false, true, false) + " is already existing in the system" + " with a birthday of " + userNameExist.getBirthDate());
						}
					}
				}
			}
		}
	}
}
