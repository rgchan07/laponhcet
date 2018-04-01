package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;

public class EventParticipantDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_EVENT_PARTICIPANT = "SESSION_EVENT_PARTICIPANT";
	public static final String SESSION_EVENT_PARTICIPANT_LIST = "SESSION_EVENT_PARTICIPANT_LIST";
	public static final String SESSION_EVENT_PARTICIPANT_PAGINATION = "SESSION_EVENT_PARTICIPANT_PAGINATION";
	
	private EventDTO eventDTO;
	private UserDTO userDTO;
	private String remarks;
	

	public EventParticipantDTO(){
		this.eventDTO = new EventDTO();
		this.userDTO = new UserDTO();
		this.remarks = "";
	}
	
	public EventParticipantDTO getEventParticipantDTO(){
		EventParticipantDTO eventParticipant = new EventParticipantDTO();
		eventParticipant.setEventDTO(this.eventDTO);
		eventParticipant.setUserDTO(this.userDTO);
		eventParticipant.setRemarks(this.remarks);
		return eventParticipant;
	}
	
	public EventDTO getEventDTO() {
		return eventDTO;
	}
	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
	
}
