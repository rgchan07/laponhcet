package com.laponhcet.action.merchandise;

import com.laponhcet.dao.MerchandiseDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddMerchandiseConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(MerchandiseDTO.SESSION_MERCHANDISE, new MerchandiseDAO(), DAOBase.DAO_ACTION_ADD);
	}	
}
