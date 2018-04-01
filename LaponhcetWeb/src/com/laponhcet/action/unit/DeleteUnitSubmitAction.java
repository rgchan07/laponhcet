package com.laponhcet.action.unit;

import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteUnitSubmitAction extends UnitAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			UnitDTO unit = (UnitDTO) obj;
			setSessionBeforeTrans(UnitDTO.SESSION_UNIT, unit.getUnit());
		}
	}
}
