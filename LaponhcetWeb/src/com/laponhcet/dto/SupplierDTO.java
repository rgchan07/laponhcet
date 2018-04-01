package com.laponhcet.dto;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.dto.UserDTO;

public class SupplierDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_SUPPLIER = "SESSION_SUPPLIER";
	public static final String SESSION_SUPPLIER_LIST = "SESSION_SUPPLIER_LIST";
	public static final String SESSION_SUPPLIER_PAGINATION = "PAGINATION_SUPPLIER_LIST";
	
	public static String[] SEARCHCRITERIA = new String[] {"Code","Name"};
	
	private int termDays;
	private String remark;
	private String companyName;
	
	public SupplierDTO() {
		super();
		super.getCityPermanent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getCityPresent().setCode(SettingsUtil.DEFAULT_CITY);
		super.getReligion().setCode(SettingsUtil.DEFAULT_RELIGION);
		termDays = 30;
		remark = "";
	}
	
	public SupplierDTO getSupplier() {
	    SupplierDTO supplier = new SupplierDTO();

	    supplier.setUserName(super.getUserName());
		supplier.setPassword(super.getPassword());
		supplier.setUserGroup(super.getUserGroup());
		supplier.setLastName(super.getLastName());
		supplier.setFirstName(super.getFirstName());
		supplier.setMiddleName(super.getMiddleName());
		supplier.setPrefixName(super.getPrefixName());
		supplier.setSuffixName(super.getSuffixName());
		supplier.setOtherTitle(super.getOtherTitle());
		supplier.setStreetPermanent(super.getStreetPermanent());
		supplier.setBarangayPermanent(super.getBarangayPermanent());
		supplier.setCityPermanent(super.getCityPermanent());
		supplier.setStreetPresent(super.getStreetPresent());
		supplier.setBarangayPresent(super.getBarangayPresent());
		supplier.setCityPresent(super.getCityPresent());
		supplier.setBirthDate(super.getBirthDate());
		supplier.setGender(super.getGender());
		supplier.setReligion(super.getReligion());
		supplier.setMaritalStatus(super.getMaritalStatus());
		supplier.setLandlineNumber(super.getLandlineNumber());
		supplier.setCpNumber(super.getCpNumber());
		supplier.setEmailAddress(super.getEmailAddress());
		supplier.setActive(super.isActive());
		supplier.setLastLoginTimestamp(super.getLastLoginTimestamp());
		supplier.setLastLoginIPAddress(super.getLastLoginIPAddress());
		
		supplier.setId(this.getId());
		supplier.setCode(this.getCode());
		supplier.setTermDays(this.termDays);
		supplier.setRemark(this.remark);
		supplier.setCompanyName(this.companyName);
		supplier.setDisplayText(supplier.getName(false, true, false));
		return supplier;
	}
	
	public String[] getTableData() {
		//make last column an code for table data that needs to be updated or deleted
		return new String[] {getCode(), getLastName(), getFirstName(), getMiddleName(), String.valueOf(getTermDays()), getCode()};
	}
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getTermDays() {
	    return termDays;
	}
	public void setTermDays(int termDays) {
	    this.termDays = termDays;
	}
	public String getRemark() {
	    return remark;
	}
	public void setRemark(String remark) {
	    this.remark = remark;
	}
}
