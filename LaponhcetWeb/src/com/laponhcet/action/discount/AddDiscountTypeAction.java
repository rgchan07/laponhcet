package com.laponhcet.action.discount;

import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypePercentageUtil;
import com.laponhcet.util.FeeUtil;
import com.mytechnopal.base.ActionBase;

public class AddDiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00047", "U00042", "U00048"}, new String[] {"U00043", "U00049", "U00051"}, new String[] {"U00044", "U00050", "U00052"}, "U00045", "U00046");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			DiscountTypeDTO discountType = new DiscountTypeDTO();
			discountType.setDiscountTypePercentageList(DiscountTypePercentageUtil.getDiscountTypePercentageListByFeeListParent(FeeUtil.getFeeListParent(new FeeDAO().getFeeList())));
			setSessionBeforeTrans(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, discountType);
		}
	}
}