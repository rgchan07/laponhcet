package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.CityDTO;

public class FeeResidentialSpecificDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FEERESIDENTIALSPECIFIC = "SESSION_FEERESIDENTIALSPECIFIC";
	public static final String SESSION_FEERESIDENTIALSPECIFIC_LIST = "SESSION_FEERESIDENTIALSPECIFIC_LIST";
	public static final String SESSION_FEERESIDENTIALSPECIFIC_PAGINATION = "SESSION_FEERESIDENTIALSPECIFIC_PAGINATION";
	
	public static final String BAGO_CITY_CODE = "01040";
	
	private AcademicYearDTO academicYear;
	private SemesterDTO semester;
	private CityDTO city;
	private FeeDTO fee;
	private double amount;
	
	public FeeResidentialSpecificDTO() {
		super();
		academicYear = new AcademicYearDTO();
		semester = new SemesterDTO();
		city = new CityDTO();
		fee = new FeeDTO();
		amount = 0d;
	}
	
	public FeeResidentialSpecificDTO getFeeResidentialSpecific() {
		FeeResidentialSpecificDTO feeResidentialSpecific = new FeeResidentialSpecificDTO();
		feeResidentialSpecific.setId(super.getId());
		feeResidentialSpecific.setAcademicYear(this.academicYear);
		feeResidentialSpecific.setSemester(this.semester);
		feeResidentialSpecific.setCity(this.city);
		feeResidentialSpecific.setFee(this.fee);
		feeResidentialSpecific.setAmount(this.amount);
		return feeResidentialSpecific;
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

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
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
