package com.laponhcet.action.venue;

public class AddVenueSubmitAction extends VenueAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
