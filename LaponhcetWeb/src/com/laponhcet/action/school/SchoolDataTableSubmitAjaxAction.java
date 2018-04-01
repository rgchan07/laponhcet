package com.laponhcet.action.school;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.Pagination;
import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;
import com.laponhcet.util.SchoolUtil;

public class SchoolDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(SchoolDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				pagination.setRecordList(new SchoolDAO().getSchoolListSearchByName(searchValue));
			}
		}
	}
	
	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
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
			SchoolDTO school = (SchoolDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", school.getCode());
				jsonObjDetails.put("name", school.getName());
				jsonObjDetails.put("address", school.getAddress1());
				//jsonObjDetails.put("button", pagination.getRecordButtonStr(sessionInfo, school.getId()).replace("~", ","));
				jsonObjDetails.put("button", SchoolUtil.getRecordButtonStr(sessionInfo, school));
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

