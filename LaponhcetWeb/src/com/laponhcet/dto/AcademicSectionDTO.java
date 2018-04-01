package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class AcademicSectionDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_ACADEMIC_SECTION = "SESSION_ACADEMIC_SECTION";
	public static final String SESSION_ACADEMIC_SECTION_LIST = "SESSION_ACADEMIC_SECTION_LIST";
	public static final String SESSION_ACADEMIC_SECTION_PAGINATION = "SESSION_ACADEMIC_SECTION_PAGINATION";
	
	private AcademicProgramDTO academicProgram;
	private int yearLevel;
	private String name;
	private String oldCode;
	
	public AcademicSectionDTO() {
		super();
		academicProgram = new AcademicProgramDTO();
		name = "";
		yearLevel = 0;
		oldCode = "";
	}
	
	public AcademicSectionDTO getAcademicSection() {
		AcademicSectionDTO academicSection = new AcademicSectionDTO();
		academicSection.setId(super.getId());
		academicSection.setCode(super.getCode());
		academicSection.setAcademicProgram(this.academicProgram);
		academicSection.setYearLevel(this.yearLevel);
		academicSection.setName(this.name);
		academicSection.setOldCode(this.oldCode);
		academicSection.setDisplayText(this.name);
		return academicSection;
	}

	public AcademicProgramDTO getAcademicProgram() {
		return academicProgram;
	}

	public void setAcademicProgram(AcademicProgramDTO academicProgram) {
		this.academicProgram = academicProgram;
	}

	public int getYearLevel() {
		return yearLevel;
	}

	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}
}
