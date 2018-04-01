package com.laponhcet.action.venue;

import java.util.List;

import com.laponhcet.dao.VenueDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.VenueDTO;
import com.laponhcet.util.VenueUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteVenueConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(VenueDTO.SESSION_VENUE_PAGINATION);
			pagination.updateList((VenueDTO) getSessionAttribute(VenueDTO.SESSION_VENUE), DAOBase.DAO_ACTION_DELETE);
			VenueUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	protected void executeLogic() {
		execute(VenueDTO.SESSION_VENUE, new VenueDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
