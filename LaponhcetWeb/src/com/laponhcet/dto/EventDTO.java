package com.laponhcet.dto;

import java.util.Date;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class EventDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_EVENT = "SESSION_EVENT";
	public static final String SESSION_EVENT_LIST = "SESSION_EVENT_LIST";
	public static final String SESSION_EVENT_PAGINATION = "SESSION_EVENT_PAGINATION";
	
	public static final String EVENT_STATUS_COMPLETE = "COMPLETE";
	public static final String EVENT_STATUS_NEXT_EVENT = "NEXT EVENT";
	public static final String EVENT_STATUS_UPCOMING = "UPCOMING";
		
	private String description;
	private Date start;
	private Date end;
	private String venue;
	private String speaker;
	private String status;
	
	public EventDTO(){
		super();
		this.description = "";
		this.start = DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd");
		this.end = DateTimeUtil.getStrToDateTime("1970-01-01", "yyyy-MM-dd");
		this.venue = "";
		this.speaker = "";
		this.status = "";
	}
	
	public EventDTO getEventDTO(){
		EventDTO event = new EventDTO();
		event.setCode(super.getCode());
		event.setDescription(this.description);
		event.setStart(this.start);
		event.setEnd(this.end);
		event.setVenue(this.venue);
		event.setSpeaker(this.speaker);
		event.setStatus(this.status);
		return event;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
		
	
}
