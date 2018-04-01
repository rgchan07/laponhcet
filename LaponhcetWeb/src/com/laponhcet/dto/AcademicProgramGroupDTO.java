package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class AcademicProgramGroupDTO extends DTOBase {

    private static final long serialVersionUID = 1L;

    public static final String SESSION_ACADEMIC_PROGRAM_GROUP = "SESSION_ACADEMIC_PROGRAM_GROUP";
    public static final String SESSION_ACADEMIC_PROGRAM_GROUP_LIST = "SESSION_ACADEMIC_PROGRAM_GROUP_LIST";
    public static final String SESSION_ACADEMIC_PROGRAM_GROUP_PAGINATION = "SESSION_ACADEMIC_PROGRAM_GROUP_PAGINATION";
	
    public static final String ACADEMIC_PROGRAM_GROUP_CODE_BASICEDUCATION = "B";
    public static final String ACADEMIC_PROGRAM_GROUP_CODE_VOCATIONAL = "V";
    public static final String ACADEMIC_PROGRAM_GROUP_CODE_COLLEGE = "C";
    public static final String ACADEMIC_PROGRAM_GROUP_CODE_GRADUATESCHOOL = "G";
    
    private String name;

    public AcademicProgramGroupDTO() {
    	super();
    	this.name = "";
    }
    
    public AcademicProgramGroupDTO getAcademicProgramGroup() {
		AcademicProgramGroupDTO academicProgramGroup = new AcademicProgramGroupDTO();
		academicProgramGroup.setId(super.getId());
		academicProgramGroup.setCode(super.getCode());
		academicProgramGroup.setName(this.name);
		return academicProgramGroup;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
