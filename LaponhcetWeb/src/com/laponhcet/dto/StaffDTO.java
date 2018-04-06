package com.laponhcet.dto;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;

public class StaffDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_STAFF = "SESSION_STAFF";
	public static final String SESSION_STAFF_LIST = "SESSION_STAFF_LIST";
	public static final String SESSION_STAFF_PAGINATION = "SESSION_STAFF_PAGINATION";
	
	public static final String[] STAFF_PAGINATION_SEARCH_CRITERIA_LIST  = new String[] {"Name or Code"};
	
	public static final String STAFF_PROFILE_PICT_DEFAULT = "/static/common/images/pict_default.png";

	
	private String programGraduated;
	private String jobRole;
	private String assignedOffice;
	private AcademicProgramDTO academicProgram;
	private String academicProgramCodes;
	
	
	public StaffDTO() {
		super();
		super.getCityPermanent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getCityPresent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getReligion().setCode(SettingsUtil.DEFAULT_RELIGION);
		super.setBirthDate(DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd"));
		programGraduated = "";
		jobRole = "";
		assignedOffice = "";
		academicProgramCodes = "";
		
	}
	public StaffDTO getStaff() {
		StaffDTO staff = new StaffDTO();
	
		
		staff.setRfid(super.getRfid());
		staff.setUserName(super.getUserName());
		staff.setPassword(super.getPassword());
		staff.setUserGroup(super.getUserGroup());
		staff.setLastName(super.getLastName());
		staff.setFirstName(super.getFirstName());
		staff.setMiddleName(super.getMiddleName());
		staff.setPrefixName(super.getPrefixName());
		staff.setSuffixName(super.getSuffixName());
		staff.setOtherTitle(super.getOtherTitle());
		staff.setStreetPermanent(super.getStreetPermanent());
		staff.setBarangayPermanent(super.getBarangayPermanent());
		staff.setCityPermanent(super.getCityPermanent());
		staff.setStreetPresent(super.getStreetPresent());
		staff.setBarangayPresent(super.getBarangayPresent());
		staff.setCityPresent(super.getCityPresent());
		staff.setBirthDate(super.getBirthDate());
		staff.setGender(super.getGender());
		staff.setReligion(super.getReligion());
		staff.setMaritalStatus(super.getMaritalStatus());
		staff.setCpNumber(super.getCpNumber());
		staff.setEmailAddress(super.getEmailAddress());
		staff.setActive(super.isActive());
		staff.setLastLoginTimestamp(super.getLastLoginTimestamp());
		staff.setLastLoginIPAddress(super.getLastLoginIPAddress());
		staff.setProfilePict(getStaffProfilePic(super.getProfilePict()));
		
		staff.setId(this.getId());
		staff.setCode(this.getCode());
		staff.setProgramGraduated(this.getProgramGraduated());
		staff.setJobRole(this.getJobRole());
		staff.setAssignedOffice(this.getAssignedOffice());
		staff.setAcademicProgramCodes(this.academicProgramCodes);
		staff.setDisplayText(this.getName(true, true, true));
		
		return staff;
	}
	public String getStaffProfilePic(String superProfilePict){
		if(superProfilePict.isEmpty()){
			superProfilePict = STAFF_PROFILE_PICT_DEFAULT; 
		}
		return superProfilePict;
	}
	public String getProgramGraduated() {
		return programGraduated;
	}

	public void setProgramGraduated(String programGraduated) {
		this.programGraduated = programGraduated;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getAssignedOffice() {
		return assignedOffice;
	}

	public void setAssignedOffice(String assignedOffice) {
		this.assignedOffice = assignedOffice;
	}
	public AcademicProgramDTO getAcademicProgram() {
		return academicProgram;
	}
	public void setAcademicProgram(AcademicProgramDTO academicProgram) {
		this.academicProgram = academicProgram;
	}
	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}
	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}

}
