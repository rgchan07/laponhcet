package com.laponhcet.action.discount;

import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.laponhcet.util.FeeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateDiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
			discountType.setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByFeeListParent(FeeUtil.getFeeListParent(new FeeDAO().getFeeList())));
			//discountType.setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByDiscountTypeCode(discountType.getCode()));
			setSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE + "_ORIG", discountType);
			setSessionBeforeTrans(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, discountType.getDiscountType());
		}
	}
}
