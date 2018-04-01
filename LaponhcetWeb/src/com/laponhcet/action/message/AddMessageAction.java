package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.AcademicProgramSubgroupDAO;
import com.laponhcet.dao.MessageTypeDAO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicProgramSubgroupDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.laponhcet.dto.MessageSMSDTO;
import com.laponhcet.dto.MessageTypeDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserGroupDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;


public class AddMessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00012", "U00007", "U00013"}, new String[] {"U00008", "U00014", "U00016"}, new String[] {"U00009", "U00015", "U00017"}, "U00010", "U00011");

		if((!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkDeleteSubmit())) {
			setSessionAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST, new MessageTypeDAO().getMessageTypeList());
			setSessionAttribute(MessageSMSDTO.SESSION_MESSAGE_SMS, new MessageSMSDTO());
			setSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL, new MessageIndividualDTO());
			setSessionBeforeTrans(MessageDTO.SESSION_MESSAGE, new MessageDTO());

			Pagination pagination = new Pagination();
			List<DTOBase> userList = new UserDAO().getUserList();
			pagination.setName(UserDTO.SESSION_USER_PAGINATION);
			pagination.setColumnNameList(new String[] {"Recipient Name"});
			pagination.setColumnWidthList(new String[] {"100"});
			pagination.setRecordPerPage(userList.size());
			pagination.setAjaxLinkCode("U00054");
			pagination.setRecordListUnfiltered(userList);
			pagination.setRecordList(userList);
			pagination.setAjaxResultDetailsList(new String[] {"name"});
			setPaginationRecord(pagination);
			setSessionAttribute(UserDTO.SESSION_USER_PAGINATION, pagination);
			setSessionAttribute(UserDTO.SESSION_USER, new UserDTO());
		}
		else{
			Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
			setPaginationRecord(pagination);
		}		
	}

	private void setPaginationRecord(Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			UserDTO user = (UserDTO) dto;
			user.setPaginationRecord(new String[]{  MessageUtil.getAddRecipientButton(user.getCode()) + user.getName(true, false, false)});
		}
	}	
}
