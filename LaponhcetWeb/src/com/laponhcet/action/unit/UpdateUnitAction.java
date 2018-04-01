package com.laponhcet.action.unit;

import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateUnitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			UnitDTO unit = (UnitDTO) obj;
			
			setSessionAttribute(UnitDTO.SESSION_UNIT + "_ORIG", unit);
			setSessionBeforeTrans(UnitDTO.SESSION_UNIT, unit.getUnit());
		}
	}
}
