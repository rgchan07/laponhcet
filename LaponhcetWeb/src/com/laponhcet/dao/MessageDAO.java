package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageSMSDTO;
import com.mysql.jdbc.Messages;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserGroupDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class MessageDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryMessageAdd = "MESSAGE_ADD";
	private String qryMessageUpdate = "MESSAGE_UPDATE";
	private String qryMessageDelete = "MESSAGE_DELETE";
	private String qryMessageByCode = "MESSAGE_BY_CODE";
	private String qryMessageList = "MESSAGE_LIST";
	private String qryMessageListSearchByType = "MESSAGE_LIST_SEARCHBY_TYPE";
	private String qryMessageListSearchByContent = "MESSAGE_LIST_SEARCHBY_CONTENT";
	private String qryMessageByMessageTypeCodeContent = "MESSAGE_BY_MESSAGETYPECODECONTENT";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		List<DTOBase> userList = new UserDAO().getUserList();
		List<DTOBase> userGroupList = new UserGroupDAO().getUserGroupList(true);
		List<DTOBase> academicProgramList = new AcademicProgramDAO().getAcademicProgramList();
		List<DTOBase> academicProgramGroupList = new AcademicProgramGroupDAO().getAcademicProgramGroupList();
		List<DTOBase> academicProgramSubgroupList = new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList();
		
		
		MessageDTO message = (MessageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		message.setCode(getGeneratedCode());
		message.setBaseDataOnInsert();
		add(conn, prepStmntList, message);
		
		String[] messageTypeCodes =  message.getMessageTypeCodes().split("~");

		// For MessageSMSDTO
		if(!StringUtil.isEmpty(message.getContentSMS())){
			for(int i = 0; i < messageTypeCodes.length; i++){
				// For UserGroup
				if(DTOUtil.getObjByCode(userGroupList, messageTypeCodes[i])!=null){
					for(DTOBase userObj: userList){
						UserDTO user = (UserDTO) userObj;
						if(user.getUserGroup().getCode().equalsIgnoreCase( messageTypeCodes[i])){
							MessageSMSDTO messageSMS = new MessageSMSDTO();
							messageSMS.setMessageCode(message.getCode());
							messageSMS.setCpNumber(user.getCpNumber());
							messageSMS.setMessage(message.getContentSMS());
							messageSMS.setPriority(message.getPriority());
							new MessageSMSDAO().executeAdd(messageSMS);
						}
					}
				// For AcademicProgram
				}else if(DTOUtil.getObjByCode(academicProgramList, messageTypeCodes[i])!=null){
					System.out.println("NOT YET DONE >> academicProgramList");
				// For AcademicProgramGroup
				}else if(DTOUtil.getObjByCode(academicProgramGroupList, messageTypeCodes[i])!=null){
					System.out.println("NOT YET DONE >> academicProgramGroupList");
				// For AcademicProgramSubgroup
				}else if(DTOUtil.getObjByCode(academicProgramSubgroupList, messageTypeCodes[i])!=null){
					System.out.println("NOT YET DONE >> academicProgramSubgroupList");
				}
			}
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	
	public String getGeneratedCode(){
		return DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "YYYYMMddKKmmss") + StringUtil.getUniqueId(0, 2);
	}
	

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageAdd));
			prepStmnt.setString(1, message.getCode());
			prepStmnt.setString(2, message.getContent());
			prepStmnt.setInt(3, message.getPriority());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "YYYY-MM-dd KK-mm-ss"));
			prepStmnt.setString(5, DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "YYYY-MM-dd KK-mm-ss"));
			prepStmnt.setString(6, message.getMessageTypeCodes());
			prepStmnt.setString(7, message.getContentSMS());
			prepStmnt.setString(8, message.getContentWebHeadline());
			prepStmnt.setString(9, message.getContentFaceKeeper());
			prepStmnt.setString(10, message.getAddedBy());
			prepStmnt.setTimestamp(11, message.getAddedTimestamp());
			prepStmnt.setString(12, message.getUpdatedBy());
			prepStmnt.setTimestamp(13, message.getUpdatedTimestamp());	
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
		MessageDTO message = (MessageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, message);
	}

	@SuppressWarnings("unchecked")
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageDelete));	
			prepStmnt.setInt(1, message.getId());
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
		MessageDTO message = (MessageDTO) obj;
		message.setBaseDataOnUpdate();
		update(conn, prepStmntList, message);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		MessageDTO message = (MessageDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryMessageUpdate));
			prepStmnt.setString(1, message.getCode());
			prepStmnt.setString(2, message.getContent());
			prepStmnt.setInt(3, message.getPriority());
			prepStmnt.setString(4, DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "yyyy-MM-dd"));
			prepStmnt.setString(5, DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "yyyy-MM-dd"));
			prepStmnt.setString(6, message.getMessageTypeCodes());
			prepStmnt.setString(7, message.getContentSMS());
			prepStmnt.setString(8, message.getContentWebHeadline());
			prepStmnt.setString(9, message.getContentFaceKeeper());
			prepStmnt.setString(10, message.getUpdatedBy());
			prepStmnt.setTimestamp(11, message.getUpdatedTimestamp());	
			prepStmnt.setInt(12, message.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public MessageDTO getMessageByCode(String code) {
		return (MessageDTO) getDTO(qryMessageByCode, code);
	}
	
	
	public List<DTOBase> getMessageList() {
		return getDTOList(qryMessageList);
	}
	
	public List<DTOBase> getMessageListSearchByType(String searchValue) {
		return getDTOList(qryMessageListSearchByType, "%" + searchValue + "%");
	}
	
	public List<DTOBase> getMessageListSearchByContent(String searchValue) {
		return getDTOList(qryMessageListSearchByContent, "%" + searchValue + "%");
	}

	public MessageDTO getMessageByMessageTypeCodeContent(MessageDTO message) {
		List<String> paramList = new ArrayList<String>();
		paramList.add(message.getMessageTypeCodes());
		paramList.add(message.getContent());
		return (MessageDTO) getDTO(qryMessageByMessageTypeCodeContent, paramList);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		MessageDTO message = new MessageDTO();
		message.setId((Integer) getDBVal(resultSet, "id"));
		message.setCode((String) getDBVal(resultSet, "code"));
		message.setContent((String) getDBVal(resultSet, "content"));
		message.setPriority((Integer) getDBVal(resultSet, "priority"));
		message.setValidTimestampStart((Timestamp) getDBVal(resultSet, "valid_timestamp_start"));
		message.setValidTimestampEnd((Timestamp) getDBVal(resultSet, "valid_timestamp_end"));
		message.setMessageTypeCodes((String) getDBVal(resultSet, "message_type_codes"));
		message.setContentSMS((String) getDBVal(resultSet, "content_sms"));
		message.setContentWebHeadline((String) getDBVal(resultSet, "content_web_headline"));
		message.setContentFaceKeeper((String) getDBVal(resultSet, "content_face_keeper"));
		message.setAddedBy((String) getDBVal(resultSet, "added_by"));
		message.setAddedTimestamp((Timestamp) getDBVal(resultSet, "added_timestamp"));
		message.setUpdatedBy((String) getDBVal(resultSet, "updated_by"));
		message.setAddedTimestamp((Timestamp) getDBVal(resultSet, "updated_timestamp"));
		message.setDisplayText(StringUtil.getShortDesc(message.getContent(), 20));
		return message;
	}

}