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
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.UserUtil;
import com.mytechnopal.util.WebUtil;

public class TeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> cityList = (List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST);
		List<DTOBase> religionList = (List<DTOBase>) getSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST);
		
		//Personal Information
		teacher.setProfilePict(getRequestString("txtProfilePict", true));
		teacher.setRfid(getRequestString("txtRfid"));
		teacher.setPrefixName(getRequestString("cboPrefixName"));
		teacher.setLastName(getRequestString("txtLastName"));
		teacher.setFirstName(getRequestString("txtFirstName"));
		teacher.setMiddleName(getRequestString("txtMiddleName"));
		teacher.setSuffixName(getRequestString("cboSuffixName"));
		teacher.setOtherTitle(getRequestString("txtOtherTitle"));
		
		// Address
		teacher.setStreetPermanent(getRequestString("txtStreetPermanent"));
		teacher.setBarangayPermanent(getRequestString("txtBarangayPermanent"));
		teacher.setCityPermanent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPermanent")));
		// If Present Address == Permanent Address
		if(getRequestBoolean("chkSameAsPermanent")) {
			teacher.setStreetPresent(teacher.getStreetPermanent());
			teacher.setBarangayPresent(teacher.getBarangayPermanent());
			teacher.setCityPresent(teacher.getCityPermanent());
		}
		else {
			teacher.setStreetPresent(getRequestString("txtStreetPresent"));
			teacher.setBarangayPresent(getRequestString("txtBarangayPresent"));
			teacher.setCityPresent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPresent")));
		}
				
		teacher.setBirthDate(DateTimeUtil.getStrToDateTime(getRequestString("txtBirthDate"), "MM/dd/yyyy"));
		teacher.setGender(getRequestString("cboGender"));
		teacher.setReligion((ReligionDTO) DTOUtil.getObjById(religionList, getRequestInt("cboReligion")));
		teacher.setMaritalStatus(getRequestString("cboMaritalStatus"));
		teacher.setCpNumber(getRequestString("txtCpNumber"));
		teacher.setEmailAddress(getRequestString("txtEmailAddress"));
		teacher.setProgramGraduated(getRequestString("txtProgramGraduated"));
		teacher.setAcademicProgramCodes(getSelectedCheckBox(academicProgramList, "AcademicProgram"));
	}
	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()) {
			TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
			//Academic Program
			if(StringUtil.isEmpty(teacher.getAcademicProgramCodes())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			}
			// Last Name and First Name
			// Other have no middle name
			else if(StringUtil.isEmpty(teacher.getLastName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Lastname");
			}
			else if(StringUtil.isEmpty(teacher.getFirstName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Firstname");
			}	
			// Teacher Phone Number
			else if(!StringUtil.isEmpty(teacher.getCpNumber()) && !StringUtil.isValidCPNumber(teacher.getCpNumber())) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Cellphone Number");
			}	
			// Teacher Email Address
			else if(!StringUtil.isEmpty(teacher.getEmailAddress()) && !WebUtil.isValidEmail(teacher.getEmailAddress())) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address is not valid!");
			}
			// Birthday
			else if(DateTimeUtil.getNumberOfMonths(teacher.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 24){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date");
			}
			// Prefix Name and Gender
			else if(!StringUtil.isEmpty(teacher.getPrefixName())) {
					if(teacher.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
						if(!teacher.isGenderFemaleByPrefixName(teacher.getPrefixName())) {
							if(teacher.isGenderMaleByPrefixName(teacher.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{teacher.getPrefixName(), teacher.getGender()});
								}
							}
						}
					else if(teacher.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
						if(!teacher.isGenderMaleByPrefixName(teacher.getPrefixName())) {
							if(teacher.isGenderFemaleByPrefixName(teacher.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{teacher.getPrefixName(), teacher.getGender()});
							}
						}
					}
				}				
			// Permanent Street/Lot/Block
				else if(StringUtil.isEmpty(teacher.getStreetPermanent())) {
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Street");
				}
			// Permanent Barangay
				else if(StringUtil.isEmpty(teacher.getStreetPermanent())) {
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Barangay");
				}
			// Present Street/Lot/Block
				else if(StringUtil.isEmpty(teacher.getStreetPresent())) {
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Street");
				}
			// Present Barangay
				else if(StringUtil.isEmpty(teacher.getStreetPresent())) {
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Barangay");
				}
				else {
					if(sessionInfo.isPreviousLinkAdd()) {
						String msg = "";
						if(StringUtil.isEmpty(teacher.getCode())) {
							List<DTOBase> existingUserListByNameAndGroup = new UserDAO().getUserListByLastNameFirstNameMiddleNameUserGroupCode(teacher.getLastName(), teacher.getFirstName(), teacher.getMiddleName(), UserGroupDTO.USER_GROUP_TEACHER_CODE);
							
							if(existingUserListByNameAndGroup.size() >= 1) {
								if(existingUserListByNameAndGroup.size() == 1) {
									UserDTO existingUser = (UserDTO) existingUserListByNameAndGroup.get(0);
									if(existingUser != null) {
										msg = "Entered teacher last, first and middle name is already existing in our database.  The existing record has a birthday of " + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
									}	
								}
								else {
									for(DTOBase userObj: existingUserListByNameAndGroup) {
										UserDTO existingUser = (UserDTO) userObj;
										if(existingUser != null) {
											if(StringUtil.isEmpty(msg)) {
												msg = "Entered teacher last, first and middle name is already existing in our database.  The existing records has the following birthdays: ";
											}
											msg += "<br>" + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
										}
									}
								}
								msg += "\nProceeding will result to create a duplicate names unless they had been proven different.";
							}	
						}	
						
						if(StringUtil.isEmpty(msg)) {
							if(!StringUtil.isEmpty(teacher.getRfid())) {
								UserDTO existingUser = new UserDAO().getUserByRFId(teacher.getRfid());
								if(existingUser != null) {
									msg = "RFID was already encoded to " + existingUser.getName(false, false, true);
								}
							}
						}
						
						if(StringUtil.isEmpty(msg)) {
							if(!StringUtil.isEmpty(teacher.getCpNumber())) {
								UserDTO existingUser = UserUtil.getUserByCpNumber(teacher.getCpNumber());
								if(existingUser != null) {
									msg = "CP Number was already encoded to " + existingUser.getName(false, false, true);
								}
							}
						}
						
						if(!StringUtil.isEmpty(msg)) {
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, msg);
						}
					}	
					else if(sessionInfo.isPreviousLinkUpdate()) {
						TeacherDTO teacherOrig = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER + "_ORIG");
						if(teacherOrig.equals(teacher)){
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, "No change has been done.");
						}
					}
				}
			}
		}
	}
