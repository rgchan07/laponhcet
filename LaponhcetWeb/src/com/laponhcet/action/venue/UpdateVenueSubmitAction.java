package com.laponhcet.action.venue;

public class UpdateVenueSubmitAction extends VenueAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
