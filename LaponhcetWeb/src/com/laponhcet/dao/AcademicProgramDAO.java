package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class AcademicProgramDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private final String qryAcademicProgramAdd = "ACADEMIC_PROGRAM_ADD";
	private final String qryAcademicProgramUpdate = "ACADEMIC_PROGRAM_UPDATE";
	private final String qryAcademicProgramDelete = "ACADEMIC_PROGRAM_DELETE";
	private final String qryAcademicProgramLast = "ACADEMIC_PROGRAM_LAST";
	private final String qryAcademicProgramByName = "ACADEMIC_PROGRAM_BY_NAME";
	private final String qryAcademicProgramByCode = "ACADEMIC_PROGRAM_BY_CODE";
	private final String qryAcademicProgramList = "ACADEMIC_PROGRAM_LIST";
	private final String qryAcademicProgramListUniqueName = "ACADEMIC_PROGRAM_LIST_UNIQUENAME";
	private final String qryAcademicProgramListSearByCodeName = "ACADEMIC_PROGRAM_LIST_SEARCHBYCODENAME";


	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		academicProgram.setBaseDataOnInsert();
		add(conn, prepStmntList, academicProgram);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicProgramAdd));
			prepStmnt.setString(1, academicProgram.getCode());
			prepStmnt.setString(2, academicProgram.getAcademicProgramGroup().getCode());
			prepStmnt.setString(3, academicProgram.getAcademicProgramSubgroup().getCode());
			prepStmnt.setString(4, academicProgram.getName());
			prepStmnt.setString(5, academicProgram.getMajor());
			prepStmnt.setString(6, academicProgram.getDescription());
			prepStmnt.setInt(7, academicProgram.getGraduationYearLevel());
			prepStmnt.setString(8, academicProgram.getHeadUser().getCode());
			prepStmnt.setString(9, academicProgram.getLogo());
			prepStmnt.setString(10, academicProgram.getAddedBy());
			prepStmnt.setTimestamp(11, academicProgram.getAddedTimestamp());
			prepStmnt.setString(12, academicProgram.getUpdatedBy());
			prepStmnt.setTimestamp(13, academicProgram.getUpdatedTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public AcademicProgramDTO getAcademicProgramLast() {
	    return (AcademicProgramDTO) getDTO(qryAcademicProgramLast);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeDelete(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		delete(conn, prepStmntList, academicProgram);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicProgramDelete));
			prepStmnt.setInt(1, academicProgram.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		academicProgram.setBaseDataOnUpdate();
		update(conn, prepStmntList, academicProgram);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicProgramUpdate));
			prepStmnt.setString(1, academicProgram.getCode());
			prepStmnt.setString(2, academicProgram.getAcademicProgramGroup().getCode());
			prepStmnt.setString(3, academicProgram.getAcademicProgramSubgroup().getCode());
			prepStmnt.setString(4, academicProgram.getName());
			prepStmnt.setString(5, academicProgram.getMajor());
			prepStmnt.setString(6, academicProgram.getDescription());
			prepStmnt.setInt(7, academicProgram.getGraduationYearLevel());
			prepStmnt.setString(8, academicProgram.getHeadUser().getCode());
			prepStmnt.setString(9, academicProgram.getLogo());
			prepStmnt.setString(10, academicProgram.getUpdatedBy());
			prepStmnt.setTimestamp(11, academicProgram.getUpdatedTimestamp());
			prepStmnt.setInt(12, academicProgram.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public AcademicProgramDTO getAcademicProgramByName(String name) {
		return (AcademicProgramDTO) getDTO(qryAcademicProgramByName, name);
	}
	
	public AcademicProgramDTO getAcademicProgramByCode(String code) {
		return (AcademicProgramDTO) getDTO(qryAcademicProgramByCode, code);
	}

	public List<DTOBase> getAcademicProgramList() {
		return getDTOList(qryAcademicProgramList);
	}
	
	public List<DTOBase> getAcademicProgramListSearchByCodeName(String searchValue) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%" + searchValue + "%");
		paramList.add("%" + searchValue + "%");
		return getDTOList(qryAcademicProgramListSearByCodeName, paramList);
	}
	
	public List<DTOBase> getAcademicProgramListUniqueName() {
		return getDTOList(qryAcademicProgramListUniqueName);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		AcademicProgramDTO academicProgram = new AcademicProgramDTO();
		academicProgram.setId((Integer) getDBVal(resultSet, "id"));
		academicProgram.setCode((String) getDBVal(resultSet, "code"));
		academicProgram.getAcademicProgramGroup().setCode((String)getDBVal(resultSet, "academic_program_group_code"));
		academicProgram.getAcademicProgramSubgroup().setCode((String)getDBVal(resultSet, "academic_program_subgroup_code"));
		academicProgram.setName((String) getDBVal(resultSet, "name"));
		academicProgram.setMajor((String)getDBVal(resultSet, "major"));
		academicProgram.setDescription((String)getDBVal(resultSet, "description"));
		academicProgram.setGraduationYearLevel((Integer)getDBVal(resultSet, "graduation_year_level"));
		academicProgram.getHeadUser().setCode((String)getDBVal(resultSet, "head_user_code"));
		academicProgram.setLogo((String)getDBVal(resultSet, "logo"));
		if(StringUtil.isEmpty(academicProgram.getMajor())) {
			academicProgram.setDisplayText(academicProgram.getName());
		}
		else {
			academicProgram.setDisplayText(academicProgram.getName() + "-" + academicProgram.getMajor());
		}
		return academicProgram;
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

}
