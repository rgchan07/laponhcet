package com.laponhcet.dto;

import java.util.List;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;


public class SchoolDTO extends DTOBase {
    private static final long serialVersionUID = 1L;

    public static final String SESSION_SCHOOL = "SESSION_SCHOOL";
    public static final String SESSION_SCHOOL_LIST = "SESSION_SCHOOL_LIST";
    public static final String SESSION_SCHOOL_PAGINATION = "SESSION_SCHOOL_PAGINATION";
    
    private String name;
    private String address1;
    private String address2;
    private CityDTO city;
    private String registrarOIC;
    private String website;
    private String contactNumber;
    
    public SchoolDTO() {
	super();
	this.name = "";
	this.address1 = "";
	this.address2 = "";
	this.city = new CityDTO();
	this.registrarOIC = "";
	this.website = "";
	this.contactNumber = "";
    }

    public SchoolDTO getSchoolDTO() {
	SchoolDTO school = new SchoolDTO();
	school.setId(super.getId());
	school.setCode(super.getCode());
	school.setName(this.name);
	school.setAddress1(this.address1);
	school.setAddress2(this.address2);
	school.setCity(this.city);
	school.setRegistrarOIC(this.registrarOIC);
	school.setWebsite(this.website);
	school.setContactNumber(this.contactNumber);
	return school;
    }
    
    public String[] getTableData(List<DTOBase> cityList) {
	//make last column an id for table data that needs to be updated or deleted
	return new String[] {getName(), getAddress1(), getAddress2(), StringUtil.isEmpty(getCity().getCode())?"":((CityDTO)DTOUtil.getObjByCode(cityList, getCity().getCode())).getName(), getRegistrarOIC(), String.valueOf(super.getId())};
}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getRegistrarOIC() {
        return registrarOIC;
    }

    public void setRegistrarOIC(String registrarOIC) {
        this.registrarOIC = registrarOIC;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
