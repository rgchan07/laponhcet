package com.laponhcet.action.semester;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;

public class SemesterDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_PAGINATION);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(SemesterDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				if(SemesterDTO.SEMESTER_DESCRIPTION_SUMMER.contains(getRequestString("txtSearchValue"))) {
					System.out.println("Summer");
					pagination.setRecordList(new SemesterDAO().getSemesterListSearchByName(SemesterDTO.SEMESTER_NAME_SUMMER));
				}
				if(SemesterDTO.SEMESTER_DESCRIPTION_FIRST.contains(getRequestString("txtSearchValue"))) {
					System.out.println("First");
					pagination.setRecordList(new SemesterDAO().getSemesterListSearchByName(SemesterDTO.SEMESTER_NAME_FIRST));
				}
				if(SemesterDTO.SEMESTER_DESCRIPTION_SECOND.contains(getRequestString("txtSearchValue"))) {
					System.out.println("Second");
					pagination.setRecordList(new SemesterDAO().getSemesterListSearchByName(SemesterDTO.SEMESTER_NAME_SECOND));
				}
			}
		}
	}
	
	protected void setPaginationDataList() {
		Pagination pagination = (Pagination) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_PAGINATION);
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
			SemesterDTO semester = (SemesterDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("academicYear", semester.getAcademicYear());
				jsonObjDetails.put("name", semester.getName());
				jsonObjDetails.put("dateStart", semester.getDateStart());
				jsonObjDetails.put("dateEnd", semester.getDateEnd());
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, semester.getId()).replace("~", ","));
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
