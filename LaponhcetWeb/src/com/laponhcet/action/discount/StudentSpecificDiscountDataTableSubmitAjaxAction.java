package com.laponhcet.action.discount;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.DiscountStudentSpecificUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.StringUtil;

public class StudentSpecificDiscountDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
		//SemesterDTO semester = new SemesterDAO().getSemesterByCode();
		//DiscountTypeDTO discountType = new DiscountTypeDAO().getDiscountTypeByCode(discount.getDiscountType().getCode());
		
		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
		if(searchCriteria.equalsIgnoreCase(DiscountStudentSpecificDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
			//pagination.setRecordList(DiscountStudentSpecificUtil.searchByStudent(semester, searchValue));
		
			}
		}
	}

	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
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
			DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) pagination.getCurrentPageRecordList().get(i);
			UserDTO user = new UserDAO().getUserByCode(discount.getStudent().getCode());
			DiscountTypeDTO discountType = new DiscountTypeDAO().getDiscountTypeByCode(discount.getDiscountType().getCode());
			//AcademicProgramDTO program = new AcademicProgramDAO().getAcademicProgramByCode(discount.getStudent().getAcademicProgram().getCode());
		
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("aySemester", discount.getSemester().getAcademicYear().getCode());
				jsonObjDetails.put("id", user.getCode());
				jsonObjDetails.put("lastName", user.getLastName());
				jsonObjDetails.put("firstName", user.getFirstName());
				jsonObjDetails.put("middleName", user.getMiddleName());
				jsonObjDetails.put("discountType",discountType.getName());
				jsonObjDetails.put("program", "lala");
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, discount.getId()).replace("~", ","));
				jsonArray.put(jsonObjDetails);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}