package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.MessageSMSDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DAOConnectorUtil;

public class MessageSMSDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	//SMS Outbox
	private String qrySMSOutboxAdd = "SMS_OUTBOX_ADD";

	protected void add2(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySMSOutboxAdd));
			prepStmnt.setString(1, messageSMS.getMessageCode());
			prepStmnt.setString(2, messageSMS.getCpNumber());
			prepStmnt.setString(3, messageSMS.getMessage());
			prepStmnt.setInt(4, messageSMS.getPriority());
			prepStmnt.setInt(5, messageSMS.getGroupNum());
			prepStmnt.setBoolean(6, messageSMS.isSent());
			prepStmnt.setString(7, messageSMS.getAddedBy());
			prepStmnt.setTimestamp(8, messageSMS.getAddedTimestamp());
			prepStmnt.setString(9, messageSMS.getUpdatedBy());
			prepStmnt.setTimestamp(10, messageSMS.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public void executeAddList2(List<DTOBase> messageSMSList) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		for(DTOBase messageSMSObj: messageSMSList) {
			MessageSMSDTO messageSMS = (MessageSMSDTO) messageSMSObj;
			add2(conn, prepStmntList, messageSMS);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	//SMS Outbox
	
	private String qryMessageSMSAdd = "MESSAGE_SMS_ADD";
	private String qryMessageSMSUpdate = "MESSAGE_SMS_UPDATE";
	private String qryMessageSMSDelete = "MESSAGE_SMS_DELETE";
	private String qryMessageSMSListByIsSentLimit = "MESSAGE_SMS_LIST_BY_ISSENTLIMIT";
	private String qryMessageSMSDeleteByIsSent = "MESSAGE_SMS_DELETE_BY_ISSENT";
	
	public void deleteByIsSent(boolean isSent) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageSMSDeleteByIsSent));
			prepStmnt.setBoolean(1, isSent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		add(conn, prepStmntList, messageSMS);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageSMSAdd));
			prepStmnt.setString(1, messageSMS.getMessageCode());
			prepStmnt.setString(2, messageSMS.getCpNumber());
			prepStmnt.setString(3, messageSMS.getMessage());
			prepStmnt.setInt(4, messageSMS.getPriority());
			prepStmnt.setInt(5, messageSMS.getGroupNum());
			prepStmnt.setBoolean(6, messageSMS.isSent());
			prepStmnt.setString(7, messageSMS.getAddedBy());
			prepStmnt.setTimestamp(8, messageSMS.getAddedTimestamp());
			prepStmnt.setString(9, messageSMS.getUpdatedBy());
			prepStmnt.setTimestamp(10, messageSMS.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public void executeAddList(List<DTOBase> smsList) {
		
	}
	
	
	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		messageSMS.setBaseDataOnUpdate();
		update(conn, prepStmntList, messageSMS);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageSMSUpdate));
			prepStmnt.setBoolean(1, messageSMS.isSent());
			prepStmnt.setString(2, messageSMS.getUpdatedBy());
			prepStmnt.setTimestamp(3, messageSMS.getUpdatedTimestamp());	
			prepStmnt.setInt(4, messageSMS.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public List<DTOBase> getMessageSMSListByIsSentGroupNum(boolean isSent, int limit) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(isSent);
		paramList.add(limit);
		return getDTOList(qryMessageSMSListByIsSentLimit, paramList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		MessageSMSDTO messageSMS = new MessageSMSDTO();
		messageSMS.setId((Integer) getDBVal(resultSet, "id"));
		messageSMS.setMessageCode((String) getDBVal(resultSet, "message_code"));
		messageSMS.setCpNumber((String) getDBVal(resultSet, "cp_number"));
		messageSMS.setMessage((String) getDBVal(resultSet, "message"));
		messageSMS.setPriority((Integer) getDBVal(resultSet, "priority"));
		messageSMS.setGroupNum((Integer) getDBVal(resultSet, "group_num"));
		messageSMS.setSent((Boolean) getDBVal(resultSet, "is_sent"));
		return messageSMS;
	}

	@Override
	public void executeDeleteList(List<DTOBase> list) {
		// TODO Auto-generated method stub
		
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageSMSDTO messageSMS = (MessageSMSDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageSMSDelete));
			prepStmnt.setInt(1, messageSMS.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> messageSMSList) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		for(DTOBase messageSMSObj: messageSMSList) {
			MessageSMSDTO messageSMS = (MessageSMSDTO) messageSMSObj;
			messageSMS.setSent(true);
			update(conn, prepStmntList, messageSMS);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	//One Facekeeper
	public static void main(String[] a) {
		ActionResponse actionResponse = null;
		MessageSMSDAO messageSMSDAOFrom = new MessageSMSDAO();
		messageSMSDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_FACEKEEPER1);

		List<DTOBase> messageSMSList = messageSMSDAOFrom.getMessageSMSListByIsSentGroupNum(false, 50);
		if(messageSMSList.size() >= 1) {
			System.out.println("Processing " + messageSMSList.size() + " records to Internet SMS Server");
			MessageSMSDAO messageSMSDAOTo = new MessageSMSDAO();
			messageSMSDAOTo.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_INTERNET);
			messageSMSDAOTo.executeAddList2(messageSMSList);
			actionResponse = (ActionResponse) messageSMSDAOTo.getResult().get(ActionResponse.SESSION_ACTION_RESPONSE);
			if(actionResponse.isSuccess()) {
				messageSMSDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_FACEKEEPER1);
				messageSMSDAOFrom.executeUpdateList(messageSMSList);
				System.out.println("Sent " + messageSMSList.size() + " to SMS Server");
			}
		}		
	}
}
