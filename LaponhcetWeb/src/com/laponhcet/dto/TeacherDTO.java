package com.laponhcet.dto;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;

public class TeacherDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_TEACHER = "SESSION_TEACHER";
	public static final String SESSION_TEACHER_LIST = "SESSION_TEACHER_LIST";
	public static final String SESSION_TEACHER_PAGINATION = "SESSION_TEACHER_PAGINATION";
	
	public static final String TEACHER_PROFILE_PICT_DEFAULT = "/static/common/images/pict_default.png";
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] { "Code or Name"};
	
	private String programGraduated;
	private String academicProgramCodes;
	
	public TeacherDTO() {
		super();
		super.getCityPermanent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getCityPresent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getReligion().setCode(SettingsUtil.DEFAULT_RELIGION);
		super.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		this.programGraduated = "";
		this.academicProgramCodes = "";
	}
	
	public TeacherDTO getTeacher() {
		TeacherDTO teacher = new TeacherDTO();
		
		teacher.setRfid(super.getRfid());
		teacher.setFacebookId(super.getFacebookId());
		teacher.setUserName(super.getUserName());
		teacher.setPassword(super.getPassword());
		teacher.setUserGroup(super.getUserGroup());
		teacher.setLastName(super.getLastName());
		teacher.setFirstName(super.getFirstName());
		teacher.setMiddleName(super.getMiddleName());
		teacher.setPrefixName(super.getPrefixName());
		teacher.setSuffixName(super.getSuffixName());
		teacher.setOtherTitle(super.getOtherTitle());
		teacher.setGender(super.getGender());
		teacher.setStreetPermanent(super.getStreetPermanent());
		teacher.setBarangayPermanent(super.getBarangayPermanent());
		teacher.setCityPermanent(super.getCityPermanent());
		teacher.setStreetPresent(super.getStreetPresent());
		teacher.setBarangayPresent(super.getBarangayPresent());
		teacher.setCityPresent(super.getCityPresent());
		teacher.setBirthPlace(super.getBirthPlace());
		teacher.setBirthDate(super.getBirthDate());
		teacher.setReligion(super.getReligion());
		teacher.setMaritalStatus(super.getMaritalStatus());
		teacher.setCitizenship(super.getCitizenship());
		teacher.setPassportNumber(super.getPassportNumber());
		teacher.setOccupation(super.getOccupation());
		teacher.setCpNumber(super.getCpNumber());
		teacher.setLandlineNumber(super.getLandlineNumber());
		teacher.setEmailAddress(super.getEmailAddress());
		teacher.setFatherName(super.getFatherName());
		teacher.setFatherOccupation(super.getFatherOccupation());
		teacher.setFatherCpNumber(super.getFatherCpNumber());
		teacher.setMotherName(super.getMotherName());
		teacher.setMotherOccupation(super.getMotherOccupation());
		teacher.setMotherCpNumber(super.getMotherCpNumber());
		teacher.setGuardianName(super.getGuardianName());
		teacher.setGuardianOccupation(super.getGuardianOccupation());
		teacher.setGuardianRelation(super.getGuardianRelation());
		teacher.setContactPerson(super.getContactPerson());
		teacher.setContactRelation(super.getContactRelation());
		teacher.setContactAddress(super.getContactAddress());
		teacher.setContactCPNumber(super.getContactCPNumber());
		teacher.setContactLandlineNumber(super.getContactLandlineNumber());
		teacher.setContactEmailAddress(super.getContactEmailAddress());
		teacher.setContactFacebookId(super.getContactFacebookId());
		teacher.setSourceDeviceInfo(super.getSourceDeviceInfo());
		teacher.setActive(super.isActive());
		teacher.setHtmlSkin(super.getHtmlSkin());
		teacher.setLastLoginTimestamp(super.getLastLoginTimestamp());
		teacher.setLastLoginIPAddress(super.getLastLoginIPAddress());
		teacher.setProfilePict(super.getProfilePict());
		
		teacher.setId(this.getId());
		teacher.setCode(this.getCode());
		teacher.setProgramGraduated(this.programGraduated);
		teacher.setAcademicProgramCodes(this.academicProgramCodes);
		teacher.setDisplayText(this.getName(true, false, true));
		return teacher;
	}

	public String getProgramGraduated() {
		return programGraduated;
	}

	public void setProgramGraduated(String programGraduated) {
		this.programGraduated = programGraduated;
	}

	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}

	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}
	
	
}
