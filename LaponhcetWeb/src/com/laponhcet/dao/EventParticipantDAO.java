package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.EventParticipantDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class EventParticipantDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryEventParticipantAdd = "EVENT_PARTICIPANT_ADD";
	private String qryEventParticipantDelete = "EVENT_PARTICIPANT_DELETE";
	private String qryEventParticipantByUserCode = "EVENT_PARTICIPANT_BY_USER_CODE";
	private String qryEventParticipantList = "EVENT_PARTICIPANT_LIST";
	
	@Override
	public void executeAdd(DTOBase obj) {
		EventParticipantDTO eventParticipant = (EventParticipantDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		eventParticipant.setBaseDataOnInsert();
		add(conn, prepStmntList, eventParticipant);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList,true));
	}
	
	public void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		EventParticipantDTO eventParticipant = (EventParticipantDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryEventParticipantAdd));
			prepStmnt.setString(1, eventParticipant.getEventDTO().getCode());
			prepStmnt.setString(2, eventParticipant.getUserDTO().getCode());
			prepStmnt.setString(3, eventParticipant.getRemarks());
			prepStmnt.setString(4, eventParticipant.getAddedBy());
			prepStmnt.setTimestamp(5, eventParticipant.getAddedTimestamp());
			prepStmnt.setString(6, eventParticipant.getUpdatedBy());
			prepStmnt.setTimestamp(7, eventParticipant.getUpdatedTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public EventParticipantDTO getEventParticipantByUserCode(String userCode){
		return (EventParticipantDTO) getDTO(qryEventParticipantByUserCode, userCode);
	}

	@Override
	public void executeDelete(DTOBase obj) {
		EventParticipantDTO eventParticipant = (EventParticipantDTO) obj;	
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, eventParticipant);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList, true));
	}
	
	public void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		EventParticipantDTO eventParticipant = (EventParticipantDTO) obj;	
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryEventParticipantDelete));
			prepStmnt.setInt(1, eventParticipant.getId());
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
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<DTOBase> getEventParticipantList() {
		return getDTOList(qryEventParticipantList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		EventParticipantDTO eventParticipant = new EventParticipantDTO();
		eventParticipant.setId((Integer) getDBVal(resultSet, "id"));
		eventParticipant.getEventDTO().setCode((String) getDBVal(resultSet, "event_code"));
		eventParticipant.getUserDTO().setCode((String) getDBVal(resultSet, "user_code"));
		eventParticipant.setRemarks((String) getDBVal(resultSet, "remarks"));
		return eventParticipant;
	}

	@Override
	public void executeUpdate(DTOBase arg0) {
		// TODO Auto-generated method stub
		
	}
}
