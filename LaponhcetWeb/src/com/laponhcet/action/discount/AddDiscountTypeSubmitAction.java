package com.laponhcet.action.discount;

public class AddDiscountTypeSubmitAction extends DiscountTypeAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
	
}
