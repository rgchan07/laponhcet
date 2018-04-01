package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laponhcet.dto.FaceLogDTO;
import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DAOConnectorUtil;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class FaceLogDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryFaceLogAdd = "FACE_LOG_ADD";
	private String qryFaceLogUpdate = "FACE_LOG_UPDATE";
	private String qryFaceLogLatestByRFID = "FACE_LOG_LATEST_BY_RFID";
	private String qryFaceLogListByTimeLogFromAndTimeLogTo = "FACE_LOG_LIST_BY_TIMELOGFROMANDTIMELOGTO";
	private String qryFaceLogDeleteByTimeLogNotCurrentDate = "FACE_LOG_DELETE_BY_TIMELOGNOTCURRENTDATE";
	private String qryFaceLogListIsProcessLimit = "FACE_LOG_LIST_ISPROCESSLIMIT";
	
	public void deleteByNotCurrentDate() {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFaceLogDeleteByTimeLogNotCurrentDate));
			prepStmnt.setString(1, DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "yyyy-MM-dd"));
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
		FaceLogDTO faceLog = (FaceLogDTO) obj;
		faceLog.setCode(DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "yyyyMMddkkss" + StringUtil.getRight(faceLog.getUserRFID().getRfid(), 4)));
		add(conn, prepStmntList, faceLog);
		if(StringUtil.isValidCPNumber(faceLog.getUserRFID().getContactCPNumber())) {
			MessageSMSDAO messageSMSDAO = new MessageSMSDAO();
			faceLog.getMessageSMS().setAddedBy("S001");
			faceLog.getMessageSMS().setAddedTimestamp(faceLog.getTimeLog());
			faceLog.getMessageSMS().setBaseDataOnInsert();
			messageSMSDAO.add(conn, prepStmntList, faceLog.getMessageSMS());
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		FaceLogDTO faceLog = (FaceLogDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFaceLogAdd));
			prepStmnt.setString(1, faceLog.getCode());
			prepStmnt.setString(2, faceLog.getUserRFID().getCode());
			prepStmnt.setString(3, faceLog.getUserRFID().getRfid());
			prepStmnt.setTimestamp(4, faceLog.getTimeLog());
			prepStmnt.setString(5, faceLog.getPict());
			prepStmnt.setBoolean(6, faceLog.isIn());
			prepStmnt.setString(7, SettingsUtil.FACEKEEPER_LOCATION);
			prepStmnt.setBoolean(8, faceLog.isProcess());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		FaceLogDTO faceLog = (FaceLogDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFaceLogUpdate));
			prepStmnt.setBoolean(1, faceLog.isProcess());
			prepStmnt.setInt(2, faceLog.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeAddList(List<DTOBase> objList) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		for(DTOBase obj: objList) {
			add(conn, prepStmntList, obj);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdate(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateList(List<DTOBase> objList) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		for(DTOBase obj: objList) {
			FaceLogDTO faceLog = (FaceLogDTO) obj;
			faceLog.setProcess(true);
			update(conn, prepStmntList, obj);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	public FaceLogDTO getFaceLogLatestByRFID(String rfid) {
		return (FaceLogDTO) getDTO(qryFaceLogLatestByRFID, rfid);
	}
	
	public List<DTOBase> getFaceLogListByTimeLogFromAndTimeLogTo(Date timeLogFrom, Date timeLogTo) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(timeLogFrom);
		paramList.add(timeLogTo);
		return getDTOList(qryFaceLogListByTimeLogFromAndTimeLogTo, paramList);
	}
	
	public List<DTOBase> getFaceLogListByIsProcessLimit(boolean isProcess, int limit) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(isProcess);
		paramList.add(limit);
		return getDTOList(qryFaceLogListIsProcessLimit, paramList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		FaceLogDTO faceLog = new FaceLogDTO();
		faceLog.setId((Integer) getDBVal(resultSet, "id"));
		faceLog.setCode((String) getDBVal(resultSet, "code"));
		faceLog.getUserRFID().setCode((String) getDBVal(resultSet, "code"));
		faceLog.getUserRFID().setCode((String) getDBVal(resultSet, "user_code"));
		faceLog.getUserRFID().setRfid((String) getDBVal(resultSet, "rfid"));
		faceLog.setTimeLog((Timestamp) getDBVal(resultSet, "time_log"));
		faceLog.setPict((String) getDBVal(resultSet, "pict"));
		faceLog.setIn((Boolean) getDBVal(resultSet, "is_in"));
		faceLog.setLocation((String) getDBVal(resultSet, "location"));
		faceLog.setProcess((Boolean) getDBVal(resultSet, "is_process"));
		return faceLog;
	}

	public static void main(String[] a) {
		ActionResponse actionResponse = null;
		//String locationTo = locationFrom.equalsIgnoreCase(DAOConnector.SERVER_LOCATION_FACEKEEPER1)?DAOConnector.SERVER_LOCATION_FACEKEEPER2:DAOConnector.SERVER_LOCATION_FACEKEEPER1;
		FaceLogDAO faceLogDAOFrom = new FaceLogDAO();
		faceLogDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_LOCAL);
		
		List<DTOBase> faceLogList = faceLogDAOFrom.getFaceLogListByIsProcessLimit(false, 60);
		if(faceLogList.size() >= 1) {
			System.out.println("Processing " + faceLogList.size() + " records to Local Backup PC");
			FaceLogDAO faceLogDAOTo = new FaceLogDAO();
			faceLogDAOTo.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_LOCAL2);
			faceLogDAOTo.executeAddList(faceLogList);
			actionResponse = (ActionResponse) faceLogDAOTo.getResult().get(ActionResponse.SESSION_ACTION_RESPONSE);
			if(actionResponse.isSuccess()) {
				faceLogDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_LOCAL);
				faceLogDAOFrom.executeUpdateList(faceLogList);
				System.out.println("Sent " + faceLogList.size() + " records to Local Backup PC");
			}
		}
		
		
		MessageSMSDAO messageSMSDAOFrom = new MessageSMSDAO();
		messageSMSDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_LOCAL);
		
		List<DTOBase> messageSMSList = messageSMSDAOFrom.getMessageSMSListByIsSentGroupNum(false, 60);
		if(messageSMSList.size() >= 1) {
			System.out.println("Processing " + messageSMSList.size() + " records to Internet SMS Server");
			MessageSMSDAO messageSMSDAOTo = new MessageSMSDAO();
			messageSMSDAOTo.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_INTERNET);
			messageSMSDAOTo.executeAddList2(messageSMSList);
			actionResponse = (ActionResponse) messageSMSDAOTo.getResult().get(ActionResponse.SESSION_ACTION_RESPONSE);
			if(actionResponse.isSuccess()) {
				messageSMSDAOFrom.daoConnectorUtil.setServerLocation(DAOConnectorUtil.SERVER_LOCATION_LOCAL);
				messageSMSDAOFrom.executeUpdateList(messageSMSList);
				System.out.println("Sent " + messageSMSList.size() + " to SMS Server");
			}
		}	
	}
}
