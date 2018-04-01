package com.laponhcet.action.supplier;

import java.util.List;

import com.laponhcet.dao.SupplierDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.SupplierDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AddSupplierConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	@SuppressWarnings("unchecked")
	protected void executeLogic() {
		if(sessionInfo.getCallBackLink() != null) {
			sessionInfo.setCurrentLink(sessionInfo.getCallBackLink().getLinkDTO());
			sessionInfo.setTransitionLink(new String[] {"UI0002", "UI0007"}, new String[] {"UI0003", "UI0008", "UI0010"}, new String[] {"UI0004", "UI0009", "UI0011"}, "UI0005", "UI0006");
			sessionInfo.setTransactionFinished(false);
			
			SupplierDTO supplier = (SupplierDTO) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER);
			MerchandiseDTO merchandise = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
			List<DTOBase> supplierList = (List<DTOBase>) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_LIST);
			supplier.setDisplayText(supplier.getName(true,false,true));
			
			supplierList.add(supplier);
			merchandise.setSupplier(supplier);
		}
		execute(SupplierDTO.SESSION_SUPPLIER, new SupplierDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
