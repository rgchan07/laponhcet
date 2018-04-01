package com.laponhcet.action.supplier;

import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.util.SupplierUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateSupplierAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_PAGINATION);
			DTOBase userObj = DTOUtil.getObjByCode(pagination.getCurrentPageRecordList(), getRequestString("txtSelectedRecord"));
			SupplierDTO supplier = SupplierUtil.getSupplier(userObj);
			setSessionAttribute(SupplierDTO.SESSION_SUPPLIER + "_ORIG", supplier);
			setSessionBeforeTrans(SupplierDTO.SESSION_SUPPLIER, supplier.getSupplier());
		}
	}
}
