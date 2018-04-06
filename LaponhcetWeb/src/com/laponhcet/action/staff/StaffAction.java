package com.laponhcet.action.staff;

import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.StaffDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.AcademicProgramUtil;
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

public class StaffAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void setInput() {
		StaffDTO staff = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF);
		List<DTOBase> cityList = (List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST);
		List<DTOBase> religionList = (List<DTOBase>) getSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		
		//Personal Information
		staff.setProfilePict(getRequestString("txtProfilePict", true));
		staff.setRfid(getRequestString("txtRfid"));
		staff.setPrefixName(getRequestString("cboPrefixName"));
		staff.setLastName(getRequestString("txtLastName"));
		staff.setFirstName(getRequestString("txtFirstName"));
		staff.setMiddleName(getRequestString("txtMiddleName"));
		staff.setSuffixName(getRequestString("cboSuffixName"));
		staff.setOtherTitle(getRequestString("txtOtherTitle"));
		
		// Address
		staff.setStreetPermanent(getRequestString("txtStreetPermanent"));
		staff.setBarangayPermanent(getRequestString("txtBarangayPermanent"));
		staff.setCityPermanent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPermanent")));
		// If Present Address == Permanent Address
		if(getRequestBoolean("chkSameAsPermanent")) {
			staff.setStreetPresent(staff.getStreetPermanent());
			staff.setBarangayPresent(staff.getBarangayPermanent());
			staff.setCityPresent(staff.getCityPermanent());
		}
		else {
			staff.setStreetPresent(getRequestString("txtStreetPresent"));
			staff.setBarangayPresent(getRequestString("txtBarangayPresent"));
			staff.setCityPresent((CityDTO) DTOUtil.getObjById(cityList, getRequestInt("cboCityPresent")));
				}
		staff.setBirthDate(DateTimeUtil.getStrToDateTime(getRequestString("txtBirthDate"), "MM/dd/yyyy"));
		staff.setGender(getRequestString("cboGender"));
		staff.setReligion((ReligionDTO) DTOUtil.getObjById(religionList, getRequestInt("cboReligion")));
		staff.setMaritalStatus(getRequestString("cboStatus"));
		staff.setCpNumber(getRequestString("txtCpNumber"));
		staff.setEmailAddress(getRequestString("txtEmailAddress"));
		staff.setProgramGraduated(getRequestString("txtProgramGraduated"));
		
	    staff.setJobRole(getRequestString("txtJobRole"));
	    staff.setAssignedOffice(getRequestString("txtAssignedOffice"));
		staff.setAcademicProgramCodes(getSelectedCheckBox(academicProgramList, "AcademicProgram"));
	}


	protected void validateInput() {
		if (!sessionInfo.isCurrentLinkDeleteSubmit()) {
			StaffDTO staff = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF);
			// Academic Program
			 if (StringUtil.isEmpty(staff.getAcademicProgramCodes())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			    }
			 	// Last Name and First Name
			 	// Other have no middle name
			 	else if (StringUtil.isEmpty(staff.getLastName())) {
			      actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name");
			    }
			    else if (StringUtil.isEmpty(staff.getFirstName())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name");
			    }
				// Prefix Name and Gender
			    else if(!staff.getPrefixName().isEmpty()) {
					if(staff.getGender().equalsIgnoreCase(UserDTO.GENDER_FEMALE)) {
						if(!staff.isGenderFemaleByPrefixName(staff.getPrefixName())) {
							if(staff.isGenderMaleByPrefixName(staff.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{staff.getPrefixName(), staff.getGender()});
							}
						}
					}
					else if(staff.getGender().equalsIgnoreCase(UserDTO.GENDER_MALE)) {
						if(!staff.isGenderMaleByPrefixName(staff.getPrefixName())) {
							if(staff.isGenderFemaleByPrefixName(staff.getPrefixName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_MISMATCH, new String[]{staff.getPrefixName(), staff.getGender()});
							}
						}
					}
				}
				// Permanent Street/Lot/Block
				else if(StringUtil.isEmpty(staff.getStreetPermanent())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Street");
				}
			 	// Permanent Barangay
				else if(StringUtil.isEmpty(staff.getStreetPermanent())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Permanent Barangay");
				}
				// Present Street/Lot/Block
				else if(StringUtil.isEmpty(staff.getStreetPresent())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Street");
				}
				// Present Barangay
				else if(StringUtil.isEmpty(staff.getStreetPresent())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Present Barangay");
				}
				// Phone Number
			    else if (!StringUtil.isEmpty(staff.getCpNumber()) && !StringUtil.isValidCPNumber(staff.getCpNumber())) {
						actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Cellphone Number.");
				}
			 	// Student Email Address
				else if(!StringUtil.isEmpty(staff.getEmailAddress()) && !WebUtil.isValidEmail(staff.getEmailAddress())) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Email Address is not valid!");
				}
			 	// Birthday
				else if(DateTimeUtil.getNumberOfMonths(staff.getBirthDate(), DateTimeUtil.getCurrentTimestamp()) < 24){
						actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Birth Date");
				}
			    else if (StringUtil.isEmpty(staff.getJobRole())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Job Role");
			    }
			    else if (StringUtil.isEmpty(staff.getAssignedOffice())) {
			    	actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Assigned Office");
			    }
			    else {
					if(sessionInfo.isPreviousLinkAdd()) {
						String msg = "";
						if(StringUtil.isEmpty(staff.getCode())) {
							List<DTOBase> existingUserListByNameAndGroup = new UserDAO().getUserListByLastNameFirstNameMiddleNameUserGroupCode(staff.getLastName(), staff.getFirstName(), staff.getMiddleName(), UserGroupDTO.USER_GROUP_STAFF_CODE);
							
							if(existingUserListByNameAndGroup.size() >= 1) {
								if(existingUserListByNameAndGroup.size() == 1) {
									UserDTO existingUser = (UserDTO) existingUserListByNameAndGroup.get(0);
									if(existingUser != null) {
										msg = "Entered staff last, first and middle name is already existing in our database.  The existing record has a birthday of " + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
									}	
								}
								else {
									for(DTOBase userObj: existingUserListByNameAndGroup) {
										UserDTO existingUser = (UserDTO) userObj;
										if(existingUser != null) {
											if(StringUtil.isEmpty(msg)) {
												msg = "Entered staff last, first and middle name is already existing in our database.  The existing records has the following birthdays: ";
											}
											msg += "<br>" + DateTimeUtil.getDateTimeToStr(existingUser.getBirthDate(), "MM/dd/yyyy");
										}
									}
								}
								msg += "\nProceeding will result to create a duplicate names unless they had been proven different.";
							}	
						}	
						
						if(StringUtil.isEmpty(msg)) {
							if(!StringUtil.isEmpty(staff.getRfid())) {
								UserDTO existingUser = new UserDAO().getUserByRFId(staff.getRfid());
								if(existingUser != null) {
									msg = "RFID was already encoded to " + existingUser.getName(false, false, true);
								}
							}
						}
						
						if(StringUtil.isEmpty(msg)) {
							if(!StringUtil.isEmpty(staff.getCpNumber())) {
								UserDTO existingUser = UserUtil.getUserByCpNumber(staff.getCpNumber());
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
						StaffDTO teacherOrig = (StaffDTO) getSessionAttribute(StaffDTO.SESSION_STAFF + "_ORIG");
						if(teacherOrig.equals(staff)){
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, "No change has been done.");
						}
					}
				}
			}
		}
	}
		 
