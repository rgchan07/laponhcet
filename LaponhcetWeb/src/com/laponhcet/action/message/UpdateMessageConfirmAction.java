package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageTypeDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateMessageConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	@SuppressWarnings("unchecked")
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
			pagination.updateList((MessageDTO) getSessionAttribute(MessageDTO.SESSION_MESSAGE), DAOBase.DAO_ACTION_UPDATE);
			MessageUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(MessageDTO.SESSION_MESSAGE, new MessageDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
