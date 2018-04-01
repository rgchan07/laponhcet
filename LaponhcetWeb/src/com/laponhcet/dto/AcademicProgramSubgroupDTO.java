package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class AcademicProgramSubgroupDTO extends DTOBase {
    private static final long serialVersionUID = 1L;
   
    public static final String SESSION_ACADEMIC_PROGRAM_SUBGROUP = "SESSION_ACADEMIC_PROGRAM_SUBGROUP";
    public static final String SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST = "SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST";
    public static final String SESSION_ACADEMIC_PROGRAM_SUBGROUP_PAGINATION = "SESSION_ACADEMIC_PROGRAM_SUBGROUP_PAGINATION";
   
    private String name;

    public AcademicProgramSubgroupDTO() {
		super();
		this.name = "";
    }
    
    public AcademicProgramSubgroupDTO getAcademicProgramSubgroup() {
		AcademicProgramSubgroupDTO academicProgramSubgroup = new AcademicProgramSubgroupDTO();
		academicProgramSubgroup.setId(super.getId());
		academicProgramSubgroup.setCode(super.getCode());
		academicProgramSubgroup.setName(this.name);
		return academicProgramSubgroup;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
