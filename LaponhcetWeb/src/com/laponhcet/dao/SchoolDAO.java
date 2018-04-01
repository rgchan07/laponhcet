package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class SchoolDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private final String qrySchoolAdd = "SCHOOL_ADD";
	private final String qrySchoolUpdate = "SCHOOL_UPDATE";
	private final String qrySchoolDelete = "SCHOOL_DELETE";
	private final String qrySchoolLast = "SCHOOL_LAST";
	private final String qrySchoolByName = "SCHOOL_BY_NAME";
	private final String qrySchoolList = "SCHOOL_LIST";
	private final String qrySchoolListSearchByName = "SCHOOL_LIST_SEARCHBY_NAME";


	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SchoolDTO school = (SchoolDTO) obj;
		school.setCode(getGeneratedCode());
		school.setBaseDataOnInsert();
		add(conn, prepStmntList, school);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SchoolDTO school = (SchoolDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySchoolAdd));
			prepStmnt.setString(1, school.getCode());
			prepStmnt.setString(2, school.getName());
			prepStmnt.setString(3, school.getAddress1());
			prepStmnt.setString(4, school.getAddress2());
			prepStmnt.setString(5, school.getCity().getCode());
			prepStmnt.setString(6, school.getRegistrarOIC());
			prepStmnt.setString(7, school.getWebsite());
			prepStmnt.setString(8, school.getContactNumber());
			prepStmnt.setString(9, school.getAddedBy());
			prepStmnt.setTimestamp(10, school.getAddedTimestamp());
			prepStmnt.setString(11, school.getUpdatedBy());
			prepStmnt.setTimestamp(12, school.getUpdatedTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	public String getGeneratedCode() {
		SchoolDTO school = getSchoolLast();
		String code = "00001"; //default school code
		if(school != null) {
			int nextNum = Integer.parseInt(school.getCode()) + 1; 
			code =  StringUtil.getPadded(String.valueOf(nextNum), 5, "0", true);
		}
		return code;
	}

	public SchoolDTO getSchoolLast() {
	    return (SchoolDTO) getDTO(qrySchoolLast);
	}
	
	@Override
	public void executeDelete(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SchoolDTO school = (SchoolDTO) obj;
		delete(conn, prepStmntList, school);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SchoolDTO school = (SchoolDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySchoolDelete));
			prepStmnt.setInt(1, school.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SchoolDTO school = (SchoolDTO) obj;
		school.setBaseDataOnUpdate();
		update(conn, prepStmntList, school);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SchoolDTO school = (SchoolDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySchoolUpdate));
			prepStmnt.setString(1, school.getName());
			prepStmnt.setString(2, school.getAddress1());
			prepStmnt.setString(3, school.getAddress2());
			prepStmnt.setString(4, school.getCity().getCode());
			prepStmnt.setString(5, school.getRegistrarOIC());
			prepStmnt.setString(6, school.getWebsite());
			prepStmnt.setString(7, school.getContactNumber());
			prepStmnt.setString(8, school.getUpdatedBy());
			prepStmnt.setTimestamp(9, school.getUpdatedTimestamp());
			prepStmnt.setInt(10, school.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public SchoolDTO getSchoolByName(String name) {
		return (SchoolDTO) getDTO(qrySchoolByName, name);
	}

	public List<DTOBase> getSchoolList() {
		return getDTOList(qrySchoolList);
	}
	
	public List<DTOBase> getSchoolListSearchByName(String name) {
		return getDTOList(qrySchoolListSearchByName, "%" + name + "%");
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		SchoolDTO school = new SchoolDTO();
		school.setId((Integer) getDBVal(resultSet, "id"));
		school.setCode((String) getDBVal(resultSet, "code"));
		school.setName((String) getDBVal(resultSet, "name"));
		school.setAddress1((String) getDBVal(resultSet, "address1"));
		school.setAddress2((String) getDBVal(resultSet, "address2"));
		school.getCity().setCode((String) getDBVal(resultSet, "city_code"));
		school.setRegistrarOIC((String) getDBVal(resultSet, "registrar_oic"));
		school.setWebsite((String) getDBVal(resultSet, "website"));
		school.setContactNumber((String) getDBVal(resultSet, "contact_number"));
		school.setDisplayText(school.getName());
		return school;
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
