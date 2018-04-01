package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;

public class AcademicProgramDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_ACADEMIC_PROGRAM = "SESSION_ACADEMIC_PROGRAM";
	public static final String SESSION_ACADEMIC_PROGRAM_LIST = "SESSION_ACADEMIC_PROGRAM_LIST";
	public static final String SESSION_ACADEMIC_PROGRAM_PAGINATION = "SESSION_ACADEMIC_PROGRAM_PAGINATION";
	
	public static final String[] ACADEMIC_PROGRAM_PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "Description"};
		
	private AcademicProgramGroupDTO academicProgramGroup;
	private AcademicProgramSubgroupDTO academicProgramSubgroup;
	private String name;
	private String major;
	private String description;
	private int graduationYearLevel;
	private UserDTO headUser;
	private String logo;

	public AcademicProgramDTO() {
		super();
		academicProgramGroup = new AcademicProgramGroupDTO();
		academicProgramSubgroup = new AcademicProgramSubgroupDTO();
		name = "";
		major = "";
		description = "";
		graduationYearLevel = 0;
		headUser = new UserDTO();
		logo = "";
	}
	
	public AcademicProgramDTO getAcademicProgram() {
		AcademicProgramDTO academicProgram = new AcademicProgramDTO();
		academicProgram.setId(super.getId());
		academicProgram.setCode(super.getCode());
		academicProgram.setAcademicProgramGroup(this.academicProgramGroup);
		academicProgram.setAcademicProgramSubgroup(this.academicProgramSubgroup);
		academicProgram.setName(this.name);
		academicProgram.setMajor(this.major);
		academicProgram.setDescription(this.description);
		academicProgram.setGraduationYearLevel(this.graduationYearLevel);
		academicProgram.setHeadUser(this.headUser);
		academicProgram.setLogo(this.logo);
		academicProgram.setDisplayText(super.getDisplayText());
		return academicProgram;
	}

	public AcademicProgramGroupDTO getAcademicProgramGroup() {
		return academicProgramGroup;
	}

	public void setAcademicProgramGroup(AcademicProgramGroupDTO academicProgramGroup) {
		this.academicProgramGroup = academicProgramGroup;
	}

	public AcademicProgramSubgroupDTO getAcademicProgramSubgroup() {
		return academicProgramSubgroup;
	}

	public void setAcademicProgramSubgroup(AcademicProgramSubgroupDTO academicProgramSubgroup) {
		this.academicProgramSubgroup = academicProgramSubgroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGraduationYearLevel() {
		return graduationYearLevel;
	}

	public void setGraduationYearLevel(int graduationYearLevel) {
		this.graduationYearLevel = graduationYearLevel;
	}

	public UserDTO getHeadUser() {
		return headUser;
	}

	public void setHeadUser(UserDTO headUser) {
		this.headUser = headUser;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
