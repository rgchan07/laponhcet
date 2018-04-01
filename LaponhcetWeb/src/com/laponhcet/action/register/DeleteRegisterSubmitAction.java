package com.laponhcet.action.register;

import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteRegisterSubmitAction extends RegisterAction {

	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		Pagination pagination = (Pagination) getSessionAttribute(RegisterDTO.SESSION_REGISTER_PAGINATION);
		DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
		RegisterDTO register = (RegisterDTO) obj;
		setSessionBeforeTrans(RegisterDTO.SESSION_REGISTER, register.getRegister());
	}
}
