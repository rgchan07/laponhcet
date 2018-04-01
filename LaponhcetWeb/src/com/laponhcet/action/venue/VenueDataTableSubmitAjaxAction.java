package com.laponhcet.action.venue;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.VenueDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.VenueDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class VenueDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(VenueDTO.SESSION_VENUE_PAGINATION);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(VenueDTO.SEARCHCRITERIA[0])) {
				pagination.setRecordList(new VenueDAO().getVenueListSearchByName(getRequestString("txtSearchValue")));
			}else if (searchCriteria.equalsIgnoreCase(VenueDTO.SEARCHCRITERIA[1])) {
				pagination.setRecordList(new VenueDAO().getVenueListSearchByLocation(getRequestString("txtSearchValue")));
			}
		}
	}
	
	protected void setPaginationList() {
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		Pagination pagination = (Pagination) getSessionAttribute(VenueDTO.SESSION_VENUE_PAGINATION);
		int currentPageTotalRecord = pagination.getCurrentPageRecordList().size();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("totalRecord", pagination.getRecordList().size());
			jsonObj.put("totalPage", pagination.getTotalPage());
			jsonObj.put("currentPage", pagination.getCurrentPage());
			jsonObj.put("recordPerPage", pagination.getRecordPerPage());
			jsonObj.put("currentPageTotalRecord", currentPageTotalRecord);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<currentPageTotalRecord; i++) {
			VenueDTO venue = (VenueDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("name", venue.getName());
				jsonObjDetails.put("location", venue.getLocation());
				jsonObjDetails.put("concurrentSession", venue.getConcurrentSession());
				jsonObjDetails.put("maxPax", venue.getMaxPax());
				jsonObjDetails.put("academicProgramCodes",  AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, venue.getAcademicProgramCodes()));
				//jsonObjDetails.put("button", pagination.getRecordButtonStr(sessionInfo, register.getId()).replace("~", ","));
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, venue.getId()).replace("~", ","));
				jsonArray.put(jsonObjDetails);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			jsonObj.put("details", jsonArray);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
