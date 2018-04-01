package com.laponhcet.action.message;

public class UpdateMessageSubmitAction extends MessageAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
