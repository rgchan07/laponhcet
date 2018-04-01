package com.laponhcet.action.venue;

import com.laponhcet.dto.VenueDTO;

public class DeleteVenueSubmitAction extends VenueAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionBeforeTrans(VenueDTO.SESSION_VENUE, getSelectedPaginationObjById(VenueDTO.SESSION_VENUE_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}
