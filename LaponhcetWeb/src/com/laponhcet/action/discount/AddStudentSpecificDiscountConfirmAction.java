package com.laponhcet.action.discount;

import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddStudentSpecificDiscountConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(DiscountStudentSpecificDTO.SESSION_DISCOUNT, new DiscountStudentSpecificDAO(), DAOBase.DAO_ACTION_ADD);
	}
}