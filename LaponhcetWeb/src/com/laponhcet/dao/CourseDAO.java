package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class CourseDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryCourseAdd = "COURSE_ADD";
	private String qryCourseUpdate = "COURSE_UPDATE";
	private String qryCourseDelete = "COURSE_DELETE";
	private String qryCourseByCode = "COURSE_BY_CODE";
	private String qryCourseList = "COURSE_LIST";
	private String qryCourseListSearchByAcademicProgramCode = "COURSE_LIST_SEARCHBY_ACADEMICPROGRAMCODE";
	private String qryCourseListSearchByCodeDescription = "COURSE_LIST_SEARCHBY_CODEDESCRIPTION";
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		CourseDTO course = (CourseDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		course.setBaseDataOnInsert();
		add(conn, prepStmntList, course);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseDTO course = (CourseDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseAdd));
			prepStmnt.setString(1, course.getCode());
			prepStmnt.setString(2, course.getDescription());
			prepStmnt.setDouble(3, course.getCreditUnit());
			prepStmnt.setDouble(4, course.getPayUnit());
			prepStmnt.setDouble(5, course.getLectureHour());
			prepStmnt.setDouble(6, course.getLaboratoryHour());
			prepStmnt.setString(7, course.getCourseGroup().getCode());
			prepStmnt.setString(8, course.getAcademicProgramCodes());
			prepStmnt.setString(9, course.getAddedBy());
			prepStmnt.setTimestamp(10, course.getAddedTimestamp());
			prepStmnt.setString(11, course.getUpdatedBy());
			prepStmnt.setTimestamp(12, course.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase obj) {
		CourseDTO course = (CourseDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, course);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	@SuppressWarnings("unchecked")
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseDTO course = (CourseDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseDelete));	
			prepStmnt.setInt(1, course.getId());
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
		CourseDTO course = (CourseDTO) obj;
		course.setBaseDataOnUpdate();
		update(conn, prepStmntList, course);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		CourseDTO course = (CourseDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryCourseUpdate));
			prepStmnt.setString(1, course.getCode());
			prepStmnt.setString(2, course.getDescription());
			prepStmnt.setDouble(3, course.getCreditUnit());
			prepStmnt.setDouble(4, course.getPayUnit());			
			prepStmnt.setDouble(5, course.getLectureHour());
			prepStmnt.setDouble(6, course.getLaboratoryHour());
			prepStmnt.setString(7, course.getCourseGroup().getCode());
			prepStmnt.setString(8, course.getAcademicProgramCodes());
			prepStmnt.setString(9, course.getUpdatedBy());
			prepStmnt.setTimestamp(10, course.getUpdatedTimestamp());	
			prepStmnt.setInt(11, course.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public CourseDTO getCourseByCode(String code) {
		return (CourseDTO) getDTO(qryCourseByCode, code);
	}
	
	
	public List<DTOBase> getCourseList() {
		return getDTOList(qryCourseList);
	}
	
	public List<DTOBase> getCourseListSearchByAcademicProgramCode(String searchValue) {
		return getDTOList(qryCourseListSearchByAcademicProgramCode, searchValue);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		CourseDTO course = new CourseDTO();
		course.setId((Integer) getDBVal(resultSet, "id"));
		course.setCode((String) getDBVal(resultSet, "code"));
		course.setDescription((String) getDBVal(resultSet, "description"));
		course.setCreditUnit((Double) getDBVal(resultSet, "credit_unit"));
		course.setPayUnit((Double) getDBVal(resultSet, "pay_unit"));
		course.setLectureHour((Double) getDBVal(resultSet, "lecture_hour"));
		course.setLaboratoryHour((Double) getDBVal(resultSet, "laboratory_hour"));
		course.getCourseGroup().setCode((String) getDBVal(resultSet, "course_group_code"));
		course.setAcademicProgramCodes((String) getDBVal(resultSet, "academic_program_codes"));
		course.setAddedBy((String) getDBVal(resultSet, "added_by"));
		course.setAddedTimestamp((Timestamp) getDBVal(resultSet, "added_timestamp"));
		course.setUpdatedBy((String) getDBVal(resultSet, "updated_by"));
		course.setAddedTimestamp((Timestamp) getDBVal(resultSet, "updated_timestamp"));
		course.setDisplayText(course.getCode() + "(" + course.getDescription() + ")");
		return course;
	}

	public List<DTOBase> getCourseListSearchByCodeDescription(String searchValue) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%" + searchValue + "%");
		paramList.add("%" + searchValue + "%");
		return getDTOList(qryCourseListSearchByCodeDescription, paramList);
	}
	
	public static void main(String[] a) {
		CourseDAO courseDAO = new CourseDAO();
		System.out.println(courseDAO.getCourseList().size());
	}
}
