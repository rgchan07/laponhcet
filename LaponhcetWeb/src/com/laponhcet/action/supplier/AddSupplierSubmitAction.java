package com.laponhcet.action.supplier;

public class AddSupplierSubmitAction extends SupplierAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
