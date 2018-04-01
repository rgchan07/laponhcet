package com.laponhcet.action.venue;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.base.ActionBase;

public class AddVenueAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars(){
		sessionInfo.setTransitionLink(new String[] {"US0094", "US0046", "US0051"}, new String[] {"US0047", "US0052", "US0054"}, new String[] {"US0048", "US0053", "US0055"}, "US0049", "US0050");
		if(!sessionInfo.isPreviousLinkAddSubmit()){
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionBeforeTrans(VenueDTO.SESSION_VENUE, new VenueDTO());
		}
	}
}
