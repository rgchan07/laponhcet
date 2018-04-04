package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.StaffDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class StaffDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryStaffAdd = "STAFF_ADD";
	private String qryStaffUpdate = "STAFF_UPDATE";
	private String qryStaffDelete = "STAFF_DELETE";
	private String qryStaffAddProfilePict = "STAFF_ADD_PROFILE_PICT";
	private String qryStaffUpdateProfilePict = "STAFF_UPDATE_PROFILE_PICT";
	private String qryStaffDeleteProfilePict = "STAFF_DELETE_PROFILE_PICT";
	private String qryStaffLastCode = "STAFF_LAST_CODE";
	private String qryStaffByCode = "STAFF_BY_CODE";
	private String qryStaffByName  = "STAFF_BY_NAME";
	private String qryStaffList = "STAFF_LIST";
	
		
	public String getGeneratedCode() {
		StaffDTO staff = getStaffLast();
		String code = "S00001"; //default staff code
		if(staff != null) {
			int nextNum = Integer.parseInt(staff.getCode().substring(1)) + 1;
			code = "S" + StringUtil.getPadded(String.valueOf(nextNum), 5, "0", true);
		}
		return code;
	}
		private StaffDTO getStaffLast() {
		return (StaffDTO) getDTO(qryStaffLastCode);
	}
	
		
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		StaffDTO staff = (StaffDTO) obj;
		staff.getUserGroup().setCode(UserGroupDTO.USER_GROUP_STAFF_CODE);
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		staff.setCode(getGeneratedCode());
		staff.setUserName(staff.getCode());
		staff.setPassword(StringUtil.getUniqueId(3, 3));
		staff.setActive(true);
		staff.setBaseDataOnInsert();
		
		//User
		new UserDAO().add(conn, prepStmntList, staff);	
		//staff PP
		addStaffProfilePict(conn, prepStmntList, staff);
		//Staff
		add(conn, prepStmntList, staff);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
				
	}
	
	private void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffAdd));
			prepStmnt.setString(1, staff.getCode());
			prepStmnt.setString(2, staff.getProgramGraduated());
			prepStmnt.setString(3, staff.getJobRole());
			prepStmnt.setString(4, staff.getAssignedOffice());
			prepStmnt.setString(5, staff.getAcademicProgramCodes());
			prepStmnt.setString(6, staff.getAddedBy());
			prepStmnt.setTimestamp(7, staff.getAddedTimestamp());
			prepStmnt.setString(8, staff.getUpdatedBy());	
			prepStmnt.setTimestamp(9, staff.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	private void addStaffProfilePict(Connection conn, List<PreparedStatement> prepStmntList, Object obj){
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffAddProfilePict));
			prepStmnt.setString(1, staff.getCode());
			prepStmnt.setString(2, staff.getProfilePict());
			prepStmnt.setString(3, staff.getAddedBy());
			prepStmnt.setTimestamp(4, staff.getAddedTimestamp());
			prepStmnt.setString(5, staff.getUpdatedBy());
			prepStmnt.setTimestamp(6, staff.getUpdatedTimestamp());	
		} catch (SQLException e) {
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
		StaffDTO staff = (StaffDTO) obj;
		//User
		UserDTO user = new UserDAO().getUserByCode(staff.getCode());
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		// delete user
		new UserDAO().delete(conn, prepStmntList, user);
		//delete staff pp
		deleteStaffProfilePict(conn, prepStmntList, staff);
		// delete staff
		delete(conn, prepStmntList, staff);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	private void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffDelete));	
			prepStmnt.setInt(1, staff.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		
	}
	
	private void deleteStaffProfilePict(Connection conn, List<PreparedStatement> prepStmntList, Object obj){
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffDeleteProfilePict));
			prepStmnt.setString(1, staff.getCode());
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
		StaffDTO staff = (StaffDTO) obj;
		staff.setBaseDataOnUpdate();

		// User
		new UserDAO().update(conn, prepStmntList, staff);
		//Update staff PP
		updateStudentProfilePict(conn, prepStmntList, staff);
		// Staff
		update(conn, prepStmntList, staff);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	private void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffUpdate));
			prepStmnt.setString(1, staff.getCode());
			prepStmnt.setString(2, staff.getProgramGraduated());
			prepStmnt.setString(3, staff.getJobRole());
			prepStmnt.setString(4, staff.getAssignedOffice());
			prepStmnt.setString(5, staff.getAcademicProgramCodes());
			prepStmnt.setString(6, staff.getUpdatedBy());	
			prepStmnt.setTimestamp(7, staff.getUpdatedTimestamp());		
			prepStmnt.setInt(8, staff.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		
	}
	
	private void updateStudentProfilePict(Connection conn, List<PreparedStatement> prepStmntList, Object obj){
		StaffDTO staff = (StaffDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryStaffUpdateProfilePict));
			prepStmnt.setString(1, staff.getProfilePict());
			prepStmnt.setString(2, staff.getUpdatedBy());
			prepStmnt.setTimestamp(3, staff.getUpdatedTimestamp());	
			prepStmnt.setString(4, staff.getCode());
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
		StaffDTO staff = new StaffDTO();
		staff.setId((Integer) getDBVal(resultSet, "id"));
		staff.setCode((String) getDBVal(resultSet, "user_code"));
		staff.setProgramGraduated((String) getDBVal(resultSet, "program_graduated"));
		staff.setJobRole((String) getDBVal(resultSet, "job_role"));
		staff.setAssignedOffice((String) getDBVal(resultSet, "assigned_office"));
		staff.setAcademicProgramCodes((String) getDBVal(resultSet, "academic_program_codes"));
		staff.setDisplayText(staff.getName(true, true, true));
		return staff;
	}
	
	public StaffDTO getStaffByCode(String code) {
		return (StaffDTO) getDTO(qryStaffByCode, code);
	}
	
	public List<DTOBase> getStaffList() {
		return getDTOList(qryStaffList);
	}
	
	public StudentDTO getStaffByName(String lastName, String firstName, String middleName) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(lastName);
		paramList.add(firstName);
		paramList.add(middleName);
		return (StudentDTO) getDTO(qryStaffByName, paramList);
	}
	
	
}
