package com.laponhcet.action.user;


public class UpdateUserAccessSubmitAction extends UserAccessAction {

	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}

	
}
