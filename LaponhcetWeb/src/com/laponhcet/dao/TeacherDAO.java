package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class TeacherDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryTeacherAdd = "TEACHER_ADD";
	private String qryTeacherAddProfilePict = "TEACHER_ADD_PROFILE_PICT";
	private String qryTeacherUpdate = "TEACHER_UPDATE";
	private String qryTeacherUpdateProfilePict = "TEACHER_UPDATE_PROFILE_PICT";
	private String qryTeacherDelete = "TEACHER_DELETE";
	private String qryTeacherDeleteProfilePict = "TEACHER_DELETE_PROFILE_PICT";
	private String qryTeacherLastCode = "TEACHER_LAST_CODE";
	private String qryTeacherByCode = "TEACHER_BY_CODE";
	private String qryTeacherList = "TEACHER_LIST";
	
	@Override
	public void executeAdd(DTOBase obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		teacher.getUserGroup().setCode(UserGroupDTO.USER_GROUP_TEACHER_CODE);
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		teacher.setCode(getGeneratedCode());
		teacher.setUserName(teacher.getCode());
		teacher.setPassword(StringUtil.getUniqueId(3, 3));
		teacher.setActive(true);
		teacher.setBaseDataOnInsert();
		//User
		new UserDAO().add(conn, prepStmntList, teacher);
		//Teacher Add PP
		addPP(conn, prepStmntList, teacher);
		//Teacher
		add(conn, prepStmntList, teacher);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherAdd));
			prepStmnt.setString(1, teacher.getCode());
			prepStmnt.setString(2, teacher.getProgramGraduated());
			prepStmnt.setString(3, teacher.getAcademicProgramCodes());
			prepStmnt.setString(4, teacher.getAddedBy());
			prepStmnt.setTimestamp(5, teacher.getAddedTimestamp());
			prepStmnt.setString(6, teacher.getUpdatedBy());
			prepStmnt.setTimestamp(7, teacher.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	protected void addPP(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherAddProfilePict));
			prepStmnt.setString(1, teacher.getCode());
			prepStmnt.setString(2, teacher.getProfilePict());
			prepStmnt.setString(3, teacher.getAddedBy());
			prepStmnt.setTimestamp(4, teacher.getAddedTimestamp());
			prepStmnt.setString(5, teacher.getUpdatedBy());
			prepStmnt.setTimestamp(6, teacher.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public String getGeneratedCode() {
		TeacherDTO teacher = getTeacherLast();
		String code = "P00001"; //default teacher code
		if(teacher != null) {
			int nextNum = Integer.parseInt(teacher.getCode().substring(1)) + 1; 
			code = "P" + StringUtil.getPadded(String.valueOf(nextNum), 5, "0", true);
		}
		return code;
	}
	
	private TeacherDTO getTeacherLast() {
		return (TeacherDTO) getDTO(qryTeacherLastCode);
	}
	
	public List<DTOBase> getTeacherList() {
		return getDTOList(qryTeacherList);
	}
	
	public TeacherDTO getTeacherByCode(String code) {
		return (TeacherDTO) getDTO(qryTeacherByCode, code);
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		//User
		UserDTO user = new UserDAO().getUserByCode(teacher.getCode());
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		//Delete User
		new UserDAO().delete(conn, prepStmntList, user);
		//Delete TeacherPP
		//deletePP(conn, prepStmntList, obj);
		//Delete Teacher
		delete(conn, prepStmntList, teacher);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherDelete));	
			prepStmnt.setInt(1, teacher.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	protected void deletePP(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherDeleteProfilePict));	
			prepStmnt.setInt(1, teacher.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		TeacherDTO teacher = (TeacherDTO) obj;
		teacher.setBaseDataOnUpdate();
		//User
		new UserDAO().update(conn, prepStmntList, teacher);
		//UpdatePP
		//updatePP(conn, prepStmntList, obj);
		//Teacher
		update(conn, prepStmntList, teacher);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherUpdate));
			prepStmnt.setString(1, teacher.getProgramGraduated());
			prepStmnt.setString(2, teacher.getAcademicProgramCodes());
			prepStmnt.setString(3, teacher.getUpdatedBy());
			prepStmnt.setTimestamp(4, teacher.getUpdatedTimestamp());		
			prepStmnt.setInt(5, teacher.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	protected void updatePP(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		TeacherDTO teacher = (TeacherDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryTeacherUpdateProfilePict));
			prepStmnt.setString(1, teacher.getProfilePict());
			prepStmnt.setString(2, teacher.getUpdatedBy());
			prepStmnt.setTimestamp(3, teacher.getUpdatedTimestamp());	
			prepStmnt.setInt(4, teacher.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		TeacherDTO teacher = new TeacherDTO();
		teacher.setId((Integer) getDBVal(resultSet, "id"));
		teacher.setCode((String) getDBVal(resultSet, "user_code"));
		teacher.setProgramGraduated((String) getDBVal(resultSet, "program_graduated"));
		teacher.setAcademicProgramCodes((String) getDBVal(resultSet, "academic_program_codes"));
		teacher.setDisplayText(teacher.getName(false, false, true));
		return teacher;
	}

}
