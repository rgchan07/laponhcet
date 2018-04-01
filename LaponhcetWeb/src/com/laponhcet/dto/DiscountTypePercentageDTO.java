package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class DiscountTypePercentageDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_DISCOUNT_TYPE_PERCENTAGE = "SESSION_DISCOUNT_TYPE_PERCENTAGE";
	public static final String SESSION_DISCOUNT_TYPE_PERCENTAGE_LIST = "SESSION_DISCOUNT_TYPE_PERCENTAGE_LIST";
	
	private String discountTypeCode;
	private FeeDTO fee;
	private double percent;
	
	public DiscountTypePercentageDTO() {
		super();
		discountTypeCode = "";
		fee = new FeeDTO();
		percent = 0d;
	}
	
	public DiscountTypePercentageDTO getDiscountTypePercentage() {
		DiscountTypePercentageDTO discountTypePercentage = new DiscountTypePercentageDTO();
		discountTypePercentage.setDiscountTypeCode(this.discountTypeCode);
		discountTypePercentage.setFee(this.fee);
		discountTypePercentage.setPercent(this.percent);
		return discountTypePercentage;
	}

	public String getDiscountTypeCode() {
		return discountTypeCode;
	}

	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	public FeeDTO getFee() {
		return fee;
	}

	public void setFee(FeeDTO fee) {
		this.fee = fee;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
