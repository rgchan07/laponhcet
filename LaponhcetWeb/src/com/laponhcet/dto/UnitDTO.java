package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class UnitDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_UNIT = "SESSION_UNIT";
	public static final String SESSION_UNIT_LIST = "SESSION_UNIT_LIST";
	public static final String SESSION_UNIT_PAGINATION = "PAGINATION_UNIT_LIST";
	
	private String name;
	
	public UnitDTO() {
		super();
		name= "";
	}
	
	public UnitDTO getUnit() {
		UnitDTO unit = new UnitDTO();
		unit.setCode(super.getCode());
		unit.setAddedBy(super.getAddedBy());
		unit.setAddedTimestamp(super.getAddedTimestamp());
		unit.setUpdatedBy(super.getUpdatedBy());
		unit.setUpdatedTimestamp(super.getUpdatedTimestamp());
		
		unit.setId(this.getId());
		unit.setName(this.getName());
		unit.setDisplayText(unit.getName());
		return unit;
	}
	
	public String[] getTableData() {
		//make last column an code for table data that needs to be updated or deleted
		return new String[] {getCode(), getName(), getCode() };
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
