package com.laponhcet.action.fee;

import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.ActionBase;

public class ViewFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionAttribute(FeeDTO.SESSION_FEE, getSelectedPaginationObjById(FeeDTO.SESSION_FEE_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}
