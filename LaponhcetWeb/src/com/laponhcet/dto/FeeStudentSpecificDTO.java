package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class FeeStudentSpecificDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FEESTUDENTSPECIFIC = "SESSION_FEESTUDENTSPECIFIC";
	public static final String SESSION_FEESTUDENTSPECIFIC_LIST = "SESSION_FEESTUDENTSPECIFIC_LIST";
	public static final String SESSION_FEESTUDENTSPECIFIC_PAGINATION = "SESSION_FEESTUDENTSPECIFIC_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name"};
	
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	private StudentDTO student;
	private FeeDTO fee;
	private double amount;
	
	public FeeStudentSpecificDTO() {
		super();
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		student = new StudentDTO();
		fee = new FeeDTO();
		amount = 0d;
	}
	
	public FeeStudentSpecificDTO getFeeStudentSpecific() {
		FeeStudentSpecificDTO feeStudentSpecific = new FeeStudentSpecificDTO();
		feeStudentSpecific.setId(super.getId());
		feeStudentSpecific.setAcademicYear(this.academicYear);
		feeStudentSpecific.setSemester(this.semester);
		feeStudentSpecific.setStudent(this.student);
		feeStudentSpecific.setFee(this.fee);
		feeStudentSpecific.setAmount(this.amount);
		return feeStudentSpecific;
	}

	public AcademicYearDTO getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYearDTO academicYear) {
		this.academicYear = academicYear;
	}

	public SemesterDTO getSemester() {
		return semester;
	}

	public void setSemester(SemesterDTO semester) {
		this.semester = semester;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public FeeDTO getFee() {
		return fee;
	}

	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
