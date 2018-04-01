package com.laponhcet.action.message;

public class AddMessageSubmitAction extends MessageAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
