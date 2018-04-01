package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AcademicProgramGroupDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private final String qryAcademicProgramGroupList = "ACADEMIC_PROGRAM_GROUP_LIST";
	private final String qryAcademicProgramGroupByCode = "ACADEMIC_PROGRAM_GROUP_BY_CODE";
	
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
	
	public AcademicProgramGroupDTO getAcademicProgramGroupByName(String code) {
		return (AcademicProgramGroupDTO) getDTO(qryAcademicProgramGroupByCode, code);
	}

	public List<DTOBase> getAcademicProgramGroupList() {
		return getDTOList(qryAcademicProgramGroupList);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		AcademicProgramGroupDTO academicProgramGroup = new AcademicProgramGroupDTO();
		academicProgramGroup.setId((Integer) getDBVal(resultSet, "id"));
		academicProgramGroup.setCode((String) getDBVal(resultSet, "code"));
		academicProgramGroup.setName((String) getDBVal(resultSet, "name"));
		academicProgramGroup.setDisplayText(academicProgramGroup.getName());
		return academicProgramGroup;
	}

}
