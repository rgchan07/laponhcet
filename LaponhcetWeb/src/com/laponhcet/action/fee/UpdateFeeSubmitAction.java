package com.laponhcet.action.fee;

public class UpdateFeeSubmitAction extends FeeAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
