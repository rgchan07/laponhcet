package com.laponhcet.action.discount;

public class UpdateStudentSpecificDiscountSubmitAction extends StudentSpecificDiscountAction {
	private static final long serialVersionUID = 1L;

	public void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
