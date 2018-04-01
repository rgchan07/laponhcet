package com.laponhcet.action.register;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateRegisterStatusConfirmAction extends RegisterAction {

	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		Pagination pagination = (Pagination) getSessionAttribute(RegisterDTO.SESSION_REGISTER_PAGINATION);
		DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
		String status = getRequestString("txtRegisterStatus");
		System.out.println();
		RegisterDTO register = (RegisterDTO) obj;
		register.setStatus(status);
		setSessionLinkOnConfirm();
		
		new RegisterDAO().executeUpdateStatus(register);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), register);
		DTOUtil.replaceObjById(pagination.getRecordList(), register);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	
}
