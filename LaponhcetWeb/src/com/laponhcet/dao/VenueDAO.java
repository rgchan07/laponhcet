package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class VenueDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryVenueAdd = "VENUE_ADD";
	private String qryVenueUpdate = "VENUE_UPDATE";
	private String qryVenueDelete = "VENUE_DELETE";
	private String qryVenueLastCode = "VENUE_LAST_CODE";
	private String qryVenueByCode = "VENUE_BY_CODE";
	private String qryVenueByName = "VENUE_BY_NAME";
	private String qryVenueByNameLocation = "VENUE_BY_NAMELOCATION";
	private String qryVenueList = "VENUE_LIST";
	private String qryVenueListSearchByName = "VENUE_LIST_SEARCHBY_NAME";
	private String qryVenueListSearchByLocation = "VENUE_LIST_SEARCHBY_LOCATION";
	
	@Override
	public void executeAdd(DTOBase obj) {
		VenueDTO venue = (VenueDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		venue.setCode(getGeneratedCode());
		venue.setBaseDataOnInsert();
		add(conn, prepStmntList, venue);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		VenueDTO venue = (VenueDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryVenueAdd));
			prepStmnt.setString(1, venue.getCode());
			prepStmnt.setString(2, venue.getName());
			prepStmnt.setString(3, venue.getLocation());
			prepStmnt.setString(4, venue.getAcademicProgramCodes());
			prepStmnt.setInt(5, venue.getConcurrentSession());
			prepStmnt.setInt(6, venue.getMaxPax());
			prepStmnt.setInt(7, venue.getColumnCount());
			prepStmnt.setString(8, venue.getRowColumn());
			prepStmnt.setString(9, venue.getAddedBy());
			prepStmnt.setTimestamp(10, venue.getAddedTimestamp());
			prepStmnt.setString(11, venue.getUpdatedBy());
			prepStmnt.setTimestamp(12, venue.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public String getGeneratedCode() {
		VenueDTO venue = getVenueLast();
		String code = "001"; //default venue code
		if(venue != null) {
			int nextNum = Integer.parseInt(venue.getCode()) + 1; 
			code = StringUtil.getPadded(String.valueOf(nextNum), 2, "0", true);
		}
		return code;
	}
	
	private VenueDTO getVenueLast() {
		return (VenueDTO) getDTO(qryVenueLastCode);
	}
	
	public VenueDTO getVenueByName(String name) {
		return (VenueDTO) getDTO(qryVenueByName, name);
	}
	
	public VenueDTO getVenueByNameLocation(String name, String location) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(name);
		paramList.add(location );
		return (VenueDTO) getDTO(qryVenueByNameLocation, paramList);
	}
	
	public List<DTOBase> getVenueList() {
		return getDTOList(qryVenueList);
	}
	
	public List<DTOBase> getVenueListSearchByName(String searchValue) {
		return getDTOList(qryVenueListSearchByName, "%" + searchValue + "%");
	}

	public List<DTOBase> getVenueListSearchByLocation(String searchValue) {
		return getDTOList(qryVenueListSearchByLocation, "%" + searchValue + "%");
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase obj) {
		VenueDTO venue = (VenueDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, venue);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		VenueDTO venue = (VenueDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryVenueDelete));	
			prepStmnt.setInt(1, venue.getId());
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
		VenueDTO venue = (VenueDTO) obj;
		venue.setBaseDataOnUpdate();
		update(conn, prepStmntList, obj);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		VenueDTO venue = (VenueDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryVenueUpdate));
			prepStmnt.setString(1, venue.getName());
			prepStmnt.setString(2, venue.getLocation());
			prepStmnt.setString(3, venue.getAcademicProgramCodes());
			prepStmnt.setInt(4, venue.getConcurrentSession());
			prepStmnt.setInt(5, venue.getMaxPax());
			prepStmnt.setInt(6, venue.getColumnCount());
			prepStmnt.setString(7, venue.getRowColumn());
			prepStmnt.setString(8, venue.getUpdatedBy());
			prepStmnt.setTimestamp(9, venue.getUpdatedTimestamp());	
			prepStmnt.setInt(10, venue.getId());
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
		VenueDTO venue = new VenueDTO();
		venue.setId((Integer) getDBVal(resultSet, "id"));
		venue.setCode((String) getDBVal(resultSet, "code"));
		venue.setName((String) getDBVal(resultSet, "name"));
		venue.setLocation((String) getDBVal(resultSet, "location"));
		venue.setAcademicProgramCodes((String) getDBVal(resultSet, "academic_program_codes"));
		venue.setConcurrentSession((Integer) getDBVal(resultSet, "concurrent_session"));
		venue.setMaxPax((Integer) getDBVal(resultSet, "max_pax"));
		venue.setColumnCount((Integer) getDBVal(resultSet, "column_count"));
		venue.setRowColumn((String) getDBVal(resultSet, "row_column"));
		venue.setDisplayText(venue.getName());
		return venue;
	}
}
