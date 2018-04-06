package com.laponhcet.action.teacher;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class TeacherDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(TeacherDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				List<DTOBase> userList = new UserDAO().getUserListByUserGroupCodeSearchByNameCode(UserGroupDTO.USER_GROUP_TEACHER_CODE, getRequestString("txtSearchValue"));
				pagination.setRecordList(TeacherUtil.getTeacherListSearchByName(userList));
			}
		}
	}
	
	protected void setPaginationList() {
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
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
			TeacherDTO teacher = (TeacherDTO) pagination.getCurrentPageRecordList().get(i);
			UserDTO user = new UserDAO().getUserByCode(teacher.getCode());
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("pict", "<img class='img-thumbnail img-md' src='"+ user.getProfilePict() +"'>");
				jsonObjDetails.put("code", teacher.getCode());
				jsonObjDetails.put("lastName", user.getLastName());
				jsonObjDetails.put("firstName", user.getFirstName());
				jsonObjDetails.put("middleName", user.getMiddleName());
				jsonObjDetails.put("availability", AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, teacher.getAcademicProgramCodes()));
				//jsonObjDetails.put("button", pagination.getRecordButtonStr(sessionInfo, register.getId()).replace("~", ","));
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, teacher.getId()).replace("~", ","));
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
