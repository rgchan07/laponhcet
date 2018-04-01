package com.laponhcet.action.venue;

import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateVenueAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			VenueDTO venue = (VenueDTO) getSelectedPaginationObjById(VenueDTO.SESSION_VENUE_PAGINATION, getRequestInt("txtSelectedRecord"));
					
			setSessionAttribute(VenueDTO.SESSION_VENUE + "_ORIG", venue);
			setSessionBeforeTrans(VenueDTO.SESSION_VENUE, venue.getVenue());
		}
	}
}
