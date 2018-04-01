package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.laponhcet.dto.AcademicProgramSubgroupDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AcademicProgramSubgroupDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private final String qryAcademicProgramSubgroupList = "ACADEMIC_PROGRAM_SUBGROUP_LIST";
	private final String qryAcademicProgramSubgroupByCode = "ACADEMIC_PROGRAM_SUBGROUP_BY_CODE";
	
	@Override
	public void executeAdd(DTOBase obj) {
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase obj) {
	}
	
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdate(DTOBase obj) {
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
	}
	
	public AcademicProgramSubgroupDTO getAcademicProgramSubgroupByName(String code) {
		return (AcademicProgramSubgroupDTO) getDTO(qryAcademicProgramSubgroupByCode, code);
	}

	public List<DTOBase> getAcademicProgramSubgroupList() {
		return getDTOList(qryAcademicProgramSubgroupList);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		AcademicProgramSubgroupDTO academicProgramSubgroup = new AcademicProgramSubgroupDTO();
		academicProgramSubgroup.setId((Integer) getDBVal(resultSet, "id"));
		academicProgramSubgroup.setCode((String) getDBVal(resultSet, "code"));
		academicProgramSubgroup.setName((String) getDBVal(resultSet, "name"));
		academicProgramSubgroup.setDisplayText(academicProgramSubgroup.getName());
		return academicProgramSubgroup;
	}

}
