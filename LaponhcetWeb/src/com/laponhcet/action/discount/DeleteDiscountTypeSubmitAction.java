package com.laponhcet.action.discount;

import com.laponhcet.dto.DiscountTypeDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteDiscountTypeSubmitAction extends DiscountTypeAction{
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		// is previous link from the list
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
			setSessionBeforeTrans(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, discountType.getDiscountType());
		}
	}

}
 