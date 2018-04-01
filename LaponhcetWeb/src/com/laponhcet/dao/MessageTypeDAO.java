package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.MessageTypeDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class MessageTypeDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryMessageTypeAdd = "MESSAGE_TYPE_ADD";
	private String qryMessageTypeUpdate = "MESSAGE_TYPE_UPDATE";
	private String qryMessageTypeDelete = "MESSAGE_TYPE_DELETE";
	private String qryMessageTypeByCode = "MESSAGE_TYPE_BY_CODE";
	private String qryMessageTypeByDescription = "MESSAGE_TYPE_BY_DESCRIPTION";
	private String qryMessageTypeList = "MESSAGE_TYPE_LIST";
	private String qryMessageTypeLast = "MESSAGE_TYPE_LAST";
	private String qryMessageTypeListSearchByDescription = "MESSAGE_TYPE_LIST_SEARCHBY_DESCRIPTION";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		messageType.setCode(getGeneratedCode());
		messageType.setBaseDataOnInsert();
		add(conn, prepStmntList, messageType);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	public String getGeneratedCode(){
		MessageTypeDTO messageType = getMessageTypeLast();
		String code = "1"; //default Message Type code
		if(messageType != null) {
			int nextNum = Integer.parseInt(messageType.getCode()) + 1; 
			code =  String.valueOf(nextNum);
		}
		return code;
	}
	
	public MessageTypeDTO getMessageTypeLast(){
		return (MessageTypeDTO) getDTO(qryMessageTypeLast);
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageTypeAdd));
			prepStmnt.setString(1, messageType.getCode());
			prepStmnt.setString(2, messageType.getDescription());
			prepStmnt.setString(3, messageType.getAddedBy());
			prepStmnt.setTimestamp(4, messageType.getAddedTimestamp());
			prepStmnt.setString(5, messageType.getUpdatedBy());
			prepStmnt.setTimestamp(6, messageType.getUpdatedTimestamp());	
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
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, messageType);
	}

	@SuppressWarnings("unchecked")
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageTypeDelete));	
			prepStmnt.setInt(1, messageType.getId());
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
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		messageType.setBaseDataOnUpdate();
		update(conn, prepStmntList, messageType);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageTypeDTO messageType = (MessageTypeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageTypeUpdate));
			prepStmnt.setString(1, messageType.getCode());
			prepStmnt.setString(2, messageType.getDescription());
			prepStmnt.setString(3, messageType.getUpdatedBy());
			prepStmnt.setTimestamp(4, messageType.getUpdatedTimestamp());	
			prepStmnt.setInt(5, messageType.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public MessageTypeDTO getMessageTypeByCode(String code) {
		return (MessageTypeDTO) getDTO(qryMessageTypeByCode, code);
	}
	
	
	public List<DTOBase> getMessageTypeList() {
		return getDTOList(qryMessageTypeList);
	}
	
	public List<DTOBase> getMessageTypeListSearchByDescription(String searchValue) {
		return getDTOList(qryMessageTypeListSearchByDescription, "%" + searchValue + "%");
	}
	
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		MessageTypeDTO messageType = new MessageTypeDTO();
		messageType.setId((Integer) getDBVal(resultSet, "id"));
		messageType.setCode((String) getDBVal(resultSet, "code"));
		messageType.setDescription((String) getDBVal(resultSet, "description"));
		messageType.setAddedBy((String) getDBVal(resultSet, "added_by"));
		messageType.setAddedTimestamp((Timestamp) getDBVal(resultSet, "added_timestamp"));
		messageType.setUpdatedBy((String) getDBVal(resultSet, "updated_by"));
		messageType.setAddedTimestamp((Timestamp) getDBVal(resultSet, "updated_timestamp"));
		messageType.setDisplayText(messageType.getDescription());
		return messageType;
	}

	public MessageTypeDTO getMessageTypeByDescription(String description) {
		return (MessageTypeDTO) getDTO(qryMessageTypeByDescription, description);
	}
}
