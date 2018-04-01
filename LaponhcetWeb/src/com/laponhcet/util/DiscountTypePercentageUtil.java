package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.DTOBase;

public class DiscountTypePercentageUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static List<DTOBase> getDiscountTypePercentageListByFeeListParent(List<DTOBase> feeListParent) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase feeObj: feeListParent) {
			DiscountTypePercentageDTO discountTypePercentage = new DiscountTypePercentageDTO();
			discountTypePercentage.setFee((FeeDTO) feeObj);
			resultList.add(discountTypePercentage);
		}
		return resultList;
	}
}
