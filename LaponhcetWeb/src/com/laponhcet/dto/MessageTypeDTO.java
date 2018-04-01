package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class MessageTypeDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_MESSAGE_TYPE = "SESSION_MESSAGE_TYPE";
	public static final String SESSION_MESSAGE_TYPE_LIST = "SESSION_MESSAGE_TYPE_LIST";
	public static final String SESSION_MESSAG_TYPEE_PAGINATION = "SESSION_MESSAG_TYPEE_PAGINATION";

	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Description"};
	
	private String description;

	public MessageTypeDTO() {
		super();
		this.description = "";
	}
	
	public MessageTypeDTO getMessageType() {
		MessageTypeDTO messageType = new MessageTypeDTO();
		messageType.setId(super.getId());
		messageType.setCode(super.getCode());
		messageType.setDescription(this.description);
		return messageType;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
