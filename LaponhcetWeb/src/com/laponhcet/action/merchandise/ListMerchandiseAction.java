package com.laponhcet.action.merchandise;

import java.util.List;

import com.laponhcet.dao.MerchandiseDAO;
import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.UnitDTO;
import com.laponhcet.util.MerchandiseUtil;
import com.mytechnopal.PaginationData;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.StringUtil;

public class ListMerchandiseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UI0002", "UI0007"}, new String[] {"UI0003", "UI0008", "UI0010"}, new String[] {"UI0004", "UI0009", "UI0011"}, "UI0005", "UI0006");
		
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			PaginationData paginationData = new PaginationData();
			List<DTOBase> merchandiseList = new MerchandiseDAO().getMerchandiseList();
			paginationData.setName(MerchandiseDTO.SESSION_PAGINATION_NAME);
			paginationData.setSearchCriteria("Name");
			paginationData.setColumnNameList(new String[] {"Code","Name","Location","Beginning Balance","Quantity on Stock","Unit","Unit Price","Costing Method","Nrv","Quantity Reorder","Supplier",""});
			paginationData.setColumnWidthList(new String[] {"5","10","10","6","6","8","10","5","5","5","10","10"});
			paginationData.setAjaxLinkCode(sessionInfo.getPaginationDataLink().getCode());
			paginationData.setRecordListUnfiltered(merchandiseList);
			paginationData.setRecordList(merchandiseList);
			paginationData.setAjaxResultDetailsList(new String[] {"code","name","location","beginningBalance","quantityOnStock","unit","unitPrice","costingMethod","nrv","quantityReorder","supplier","button"});
			setPaginationRecord(paginationData, sessionInfo);
			setSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + paginationData.getName(), paginationData);
			
		}
	}
	
	public static void setPaginationRecord(PaginationData paginationData, SessionInfo sessionInfo) {
		for(DTOBase dto: paginationData.getCurrentPageRecordList()) {
			MerchandiseDTO merchandise = (MerchandiseDTO) dto;
			UnitDTO unit = new UnitDAO().getUnitByCode(merchandise.getUnit().getCode());
			UserDTO supplier = new UserDAO().getUserByCode(merchandise.getSupplier().getCode());
			merchandise.setPaginationRecord(new String[]{merchandise.getCode(),merchandise.getName(), merchandise.getLocation(), StringUtil.getNumToStr(merchandise.getQtyBeginning(), "0.00", false), StringUtil.getNumToStr(merchandise.getQtyOnStock(), "0.00", false), unit.getName(), StringUtil.getNumToStr(merchandise.getUnitPrice(), "0.00", false), merchandise.getCostingMethod(), StringUtil.getNumToStr(merchandise.getNrv(), "0.00", false), StringUtil.getNumToStr(merchandise.getQtyReorder(), "0.00", false), supplier.getName(true, true, true), MerchandiseUtil.getRecordButtonStr(sessionInfo, merchandise)});
		}
	}
}
