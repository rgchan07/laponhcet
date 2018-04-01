package com.laponhcet.action.merchandise;

import java.util.List;

import com.laponhcet.dao.SupplierDAO;
import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.dto.UnitDTO;
import com.laponhcet.util.SupplierUtil;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class AddMerchandiseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UI0002", "UI0007"}, new String[] {"UI0003", "UI0008", "UI0010"}, new String[] {"UI0004", "UI0009", "UI0011"}, "UI0005", "UI0006");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			MerchandiseDTO merchandise = new MerchandiseDTO();
			List<DTOBase> unitList = new UnitDAO().getUnitList();
			List<DTOBase> supplierList = new SupplierDAO().getSupplierList();
			merchandise.setUnit((UnitDTO) unitList.get(0));
			merchandise.setSupplier((SupplierDTO) supplierList.get(0));
			SupplierUtil.setSupplierList(supplierList);
			setSessionAttribute(UnitDTO.SESSION_UNIT_LIST, unitList);
			setSessionAttribute(SupplierDTO.SESSION_SUPPLIER_LIST, supplierList);
			setSessionBeforeTrans(MerchandiseDTO.SESSION_MERCHANDISE, merchandise);
		}
	}
}
