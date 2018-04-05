package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class DiscountTypePercentageDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_DISCOUNT_TYPE_PERCENTAGE = "SESSION_DISCOUNT_TYPE_PERCENTAGE";
	public static final String[] AY_SEMESTER_RB_CRITERIA_LIST = new String[] {"AY", "Semester"};
	public static final String RB_CRITERIA_AY_CODE = "0";
	public static final String RB_CRITERIA_SEMESTER_CODE = "1";
	
	private String discountTypeCode;
	private FeeDTO fee;
	private double percent;
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	
	public DiscountTypePercentageDTO() {
		super();
		discountTypeCode = "";
		fee = new FeeDTO();
		percent = 0d;
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		
		
	}
	
	public DiscountTypePercentageDTO getDiscountTypePercentage() {
		DiscountTypePercentageDTO discountTypePercentage = new DiscountTypePercentageDTO();
		discountTypePercentage.setDiscountTypeCode(this.discountTypeCode);
		discountTypePercentage.setFee(this.fee);
		discountTypePercentage.setPercent(this.percent);
		discountTypePercentage.setAcademicYear(academicYear);
		discountTypePercentage.setSemester(semester);
		return discountTypePercentage;
	}

	public String getDiscountTypeCode() {
		return discountTypeCode;
	}

	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	public FeeDTO getFee() {
		return fee;
	}

	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
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
}
