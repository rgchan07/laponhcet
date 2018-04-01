package com.laponhcet.action.message;

import com.laponhcet.dto.MessageDTO;
import com.mytechnopal.base.ActionBase;

public class DeleteMessageSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		MessageDTO message = (MessageDTO)getSelectedPaginationObjById(MessageDTO.SESSION_MESSAGE_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionBeforeTrans(MessageDTO.SESSION_MESSAGE, message);
	}
}
