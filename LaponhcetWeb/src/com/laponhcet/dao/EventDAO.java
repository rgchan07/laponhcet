package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laponhcet.dto.EventDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class EventDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryEventAdd = "EVENT_ADD";
	private String qryEventUpdate = "EVENT_UPDATE";
	private String qryEventDelete = "EVENT_DELETE";
	private String qryEventList = "EVENT_LIST";
	private String qryEventLastCode = "EVENT_LAST_CODE";
	
	@Override
	public void executeAdd(DTOBase obj) {
		EventDTO event = (EventDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		event.setBaseDataOnInsert();
		event.setCode(getGeneratedCode());
		add(conn, prepStmntList, event);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList,true));
	}
	
	public String getGeneratedCode() {
		EventDTO event =  getEventLast();
		String code = "001"; //default supplier code
		if(event != null) {
			int nextNum = Integer.parseInt(event.getCode()) + 1; 
			code =  StringUtil.getPadded(String.valueOf(nextNum), 5, "0", true);
		}
		return code;
	}
	
	private EventDTO getEventLast() {
		return (EventDTO) getDTO(qryEventLastCode);
	}
	
	public void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		EventDTO event = (EventDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryEventAdd));
			prepStmnt.setString(1, event.getCode());
			prepStmnt.setString(2, event.getDescription());
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(event.getStart(), "yyyy-MM-dd"));
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(event.getStart(), "yyyy-MM-dd"));
			prepStmnt.setString(5, event.getVenue());
			prepStmnt.setString(6, event.getSpeaker());
			prepStmnt.setString(7, event.getStatus());
			prepStmnt.setString(8, event.getAddedBy());
			prepStmnt.setTimestamp(9, event.getAddedTimestamp());
			prepStmnt.setString(10, event.getUpdatedBy());
			prepStmnt.setTimestamp(11, event.getUpdatedTimestamp());
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
		EventDTO event = (EventDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, event);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList, true));
	}
	
	public void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		EventDTO event = (EventDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryEventDelete));
			prepStmnt.setInt(1, event.getId());
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
		EventDTO event = (EventDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		event.setBaseDataOnUpdate();
		update(conn, prepStmntList, event);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList, true));
	}
	
	
	public void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		EventDTO event = (EventDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryEventUpdate));
			prepStmnt.setString(1, event.getDescription());
			prepStmnt.setString(2, DateTimeUtil.getDateTimeToStr(event.getStart(), "yyyy-MM-dd"));
			prepStmnt.setString(3, DateTimeUtil.getDateTimeToStr(event.getStart(), "yyyy-MM-dd"));
			prepStmnt.setString(4, event.getVenue());
			prepStmnt.setString(5, event.getSpeaker());
			prepStmnt.setString(6, event.getStatus());
			prepStmnt.setString(7, event.getUpdatedBy());
			prepStmnt.setTimestamp(8, event.getUpdatedTimestamp());
			prepStmnt.setInt(9, event.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<DTOBase> getEventList() {
		return getDTOList(qryEventList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		EventDTO event = new EventDTO();
		event.setId((Integer) getDBVal(resultSet, "id"));
		event.setCode((String) getDBVal(resultSet, "code"));
		event.setDescription((String) getDBVal(resultSet, "description"));
		event.setStart((Date) getDBVal(resultSet, "start"));
		event.setEnd((Date) getDBVal(resultSet, "end"));
		event.setVenue((String) getDBVal(resultSet, "venue"));
		event.setSpeaker((String) getDBVal(resultSet, "speaker"));
		event.setStatus((String) getDBVal(resultSet, "status"));
		return event;
	}
}
