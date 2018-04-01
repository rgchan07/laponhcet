package com.laponhcet.action.school;


import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dto.CityDTO;

import java.util.List;
import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;

public class ListSchoolAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0013", "US0018"}, new String[] {"US0014", "US0019", "US0021"}, new String[] {"US0015", "US0020", "US0022"}, "US0016", "US0017");
		if((!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit())) {
			Pagination pagination = new Pagination();
			List<DTOBase> schoolList = new SchoolDAO().getSchoolList();
			pagination.setName(SchoolDTO.SESSION_SCHOOL_PAGINATION);
			pagination.setSearchCriteria("Name");
			pagination.setColumnNameList(new String[] {"Code","Name", "Address", ""});
			pagination.setColumnWidthList(new String[] {"15","42", "30", "13"});	
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(schoolList);
			pagination.setRecordList(schoolList);
			pagination.setAjaxResultDetailsList(new String[] {"code", "name", "address", "button"});
			setPaginationRecord(pagination, sessionInfo);
			setSessionAttribute(pagination.getName(), pagination);
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
		}
	}
	
	private void setPaginationRecord(Pagination pagination, SessionInfo sessionInfo) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			SchoolDTO school = (SchoolDTO) dto;
			school.setPaginationRecord(new String[]{school.getCode(), school.getName(), school.getAddress1(), SchoolUtil.getRecordButtonStr(sessionInfo, school)});
		}
	}	
}

