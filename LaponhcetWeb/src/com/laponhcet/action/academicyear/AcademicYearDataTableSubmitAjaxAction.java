package com.laponhcet.action.academicyear;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.laponhcet.util.AcademicYearUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class AcademicYearDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(AcademicYearDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				pagination.setRecordList(new AcademicYearDAO().getAcademicYearListSearchByName(searchValue));
			}
			else if(searchCriteria.equalsIgnoreCase(AcademicYearDTO.PAGINATION_SEARCH_CRITERIA_LIST[1])){
				//pagination.setRecordList(AcademicYearUtil.getAcademicYearListSearchByAcademicProgram(searchValue));
			}
		}
	}

	protected void setPaginationList() {
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		Pagination pagination = (Pagination) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION);
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
			AcademicYearDTO academicYear = (AcademicYearDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("name", academicYear.getName());
				jsonObjDetails.put("date_start", DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM-dd-yyyy"));
				jsonObjDetails.put("date_end", DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM-dd-yyyy"));
				jsonObjDetails.put("academic_program", AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, academicYear.getAcademicProgramCodes()));
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, academicYear.getId()).replace("~", ","));
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

