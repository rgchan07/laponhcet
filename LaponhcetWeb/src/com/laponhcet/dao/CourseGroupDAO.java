package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.CourseGroupDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class CourseGroupDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryCourseGroupAdd = "COURSE_GROUP_ADD";
	private String qryCourseGroupUpdate = "COURSE_GROUP_UPDATE";
	private String qryCourseGroupDelete = "COURSE_GROUP_DELETE";
	private String qryCourseGroupByCode = "COURSE_GROUP_BY_CODE";
	private String qryCourseGroupByName = "COURSE_GROUP_BY_NAME";
	private String qryCourseGroupList = "COURSE_GROUP_LIST";
	private String qryCourseGroupLast = "COURSE_GROUP_LAST";
	private String qryCourseGroupListSearchByNameDescription = "COURSE_GROUP_LIST_SEARCHBY_NAMEDESCRIPTION";
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		courseGroup.setCode(getGeneratedCode());
		courseGroup.setBaseDataOnInsert();
		add(conn, prepStmntList, courseGroup);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseGroupAdd));
			prepStmnt.setString(1, courseGroup.getCode());
			prepStmnt.setString(2, courseGroup.getName());
			prepStmnt.setString(3, courseGroup.getDescription());
			prepStmnt.setString(4, courseGroup.getAddedBy());
			prepStmnt.setTimestamp(5, courseGroup.getAddedTimestamp());
			prepStmnt.setString(6, courseGroup.getUpdatedBy());
			prepStmnt.setTimestamp(7, courseGroup.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public String getGeneratedCode() {
		CourseGroupDTO unit =  getCourseGroupLast();
		String code = "001"; //default Course Group code
		if(unit != null) {
			int nextNum = Integer.parseInt(unit.getCode()) + 1; 
			code =  StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
		}
		return code;
	}
	
	private CourseGroupDTO getCourseGroupLast() {
		return (CourseGroupDTO) getDTO(qryCourseGroupLast);
	}
	
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase obj) {
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, courseGroup);
	}

	@SuppressWarnings("unchecked")
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseGroupDelete));	
			prepStmnt.setInt(1, courseGroup.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
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
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		courseGroup.setBaseDataOnUpdate();
		update(conn, prepStmntList, courseGroup);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseGroupDTO courseGroup = (CourseGroupDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseGroupUpdate));
			prepStmnt.setString(1, courseGroup.getCode());
			prepStmnt.setString(2, courseGroup.getName());
			prepStmnt.setString(3, courseGroup.getDescription());
			prepStmnt.setString(4, courseGroup.getUpdatedBy());
			prepStmnt.setTimestamp(5, courseGroup.getUpdatedTimestamp());	
			prepStmnt.setInt(6, courseGroup.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public CourseGroupDTO getCourseGroupByCode(String code) {
		return (CourseGroupDTO) getDTO(qryCourseGroupByCode, code);
	}
	
	
	public List<DTOBase> getCourseGroupList() {
		return getDTOList(qryCourseGroupList);
	}
	
	public List<DTOBase> getCourseGroupListSearchByNameDescription(String searchValue) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%" + searchValue + "%");
		paramList.add("%" + searchValue + "%");
		return getDTOList(qryCourseGroupListSearchByNameDescription, paramList);
	}
	
	
	public static void main(String[] args){
		List<DTOBase> list = new CourseGroupDAO().getCourseGroupListSearchByNameDescription("%E%");
		System.out.println("Size: " + list.size());
	}
	
	public CourseGroupDTO getCourseGroupByName(String name) {
		return (CourseGroupDTO) getDTO(qryCourseGroupByName, name);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		CourseGroupDTO courseGroup = new CourseGroupDTO();
		courseGroup.setId((Integer) getDBVal(resultSet, "id"));
		courseGroup.setCode((String) getDBVal(resultSet, "code"));
		courseGroup.setName((String) getDBVal(resultSet, "name"));
		courseGroup.setDescription((String) getDBVal(resultSet, "description"));
		courseGroup.setAddedBy((String) getDBVal(resultSet, "added_by"));
		courseGroup.setAddedTimestamp((Timestamp) getDBVal(resultSet, "added_timestamp"));
		courseGroup.setUpdatedBy((String) getDBVal(resultSet, "updated_by"));
		courseGroup.setAddedTimestamp((Timestamp) getDBVal(resultSet, "updated_timestamp"));
		courseGroup.setDisplayText(courseGroup.getName() + " - " + courseGroup.getDescription());
		return courseGroup;
	}
}
