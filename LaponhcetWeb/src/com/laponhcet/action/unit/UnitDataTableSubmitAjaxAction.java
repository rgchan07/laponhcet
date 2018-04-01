package com.laponhcet.action.unit;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.UnitDTO;
import com.laponhcet.util.UnitUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;

public class UnitDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			pagination.setRecordList(new UnitDAO().getUnitListSearchByCodeName(getRequestString("txtSearchValue")));
		}
	}
	
	protected void setPaginationDataList() {
		Pagination pagination = (Pagination) getSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION);
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
			UnitDTO unit = (UnitDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", unit.getCode());
				jsonObjDetails.put("name", unit.getName());
				//jsonObjDetails.put("button", paginationData.getRecordButtonStr(sessionInfo, register.getId()).replace("~", ","));
				jsonObjDetails.put("button", UnitUtil.getRecordButtonStr(sessionInfo, unit));
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
