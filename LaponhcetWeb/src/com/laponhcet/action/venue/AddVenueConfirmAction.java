package com.laponhcet.action.venue;

import com.laponhcet.dao.VenueDAO;
import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddVenueConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(VenueDTO.SESSION_VENUE, new VenueDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
