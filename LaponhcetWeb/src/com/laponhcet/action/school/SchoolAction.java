package com.laponhcet.action.school;

import java.util.List;

import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class SchoolAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setInput() {
		SchoolDTO school = (SchoolDTO) getSessionAttribute(SchoolDTO.SESSION_SCHOOL);
		List<DTOBase> cityList = (List<DTOBase>) getSessionAttribute(CityDTO.SESSION_CITY_LIST);
		int cityId = getRequestInt("cboCity");		
		
		school.setName(getRequestString("txtName"));
		school.setAddress1(getRequestString("txtAddress1"));
		school.setAddress2(getRequestString("txtAddress2"));		
		
		if(cityId != 0) {
		    school.setCity((CityDTO) DTOUtil.getObjById(cityList, cityId));
		}
		
		school.setRegistrarOIC(getRequestString("txtRegistrarOIC"));
		school.setWebsite(getRequestString("txtWebsite", true));
		school.setContactNumber(getRequestString("txtContactNumber"));
	}
	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()){
			SchoolDTO school = (SchoolDTO) getSessionAttribute(SchoolDTO.SESSION_SCHOOL);
			if(StringUtil.isEmpty(school.getName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
			}
			/*else if(StringUtil.isEmpty(school.getAddress1()) && StringUtil.isEmpty(school.getAddress2())) {
				message.constructMsg(Message.MSG_CLASS_EMPTY, "Address");
			}*/
			else {
				SchoolDTO existing = new SchoolDAO().getSchoolByName(school.getName());
				if(sessionInfo.isPreviousLinkAdd()) {
					if(existing != null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
					}
				}
				else {
					SchoolDTO schoolOrig = (SchoolDTO) getSessionAttribute(SchoolDTO.SESSION_SCHOOL +"_ORIG");
					if(!schoolOrig.getName().equalsIgnoreCase(school.getName())) {
						if(existing != null) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
						}
					}
				}
			}
		}
	}
}

