package com.laponhcet.action.discount;

import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteStudentSpecificDiscountSubmitAction extends StudentSpecificDiscountAction{
private static final long serialVersionUID = 1L;
protected void setSessionVars() {
	// is previous link from the list
	if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
		Pagination pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
		DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
		DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) obj;
		setSessionBeforeTrans(DiscountStudentSpecificDTO.SESSION_DISCOUNT, discount.getDiscount());
	}
}

}
