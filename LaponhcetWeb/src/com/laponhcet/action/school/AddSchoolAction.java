package com.laponhcet.action.school;

import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dto.CityDTO;

public class AddSchoolAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0013", "US0018"}, new String[] {"US0014", "US0019", "US0021"}, new String[] {"US0015", "US0020", "US0022"}, "US0016", "US0017");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			SchoolDTO school = new SchoolDTO();
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
			setSessionBeforeTrans(SchoolDTO.SESSION_SCHOOL, school);
		}
	}
}

