package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dao.MessageTypeDAO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageTypeDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00012", "U00007", "U00013"}, new String[] {"U00008", "U00014", "U00016"}, new String[] {"U00009", "U00015", "U00017"}, "U00010", "U00011");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit() && !sessionInfo.isPreviousLinkView()) {
			pagination = new Pagination();
			List<DTOBase> messageList = new MessageDAO().getMessageList();
			pagination.setName(MessageDTO.SESSION_MESSAGE_PAGINATION);
			pagination.setSearchCriteria(MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Code","Type", "Message", ""});
			pagination.setColumnWidthList(new String[] {"13","70","17"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(messageList);
			pagination.setRecordList(messageList);
			pagination.setAjaxResultDetailsList(new String[] {"type", "message", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});

			setSessionAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST, new MessageTypeDAO().getMessageTypeList());
			setSessionAttribute(MessageDTO.SESSION_MESSAGE, new MessageDTO());
		}
		else{
			pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
		}
		MessageUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST));
		setSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION, pagination);
	}
	
}
