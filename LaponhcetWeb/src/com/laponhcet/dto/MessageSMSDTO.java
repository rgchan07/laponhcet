package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;

public class MessageSMSDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_MESSAGE_SMS = "SESSION_MESSAGE_SMS";
	public static final String SESSION_MESSAGE_SMS_LIST = "SESSION_MESSAGE_SMS_LIST";
	public static final String SESSION_MESSAGE_SMS_PAGINATION = "SESSION_MESSAGE_SMS_PAGINATION";
	
	private String messageCode;
	private String cpNumber;
	private String message;
	private int priority;
	private int groupNum;
	private boolean isSent;
	
	public MessageSMSDTO() {
		super();
		this.messageCode = "";
		this.cpNumber = "";
		this.message = "";
		this.priority = 9;
		this.groupNum = 1;
		isSent = false;
	}
	
	public MessageSMSDTO getSMSOutbox() {
		MessageSMSDTO smsOutbox = new MessageSMSDTO();
		smsOutbox.setId(super.getId());
		smsOutbox.setMessageCode(this.messageCode);
		smsOutbox.setCpNumber(this.cpNumber);
		smsOutbox.setMessage(this.message);
		smsOutbox.setPriority(this.priority);
		smsOutbox.setGroupNum(this.groupNum);
		smsOutbox.setSent(this.isSent);
		return smsOutbox;
	}
	
	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getCpNumber() {
		return cpNumber;
	}
	
	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
}
