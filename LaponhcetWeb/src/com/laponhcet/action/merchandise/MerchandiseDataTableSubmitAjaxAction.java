package com.laponhcet.action.merchandise;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.MerchandiseDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.util.MerchandiseUtil;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;

public class MerchandiseDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		PaginationData paginationData = (PaginationData) getSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + MerchandiseDTO.SESSION_PAGINATION_NAME);
		if(StringUtil.isEmpty(getRequestString("txtSearchValue"))) {
			paginationData.setRecordList(paginationData.getRecordListUnfiltered());
		}
		else {
			paginationData.setRecordList(new MerchandiseDAO().getMerchandiseListSearchByCodeName(getRequestString("txtSearchValue")));
		}
	}
	
	protected void setPaginationDataList() {
		PaginationData paginationData = (PaginationData) getSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + MerchandiseDTO.SESSION_PAGINATION_NAME);
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
			MerchandiseDTO merchandise = (MerchandiseDTO) paginationData.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", merchandise.getCode());
				jsonObjDetails.put("name", merchandise.getName());
				jsonObjDetails.put("location", merchandise.getLocation());
				jsonObjDetails.put("beginningBalance", merchandise.getQtyBeginning());
				jsonObjDetails.put("quantityOnStock", merchandise.getQtyOnStock());
				jsonObjDetails.put("unit", merchandise.getUnit());
				jsonObjDetails.put("unitPrice", merchandise.getUnitPrice());
				jsonObjDetails.put("costingMethod", merchandise.getCostingMethod());
				jsonObjDetails.put("nrv", merchandise.getNrv());
				jsonObjDetails.put("quantityReorder", merchandise.getQtyReorder());
				jsonObjDetails.put("supplier", merchandise.getSupplier());
				//jsonObjDetails.put("button", paginationData.getRecordButtonStr(sessionInfo, register.getId()).replace("~", ","));
				jsonObjDetails.put("button", MerchandiseUtil.getRecordButtonStr(sessionInfo, merchandise));
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
