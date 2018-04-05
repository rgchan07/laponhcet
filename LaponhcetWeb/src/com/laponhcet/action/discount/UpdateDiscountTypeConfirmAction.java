package com.laponhcet.action.discount;

import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateDiscountTypeConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
		DiscountTypeDTO discountType = (DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
		Pagination pagination = (Pagination) getSessionAttribute( DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
		pagination.updateList((DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE), DAOBase.DAO_ACTION_UPDATE);
		DiscountTypeUtil.setPaginationRecord(sessionInfo, pagination );
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), discountType);
		
	}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, new DiscountTypeDAO(), DAOBase.DAO_ACTION_UPDATE);
		actionResponse.constructMessage(ActionResponse.TYPE_FAIL, "fail");
	}

	
}
