package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class FeeDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FEE = "SESSION_FEE";
	public static final String SESSION_FEE_LIST = "SESSION_FEE_LIST";
	public static final String SESSION_FEE_PAGINATION = "SESSION_FEE_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "Parent"};
	
	private String codeParent;
	private String name;
	private String academicProgramCodes;
	private boolean isMandatory;
	
	public FeeDTO() {
		super();
		codeParent = "";
		name = "";
		academicProgramCodes = "";
		isMandatory = false;
	}
	
	public FeeDTO getFee() {
		FeeDTO fee = new FeeDTO();
		fee.setId(super.getId());
		fee.setCode(super.getCode());
		fee.setCodeParent(this.codeParent);
		fee.setName(this.name);
		fee.setAcademicProgramCodes(this.academicProgramCodes);
		fee.setMandatory(isMandatory);
		return fee;
	}

	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}

	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
}
