package com.laponhcet.action.supplier;

import com.laponhcet.dao.SupplierDAO;
import com.laponhcet.dto.SupplierDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateSupplierConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		SupplierDTO supplier = (SupplierDTO) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER);
		Pagination pagination = (Pagination) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_PAGINATION);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), supplier);
		DTOUtil.replaceObjById(pagination.getRecordList(), supplier);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(SupplierDTO.SESSION_SUPPLIER, new SupplierDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
