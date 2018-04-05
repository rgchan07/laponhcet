package com.laponhcet.action.discount;

public class AddStudentSpecificDiscountSubmitAction extends StudentSpecificDiscountAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
	
}
