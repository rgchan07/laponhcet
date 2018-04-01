package com.laponhcet.action.fee;

import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddFeeConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(FeeDTO.SESSION_FEE, new FeeDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
