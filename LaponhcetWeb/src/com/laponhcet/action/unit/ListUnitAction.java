package com.laponhcet.action.unit;

import java.util.List;

import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.UnitDTO;
import com.laponhcet.util.UnitUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListUnitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UU0002", "UU0007"}, new String[] {"UU0003", "UU0008", "UU0010"}, new String[] {"UU0004", "UU0009", "UU0011"}, "UU0005", "UU0006");
		
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			Pagination Pagination = new Pagination();
			List<DTOBase> unitList = new UnitDAO().getUnitList();
			Pagination.setName(UnitDTO.SESSION_UNIT_PAGINATION);
			Pagination.setSearchCriteria("Name");
			Pagination.setColumnNameList(new String[] {"Code","Name",""});
			Pagination.setColumnWidthList(new String[] {"20","70","10"});
			Pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			Pagination.setRecordListUnfiltered(unitList);
			Pagination.setRecordList(unitList);
			Pagination.setAjaxResultDetailsList(new String[] {"code","name","button"});
			setPaginationRecord(Pagination, sessionInfo);
			setSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION, Pagination);
			
		}
	}
	
	public static void setPaginationRecord(Pagination pagination, SessionInfo sessionInfo) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			UnitDTO unit = (UnitDTO) dto;
			unit.setPaginationRecord(new String[] {unit.getCode(), unit.getName(), UnitUtil.getRecordButtonStr(sessionInfo, unit)});
		}
	}
}
