package com.laponhcet.action.course;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class CourseDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(CourseDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				pagination.setRecordList(new CourseDAO().getCourseListSearchByCodeDescription(searchValue));
			}
		}
	}
	
	protected void setPaginationList() {
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		Pagination pagination = (Pagination) getSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION);
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
			CourseDTO course = (CourseDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", course.getCode());
				jsonObjDetails.put("description", course.getDescription());
				jsonObjDetails.put("credit_unit", course.getCreditUnit());
				jsonObjDetails.put("pay_unit", course.getPayUnit());
				jsonObjDetails.put("lecture_hour", course.getLectureHour());
				jsonObjDetails.put("laboratory_hour", course.getLaboratoryHour());
				jsonObjDetails.put("course_group", course.getCourseGroup());
				jsonObjDetails.put("program", AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, course.getAcademicProgramCodes()));
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, course.getId()).replace("~", ","));
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
