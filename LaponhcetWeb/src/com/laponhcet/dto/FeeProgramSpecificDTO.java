package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;

public class FeeProgramSpecificDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FEEPROGRAMSPECIFIC = "SESSION_FEEPROGRAMSPECIFIC";
	public static final String SESSION_FEEPROGRAMSPECIFIC_LIST = "SESSION_FEEPROGRAMSPECIFIC_LIST";
	public static final String SESSION_FEEPROGRAMSPECIFIC_PAGINATION = "SESSION_FEEPROGRAMSPECIFIC_PAGINATION";
	
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	private AcademicProgramDTO academicProgram;
	private int yearLevel;
	private FeeDTO fee;
	private double enrollmentStatusContinuing;
	private double enrollmentStatusCrossEnrollee;
	private double enrollmentStatusNew;
	private double enrollmentStatusReturnee;
	private double enrollmentStatusShiftee;
	private double enrollmentStatusTransferee;
	
	public FeeProgramSpecificDTO() {
		super();
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		academicProgram = new AcademicProgramDTO();
		yearLevel = 0;
		fee = new FeeDTO();
		enrollmentStatusContinuing = 0d;
		enrollmentStatusCrossEnrollee = 0d;
		enrollmentStatusNew = 0d;
		enrollmentStatusReturnee = 0d;
		enrollmentStatusShiftee = 0d;
		enrollmentStatusTransferee = 0d;
	}
	
	public FeeProgramSpecificDTO getFeeProgramSpecific() {
		FeeProgramSpecificDTO feeProgramSpecific = new FeeProgramSpecificDTO();
		feeProgramSpecific.setId(super.getId());
		feeProgramSpecific.setCode(super.getCode());
		feeProgramSpecific.setAcademicYear(this.academicYear);
		feeProgramSpecific.setSemester(this.semester);
		feeProgramSpecific.setAcademicProgram(this.academicProgram);
		feeProgramSpecific.setYearLevel(this.yearLevel);
		feeProgramSpecific.setFee(this.fee);
		feeProgramSpecific.setEnrollmentStatusContinuing(this.enrollmentStatusContinuing);	
		feeProgramSpecific.setEnrollmentStatusCrossEnrollee(this.enrollmentStatusCrossEnrollee);
		feeProgramSpecific.setEnrollmentStatusNew(this.enrollmentStatusNew);
		feeProgramSpecific.setEnrollmentStatusReturnee(this.enrollmentStatusReturnee);
		feeProgramSpecific.setEnrollmentStatusShiftee(this.enrollmentStatusShiftee);
		feeProgramSpecific.setEnrollmentStatusTransferee(this.enrollmentStatusTransferee);
		return feeProgramSpecific;
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

	public FeeDTO getFee() {
		return fee;
	}

	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}

	public double getEnrollmentStatusContinuing() {
		return enrollmentStatusContinuing;
	}

	public void setEnrollmentStatusContinuing(double enrollmentStatusContinuing) {
		this.enrollmentStatusContinuing = enrollmentStatusContinuing;
	}

	public double getEnrollmentStatusCrossEnrollee() {
		return enrollmentStatusCrossEnrollee;
	}

	public void setEnrollmentStatusCrossEnrollee(double enrollmentStatusCrossEnrollee) {
		this.enrollmentStatusCrossEnrollee = enrollmentStatusCrossEnrollee;
	}

	public double getEnrollmentStatusNew() {
		return enrollmentStatusNew;
	}

	public void setEnrollmentStatusNew(double enrollmentStatusNew) {
		this.enrollmentStatusNew = enrollmentStatusNew;
	}

	public double getEnrollmentStatusReturnee() {
		return enrollmentStatusReturnee;
	}

	public void setEnrollmentStatusReturnee(double enrollmentStatusReturnee) {
		this.enrollmentStatusReturnee = enrollmentStatusReturnee;
	}

	public double getEnrollmentStatusShiftee() {
		return enrollmentStatusShiftee;
	}

	public void setEnrollmentStatusShiftee(double enrollmentStatusShiftee) {
		this.enrollmentStatusShiftee = enrollmentStatusShiftee;
	}

	public double getEnrollmentStatusTransferee() {
		return enrollmentStatusTransferee;
	}

	public void setEnrollmentStatusTransferee(double enrollmentStatusTransferee) {
		this.enrollmentStatusTransferee = enrollmentStatusTransferee;
	}
}
