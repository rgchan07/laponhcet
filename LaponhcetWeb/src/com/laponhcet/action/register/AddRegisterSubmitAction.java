package com.laponhcet.action.register;

public class AddRegisterSubmitAction extends RegisterAction {

	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
