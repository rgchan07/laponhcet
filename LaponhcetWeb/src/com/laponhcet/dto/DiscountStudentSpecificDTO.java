package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class DiscountStudentSpecificDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_DISCOUNT = "SESSION_DISCOUNT";
	public static final String SESSION_DISCOUNT_LIST = "SESSION_DISCOUNT_LIST";
	public static final String SESSION_DISCOUNT_PAGINATION = "SESSION_DISCOUNT_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Student"};
	
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	private StudentDTO student;
	private DiscountTypeDTO discountType;
	private double amount;
	private String remark;
	
	public DiscountStudentSpecificDTO() {
		super();
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		student = new StudentDTO();
		discountType = new DiscountTypeDTO();
		amount = 0d;
		remark = "";
	}

	public DiscountStudentSpecificDTO getDiscount() {
		DiscountStudentSpecificDTO discount = new DiscountStudentSpecificDTO();
		discount.setId(super.getId());
		discount.setCode(super.getCode());
		discount.setAcademicYear(this.academicYear);
		discount.setSemester(this.semester);
		discount.setStudent(this.student);
		discount.setDiscountType(this.discountType);
		discount.setAmount(this.amount);
		discount.setRemark(this.remark);
		return discount;
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

	public DiscountTypeDTO getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountTypeDTO discountType) {
		this.discountType = discountType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
