package com.laponhcet.action.discount;

import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class ViewDiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
			discountType.setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByDiscountTypeCode(discountType.getCode()));
			setSessionBeforeTrans(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, discountType.getDiscountType());
		}
	}
}

