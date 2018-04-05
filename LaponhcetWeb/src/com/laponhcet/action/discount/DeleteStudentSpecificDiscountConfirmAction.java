package com.laponhcet.action.discount;

import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class DeleteStudentSpecificDiscountConfirmAction  extends ActionBase{
private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
		  
		  protected void setSessionVars() {
			  if(actionResponse.isSuccess()) {
		  Pagination pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
			pagination.updateList((DiscountStudentSpecificDTO) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT), DAOBase.DAO_ACTION_DELETE);
		  }
			  sessionInfo.setCurrentLink(sessionInfo.getListLink());
		  }
		  
		  protected void executeLogic() {
			execute(DiscountStudentSpecificDTO.SESSION_DISCOUNT, new DiscountStudentSpecificDAO(), DAOBase.DAO_ACTION_DELETE);
		  }
}




