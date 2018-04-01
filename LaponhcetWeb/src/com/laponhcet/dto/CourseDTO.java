package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;

public class CourseDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_COURSE = "SESSION_COURSE";
	public static final String SESSION_COURSE_LIST = "SESSION_COURSE_LIST";
	public static final String SESSION_COURSE_PAGINATION = "SESSION_COURSE_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Code", "Description"};
	
	private String description;
	private double creditUnit;	
	private double payUnit;
	private double lectureHour;
	private double laboratoryHour;
	private String academicProgramCodes;
	private CourseGroupDTO courseGroup;
	
	public CourseDTO() {
		super();
		description = "";
		creditUnit = 0;
		payUnit = 0;
		lectureHour = 0;
		laboratoryHour = 0;
		academicProgramCodes = "";
		courseGroup = new CourseGroupDTO(); 
	}
	
	public CourseDTO getCourse() {
		CourseDTO course = new CourseDTO();
		course.setId(super.getId());
		course.setCode(super.getCode());
		course.setDescription(this.description);
		course.setCreditUnit(this.creditUnit);
		course.setPayUnit(this.payUnit);
		course.setLectureHour(this.lectureHour);
		course.setLaboratoryHour(this.laboratoryHour);
		course.setAcademicProgramCodes(this.academicProgramCodes);
		course.setCourseGroup(this.courseGroup);
		course.setDisplayText(super.getDisplayText());
		return course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCreditUnit() {
		return creditUnit;
	}

	public void setCreditUnit(double creditUnit) {
		this.creditUnit = creditUnit;
	}

	public double getPayUnit() {
		return payUnit;
	}

	public void setPayUnit(double payUnit) {
		this.payUnit = payUnit;
	}

	public double getLectureHour() {
		return lectureHour;
	}

	public void setLectureHour(double lectureHour) {
		this.lectureHour = lectureHour;
	}

	public double getLaboratoryHour() {
		return laboratoryHour;
	}

	public void setLaboratoryHour(double laboratoryHour) {
		this.laboratoryHour = laboratoryHour;
	}

	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}

	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}

	public CourseGroupDTO getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(CourseGroupDTO courseGroup) {
		this.courseGroup = courseGroup;
	}
}
