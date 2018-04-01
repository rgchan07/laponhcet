package com.laponhcet.dto;

import java.util.Date;

import com.mytechnopal.base.DTOBase;

public class AcademicYearDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_ACADEMIC_YEAR = "SESSION_ACADEMIC_YEAR";
	public static final String SESSION_ACADEMIC_YEAR_LIST = "SESSION_ACADEMIC_YEAR_LIST";
	public static final String SESSION_ACADEMIC_YEAR_PAGINATION = "SESSION_ACADEMIC_YEAR_PAGINATION";

	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "Program"};
	
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private String academicProgramCodes;
	
	public AcademicYearDTO() {
		super();
		this.name = "";
		this.dateStart = null;
		this.dateEnd = null;
		this.academicProgramCodes = "";
	}
	
	public AcademicYearDTO getAcademicYear() {
		AcademicYearDTO academicYear = new AcademicYearDTO();
		academicYear.setId(super.getId());
		academicYear.setCode(super.getCode());
		academicYear.setName(this.name);
		academicYear.setDateStart(this.dateStart);
		academicYear.setDateEnd(this.dateEnd);
		academicYear.setAcademicProgramCodes(this.academicProgramCodes);
		return academicYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}

	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}
}
