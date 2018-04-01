package com.laponhcet.action.fee;

public class AddFeeSubmitAction extends FeeAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
