package com.laponhcet.dto;

import java.util.Date;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class SemesterDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_SEMESTER = "SESSION_SEMESTER";
	public static final String SESSION_SEMESTER_LIST = "SESSION_SEMESTER_LIST";
	public static final String SESSION_SEMESTER_PAGINATION = "SESSION_SEMESTER_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Academic Year", "Name"};
	
	public static final int SEMESTER_NAME_SUMMER = 0;
	public static final int SEMESTER_NAME_FIRST = 1;
	public static final int SEMESTER_NAME_SECOND = 2;
	
	public static final String SEMESTER_DESCRIPTION_SUMMER = "SUMMER";
	public static final String SEMESTER_DESCRIPTION_FIRST = "FIRST SEMESTER";
	public static final String SEMESTER_DESCRIPTION_SECOND = "SECOND SEMESTER";
	
	
	public static final String SEMESTER_NAME_LIST = String.valueOf(SEMESTER_NAME_SUMMER) + String.valueOf(SEMESTER_NAME_FIRST) + String.valueOf(SEMESTER_NAME_SECOND);
	public static final String[] SEMESTER_DESCRIPTION_LIST = new String[] {SEMESTER_DESCRIPTION_SUMMER, SEMESTER_DESCRIPTION_FIRST, SEMESTER_DESCRIPTION_SECOND}; 

	private AcademicYearDTO academicYear;
	private int name;
	private Date dateStart; //within selected academic year date start and date end
	private Date dateEnd; //within selected academic year date start and date end but not less than semester date start
	
	
	public SemesterDTO() {
		super();
		this.academicYear = new AcademicYearDTO();
		this.name = 0;
		this.dateStart = null;
		this.dateEnd = null;
	}
	
	public SemesterDTO getSemester() {
		SemesterDTO semester = new SemesterDTO();
		semester.setId(super.getId());
		semester.setCode(super.getCode());
		semester.setAcademicYear(this.academicYear);
		semester.setName(this.name);
		semester.setDateStart(this.dateStart);
		semester.setDateEnd(this.dateEnd);
		semester.setDisplayText(getDescription1());
		return semester;
	}

	public String getDescription1() {
		if(name == SEMESTER_NAME_SUMMER) {
			return StringUtil.getLeft(this.academicYear.getName(), 4) + " | " + SEMESTER_DESCRIPTION_LIST[name] + " (" + DateTimeUtil.getDateTimeToStr(dateStart, "MM-dd-yyyy") + " - " + DateTimeUtil.getDateTimeToStr(dateEnd, "MM-dd-yyyy") + ")";
		}
		else {
			return this.academicYear.getName() + " | " + SEMESTER_DESCRIPTION_LIST[name] + " (" + DateTimeUtil.getDateTimeToStr(dateStart, "MM-dd-yyyy") + " - " + DateTimeUtil.getDateTimeToStr(dateEnd, "MM-dd-yyyy") + ")";
		}
	}

	public String getDescription2() {
		if(name == SEMESTER_NAME_SUMMER) {
			return SEMESTER_DESCRIPTION_LIST[name] + " " + StringUtil.getLeft(this.academicYear.getName(), 4) + " (" + DateTimeUtil.getDateTimeToStr(dateStart, "MM-dd-yyyy") + " - " + DateTimeUtil.getDateTimeToStr(dateEnd, "MM-dd-yyyy") + ")";
		}
		else {
			return SEMESTER_DESCRIPTION_LIST[name] + " S.Y. " + this.academicYear.getName() + " (" + DateTimeUtil.getDateTimeToStr(dateStart, "MM-dd-yyyy") + " - " + DateTimeUtil.getDateTimeToStr(dateEnd, "MM-dd-yyyy") + ")";
		}
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

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public AcademicYearDTO getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYearDTO academicYear) {
		this.academicYear = academicYear;
	}
}
