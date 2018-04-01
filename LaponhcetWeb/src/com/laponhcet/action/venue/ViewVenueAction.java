package com.laponhcet.action.venue;

import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.base.ActionBase;

public class ViewVenueAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionBeforeTrans(VenueDTO.SESSION_VENUE, getSelectedPaginationObjById(VenueDTO.SESSION_VENUE_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}
