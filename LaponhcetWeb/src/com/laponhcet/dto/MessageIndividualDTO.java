package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;

public class MessageIndividualDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_MESSAGE_INDIVIDUAL = "SESSION_MESSAGE_INDIVIDUAL";
	public static final String SESSION_MESSAGE_INDIVIDUAL_LIST = "SESSION_MESSAGE_INDIVIDUAL_LIST";
	public static final String SESSION_MESSAGE_INDIVIDUAL_PAGINATION = "SESSION_MESSAGE_INDIVIDUAL_PAGINATION";
	
	private String messageCode;
	private UserDTO user;
	private boolean isSeen;
	
	public MessageIndividualDTO() {
		super();
		this.messageCode = "";
		this.user = new UserDTO();
		this.isSeen = false;
	}

	public MessageIndividualDTO getMessageIndividual() {
		MessageIndividualDTO messageIndividual = new MessageIndividualDTO();
		messageIndividual.setId(super.getId());
		messageIndividual.setMessageCode(messageCode);
		messageIndividual.setUser(this.user);
		messageIndividual.setSeen(this.isSeen);
		return messageIndividual;
	}
	
	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
}
