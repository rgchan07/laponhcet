package com.laponhcet.action.venue;

import com.laponhcet.dao.VenueDAO;
import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.util.StringUtil;

public class VenueAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
			VenueDTO venue = (VenueDTO) getSessionAttribute(VenueDTO.SESSION_VENUE);
			String name = getRequestString("txtName");
			String location = getRequestString("txtLocation");
			String academicProgramCodes = getRequestString("txtAcademicProgramChecked");
			Integer concurrentSession = getRequestInt("txtConcurrentSession");
			Integer maxPax = getRequestInt("txtMaxPax");
			Integer colCount = getRequestInt("txtColumnCount");
			String colRow = getRequestString("txtColumnRowCells");
			
			venue.setName(name);
			venue.setLocation(location);
			venue.setAcademicProgramCodes(academicProgramCodes);
			venue.setConcurrentSession(concurrentSession);
			venue.setMaxPax(maxPax);
			venue.setColCount(colCount);
			venue.setColRow(colRow);
	}
	
	protected void validateInput() {
		if(!sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("US0049")) {
			VenueDTO venue = (VenueDTO) getSessionAttribute(VenueDTO.SESSION_VENUE);
			ActionResponse actionResponses = super.actionResponse;
			if(StringUtil.isEmpty(venue.getName())){
				actionResponses.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
			}else if(StringUtil.isEmpty(venue.getLocation())) {
				actionResponses.constructMessage(ActionResponse.TYPE_EMPTY, "Location");
			}else if(StringUtil.isEmpty(venue.getAcademicProgramCodes())) {
				actionResponses.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			}else {
				VenueDTO venueNameExist = new VenueDAO().getVenueByName(venue.getName());
				VenueDTO venueNameLocationExist = new VenueDAO().getVenueByNameLocation(venue.getName(), venue.getLocation());
				
				if(sessionInfo.isPreviousLinkAdd()) {
					if(venueNameExist!=null){
						actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Duplicate of Name: " + venueNameExist.getName() + " with a location of " + venueNameExist.getLocation());
					}
					if(actionResponses.isSuccess() && venueNameLocationExist!=null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name: " + venueNameExist.getName() + " in the location of " + venueNameExist.getLocation());
					}
				}else if(sessionInfo.isPreviousLinkUpdate()) {
					VenueDTO venueOrig = (VenueDTO) getSessionAttribute(VenueDTO.SESSION_VENUE + "_ORIG");
					if(venueNameExist!=null) {
						if(!venueOrig.getName().equalsIgnoreCase(venue.getName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Name: " + venueNameExist.getName() + " with the location of " + venueNameExist.getLocation());
						}
					}
					if(actionResponses.isSuccess() && venueNameLocationExist!=null) {
						if(!venueOrig.getName().equalsIgnoreCase(venue.getName()) && !venueOrig.getLocation().equalsIgnoreCase(venue.getLocation())) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name: " + venueNameExist.getName() + " in the location of " + venueNameExist.getLocation());
						}
					}
				}
			}
		}
	}
}
