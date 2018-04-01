package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicSectionDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;


public class AcademicSectionDAO  extends DAOBase{
	private static final long serialVersionUID = 1L;

	private String qryAcademicSectionAdd = "ACADEMIC_SECTION_ADD";
	private String qryAcademicSectionUpdate = "ACADEMIC_SECTION_UPDATE";
	private String qryAcademicSectionDelete = "ACADEMIC_SECTION_DELETE";
	private String qryAcademicSectionList = "ACADEMIC_SECTION_LIST";
	private String qryAcademicSectionLastCode = "ACADEMIC_SECTION_LAST_CODE";
	private String qryAcademicSectionSearchByName = "ACADEMIC_SECTION_LIST_SEARCHBY_NAME";
	private String qryAcademicSectionListByAcademicProgramCode ="ACADEMIC_SECTION_LIST_BY_ACADEMIC_PROGRAM_CODE";
	
	
	
	public String getGeneratedCode() {
		AcademicSectionDTO section = getAcademicSectionLast();
		String code = "001";
		if (section != null) {
			int nextNum = Integer.parseInt(section.getCode()) + 1;
			code = String.valueOf(section.getAcademicProgram().getCode()) + Integer.valueOf(section.getYearLevel())
					+ StringUtil.getPadded(String.valueOf(nextNum), 2, "0", true);
		}
		return code;

	}
	
	
	
	private AcademicSectionDTO getAcademicSectionLast() {
		return (AcademicSectionDTO) getDTO(qryAcademicSectionLastCode);
	}



	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		section.setBaseDataOnInsert();
		add(conn, prepStmntList, section);	
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	private void add(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj ) {
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicSectionAdd));
			prepStmnt.setString(1, section.getCode());
			prepStmnt.setString(2, section.getAcademicProgram().getCode());
			prepStmnt.setInt(3,  section.getYearLevel());
			prepStmnt.setString(4, section.getName());
			prepStmnt.setString(5, section.getAddedBy());
			prepStmnt.setTimestamp(6, section.getAddedTimestamp());
			prepStmnt.setString(7, section.getUpdatedBy());
			prepStmnt.setTimestamp(8, section.getUpdatedTimestamp());
			prepStmnt.setString(9, section.getOldCode());
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeDelete(DTOBase obj) {
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, section);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	private void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicSectionDelete));
			prepStmnt.setInt(1, section.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		section.setBaseDataOnUpdate();
		update(conn, prepStmntList, section);	
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	private void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		AcademicSectionDTO section = (AcademicSectionDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryAcademicSectionUpdate));
			prepStmnt.setString(1, section.getCode() );
			prepStmnt.setString(2, section.getAcademicProgram().getCode());
			prepStmnt.setInt(3,  section.getYearLevel());
			prepStmnt.setString(4, section.getName());
			prepStmnt.setString(5, section.getAddedBy());
			prepStmnt.setTimestamp(6, section.getAddedTimestamp());
			prepStmnt.setString(7, section.getUpdatedBy());
			prepStmnt.setTimestamp(8, section.getUpdatedTimestamp());
			prepStmnt.setString(9, section.getOldCode());
			prepStmnt.setInt(10, section.getId());
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<DTOBase> getAcademicSectionList() {
		return getDTOList(qryAcademicSectionList);
	}
	
	public List<DTOBase> getAcademicSectionLastCode() {
		return getDTOList(qryAcademicSectionLastCode);
	}
	
	public List<DTOBase> getQryAcademicSectionListByAcademicProgramCode() {
		return getDTOList(qryAcademicSectionListByAcademicProgramCode);
	}
	
//search
	public List<DTOBase> getQryAcademicSectionSearchByName(String searchValue) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%" + searchValue + "%");
		return getDTOList(qryAcademicSectionSearchByName, paramList );
	}


	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		AcademicSectionDTO section = new AcademicSectionDTO();
		
		section.setId((Integer) getDBVal(resultSet, "id"));
		section.setCode((String) getDBVal(resultSet, "code"));
		section.getAcademicProgram().setCode((String) getDBVal(resultSet, "academic_program_code"));
		section.setYearLevel((Integer) getDBVal(resultSet, "year_level"));
		section.setName((String) getDBVal(resultSet, "name"));
		section.setOldCode((String) getDBVal(resultSet, "old_code"));
		section.setDisplayText(section.getName());
		return section;
	}

	
}
