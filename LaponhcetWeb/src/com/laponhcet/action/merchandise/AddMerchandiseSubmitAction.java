package com.laponhcet.action.merchandise;

public class AddMerchandiseSubmitAction extends MerchandiseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
