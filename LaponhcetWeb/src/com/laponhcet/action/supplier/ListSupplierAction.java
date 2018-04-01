package com.laponhcet.action.supplier;

import java.util.List;

import com.laponhcet.dao.SupplierDAO;
import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.util.SupplierUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.StringUtil;

public class ListSupplierAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UM0002", "UM0007"}, new String[] {"UM0003", "UM0008", "UM0010"}, new String[] {"UM0004", "UM0009", "UM0011"}, "UM0005", "UM0006");
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			Pagination pagination = new Pagination();
			List<DTOBase> supplierList = new SupplierDAO().getSupplierList();
			pagination.setName(SupplierDTO.SESSION_SUPPLIER_PAGINATION);
			pagination.setSearchCriteria("Name");
			pagination.setColumnNameList(new String[] {"Code","Last name","First name","Middle name","Term Days",""});
			pagination.setColumnWidthList(new String[] {"10","20","20","20","20","10"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(supplierList);
			pagination.setRecordList(supplierList);
			pagination.setAjaxResultDetailsList(new String[] {"code","lastName","firstName","middleName","termDays","button"});
			setPaginationRecord(pagination, sessionInfo);
			setSessionAttribute(SupplierDTO.SESSION_SUPPLIER_PAGINATION, pagination);
			
		}
	}
	
	public static void setPaginationRecord(Pagination pagination, SessionInfo sessionInfo) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			SupplierDTO supplier = (SupplierDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(supplier.getCode());
			supplier.setPaginationRecord(new String[]{supplier.getCode(), user.getLastName(), user.getFirstName(), user.getMiddleName(), StringUtil.getFormattedNum(supplier.getTermDays(), "0.00"), SupplierUtil.getRecordButtonStr(sessionInfo, supplier)});
		}
	}
}
