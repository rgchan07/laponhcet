package com.laponhcet.action.supplier;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.SupplierDAO;
import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.util.SupplierUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;

public class SupplierDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_PAGINATION);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			pagination.setRecordList(new SupplierDAO().getSupplierListSearchByCodeName(getRequestString("txtSearchValue")));
		}
	}
	
	protected void setPaginationDataList() {
		Pagination pagination = (Pagination) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_PAGINATION);
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
			SupplierDTO supplier = (SupplierDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", supplier.getCode());
				jsonObjDetails.put("lastName", supplier.getLastName());
				jsonObjDetails.put("firstName", supplier.getFirstName());
				jsonObjDetails.put("middleName", supplier.getMiddleName());
				jsonObjDetails.put("termDays", supplier.getTermDays());
				//jsonObjDetails.put("button", paginationData.getRecordButtonStr(sessionInfo, register.getId()).replace("~", ","));
				jsonObjDetails.put("button",SupplierUtil.getRecordButtonStr(sessionInfo, supplier));
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
