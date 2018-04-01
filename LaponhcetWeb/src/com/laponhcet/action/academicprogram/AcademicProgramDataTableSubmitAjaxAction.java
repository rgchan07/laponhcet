package com.laponhcet.action.academicprogram;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.util.AcademicProgramUtil;

public class AcademicProgramDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		PaginationData paginationData = (PaginationData) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		
		if(StringUtil.isEmpty(searchValue)) {
			paginationData.setRecordList(paginationData.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(AcademicProgramDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				paginationData.setRecordList(new AcademicProgramDAO().getAcademicProgramListSearchByCodeName(searchValue));
			}
		}
	}
	
	protected void setPaginationDataList() {
		PaginationData paginationData = (PaginationData) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_PAGINATION);
		int currentPageTotalRecord = paginationData.getCurrentPageRecordList().size();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("totalRecord", paginationData.getRecordList().size());
			jsonObj.put("totalPage", paginationData.getTotalPage());
			jsonObj.put("currentPage", paginationData.getCurrentPage());
			jsonObj.put("recordPerPage", paginationData.getRecordPerPage());
			jsonObj.put("currentPageTotalRecord", currentPageTotalRecord);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<currentPageTotalRecord; i++) {
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) paginationData.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", academicProgram.getCode());
				jsonObjDetails.put("name", academicProgram.getName());
				jsonObjDetails.put("description", academicProgram.getDescription());
				//jsonObjDetails.put("button", paginationData.getRecordButtonStr(sessionInfo, academicProgram.getId()).replace("~", ","));
				jsonObjDetails.put("button", AcademicProgramUtil.getRecordButtonStr(sessionInfo, academicProgram));
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

