package com.laponhcet.action.discount;

import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateStudentSpecificDiscountAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
			//DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			//DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) obj;
			//discountType.setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByDiscountTypeCode(discountType.getCode()));
			//setSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT + "_ORIG", discount);
			//setSessionBeforeTrans(DiscountStudentSpecificDTO.SESSION_DISCOUNT, discount.getDiscount());
		}
	}
}
