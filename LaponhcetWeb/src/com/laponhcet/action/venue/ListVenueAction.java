package com.laponhcet.action.venue;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.VenueDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.VenueDTO;
import com.laponhcet.util.VenueUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class ListVenueAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0094", "US0046", "US0051"}, new String[] {"US0047", "US0052", "US0054"}, new String[] {"US0048", "US0053", "US0055"}, "US0049", "US0050");
		Pagination pagination =  null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> venueList = new VenueDAO().getVenueList();
			pagination.setName(VenueDTO.SESSION_VENUE_PAGINATION);
			pagination.setSearchCriteria(VenueDTO.SEARCHCRITERIA[0]);
			pagination.setColumnNameList(new String[] {"Name","Location","Concurrent Session","Max Pax","Program Availability",""});
			pagination.setColumnWidthList(new String[] {"10","30","10","10","25","15"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(venueList);
			pagination.setRecordList(venueList);
			pagination.setAjaxResultDetailsList(new String[] {"name","location","concurrentSession","maxPax","academicProgramCodes",Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(VenueDTO.SESSION_VENUE, new VenueDTO());
		}else {
			pagination = (Pagination) getSessionAttribute(VenueDTO.SESSION_VENUE_PAGINATION);
		}
		VenueUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		setSessionAttribute(VenueDTO.SESSION_VENUE_PAGINATION, pagination);
	}
}
