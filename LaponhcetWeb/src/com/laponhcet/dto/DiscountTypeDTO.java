package com.laponhcet.dto;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.base.DTOBase;

public class DiscountTypeDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_DISCOUNT_TYPE = "SESSION_DISCOUNT_TYPE";
	public static final String SESSION_DISCOUNT_TYPE_LIST = "SESSION_DISCOUNT_TYPE_LIST";
	public static final String SESSION_DISCOUNT_TYPE_PAGINATION = "SESSION_DISCOUNT_TYPE_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name"};
	
	private String schoolTerm;
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	private String name;
	private boolean isPercentage;
	private List<DTOBase> discountTypePercentageList;
	
	public DiscountTypeDTO() {
		super();
		schoolTerm = SettingsUtil.SCHOOL_TERM_SEMESTER;
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		name = "";
		isPercentage = false;
		discountTypePercentageList = new ArrayList<DTOBase>();
	}

	public DiscountTypeDTO getDiscountType() {
		DiscountTypeDTO discountType = new DiscountTypeDTO();
		discountType.setId(super.getId());
		discountType.setCode(super.getCode());
		discountType.setAcademicYear(this.academicYear);
		discountType.setSemester(this.semester);
		discountType.setName(this.name);
		discountType.setPercentage(this.isPercentage);
		discountType.setDiscountTypePercentageList(this.discountTypePercentageList);
		return discountType;
	}
	
	public String getSchoolTerm() {
		return schoolTerm;
	}

	public void setSchoolTerm(String schoolTerm) {
		this.schoolTerm = schoolTerm;
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

	public String getDisplayText() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPercentage() {
		return isPercentage;
	}

	public void setPercentage(boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

	public List<DTOBase> getDiscountTypePercentageList() {
		return discountTypePercentageList;
	}

	public void setDiscountTypePercentageList(
		List<DTOBase> discountTypePercentageList) {
		this.discountTypePercentageList = discountTypePercentageList;
	}
}
