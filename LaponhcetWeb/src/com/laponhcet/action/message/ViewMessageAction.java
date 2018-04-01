package com.laponhcet.action.message;

import com.laponhcet.dto.MessageDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.util.DTOUtil;

public class ViewMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	protected void setSessionVars() {
		Pagination pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
		int id = getRequestInt("txtSelectedRecord");
		MessageDTO message = (MessageDTO) DTOUtil.getObjById(pagination.getCurrentPageRecordList(), id);
		setSessionBeforeTrans(MessageDTO.SESSION_MESSAGE, message.getMessage());
	}
}

