package com.laponhcet.action.fee;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.laponhcet.util.FeeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class FeeDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(FeeDTO.SESSION_FEE_PAGINATION);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(FeeDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				pagination.setRecordList(new FeeDAO().getFeeListSearchByName(getRequestString("txtSearchValue")));
			}else if(searchCriteria.equalsIgnoreCase(FeeDTO.PAGINATION_SEARCH_CRITERIA_LIST[1])) {
				List<DTOBase> feeParentList = new FeeDAO().getFeeListSearchByName(getRequestString("txtSearchValue"));
				pagination.setRecordList(FeeUtil.getFeeListSearchByParent(feeParentList, (List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST)));
			}	
		}
	}
	
	protected void setPaginationList() {
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> feeList = (List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST);
		Pagination pagination = (Pagination) getSessionAttribute(FeeDTO.SESSION_FEE_PAGINATION);
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
			FeeDTO fee = (FeeDTO) pagination.getCurrentPageRecordList().get(i);
			FeeDTO parent = (FeeDTO) DTOUtil.getObjByCode(feeList, fee.getCodeParent());
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("parent", parent==null?"None":parent.getName());
				jsonObjDetails.put("name", fee.getName());
				jsonObjDetails.put("academicProgramCodes", AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, fee.getAcademicProgramCodes()));
				jsonObjDetails.put("mandatory", fee.isMandatory()?"Yes":"No");
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, fee.getId()).replace("~", ","));
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
