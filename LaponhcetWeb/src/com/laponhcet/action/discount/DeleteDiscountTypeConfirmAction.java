package com.laponhcet.action.discount;

import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteDiscountTypeConfirmAction  extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
		  
		  protected void setSessionVars() {
			  if(actionResponse.isSuccess()) {
		  Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
			pagination.updateList((DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE), DAOBase.DAO_ACTION_DELETE);
		  }
			  sessionInfo.setCurrentLink(sessionInfo.getListLink());
		  }
		  
		  protected void executeLogic() {
			execute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, new DiscountTypeDAO(), DAOBase.DAO_ACTION_DELETE);
		  }
}


