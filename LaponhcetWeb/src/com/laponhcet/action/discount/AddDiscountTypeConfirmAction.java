package com.laponhcet.action.discount;

import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddDiscountTypeConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, new DiscountTypeDAO(), DAOBase.DAO_ACTION_ADD);
	}
}