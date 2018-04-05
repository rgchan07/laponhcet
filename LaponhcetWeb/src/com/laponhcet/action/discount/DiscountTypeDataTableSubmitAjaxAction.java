package com.laponhcet.action.discount;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.util.StringUtil;

public class DiscountTypeDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
		if(searchCriteria.equalsIgnoreCase(DiscountTypeDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
			pagination.setRecordList(new DiscountTypeDAO().getDiscountTypeListSearchByName(searchValue));
		
			}
		}
	}

	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
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
			DiscountTypeDTO discountType = (DiscountTypeDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				
				jsonObjDetails.put("name", discountType.getName());
				jsonObjDetails.put("percentage", discountType.isPercentage()?"YES":"NO");
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, discountType.getId()).replace("~", ","));
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
