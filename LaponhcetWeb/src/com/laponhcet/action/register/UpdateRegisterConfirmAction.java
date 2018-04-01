package com.laponhcet.action.register;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateRegisterConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		RegisterDTO register = (RegisterDTO) getSessionAttribute(RegisterDTO.SESSION_REGISTER);
		Pagination pagination = (Pagination) getSessionAttribute(RegisterDTO.SESSION_REGISTER_PAGINATION);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), register);
		DTOUtil.replaceObjById(pagination.getRecordList(), register);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(RegisterDTO.SESSION_REGISTER, new RegisterDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
