package com.laponhcet.action.message;

import com.laponhcet.dto.MessageDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			MessageDTO message = (MessageDTO)getSelectedPaginationObjById(MessageDTO.SESSION_MESSAGE_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionAttribute(MessageDTO.SESSION_MESSAGE + "_ORIG", message);
			setSessionBeforeTrans(MessageDTO.SESSION_MESSAGE, message.getMessage());
		}
	}
}
