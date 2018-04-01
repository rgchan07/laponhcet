package com.laponhcet.action.eventparticipant;

import com.laponhcet.dao.EventParticipantDAO;
import com.laponhcet.dto.EventParticipantDTO;
import com.mytechnopal.base.ActionBase;

public class AddEventParticipantConfirmAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		EventParticipantDTO eventParticipant = (EventParticipantDTO) getSessionAttribute(EventParticipantDTO.SESSION_EVENT_PARTICIPANT);
		String eventCode = getRequestString("txtEventCode");
		String userCode = sessionInfo.getCurrentUser().getCode();
		eventParticipant.getEventDTO().setCode(eventCode);
		eventParticipant.getUserDTO().setCode(userCode);
		setSessionLinkOnConfirm();
		new EventParticipantDAO().executeAdd(eventParticipant);;
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
	
}
