package com.laponhcet.action.unit;

import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteUnitConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		UnitDTO unit = (UnitDTO) getSessionAttribute(UnitDTO.SESSION_UNIT);
		Pagination pagination = (Pagination) getSessionAttribute(UnitDTO.SESSION_UNIT_PAGINATION);
		DTOUtil.removeObjById(pagination.getRecordListUnfiltered(), unit.getId());
		DTOUtil.removeObjById(pagination.getRecordList(), unit.getId());
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	protected void executeLogic() {
		execute(UnitDTO.SESSION_UNIT, new UnitDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
