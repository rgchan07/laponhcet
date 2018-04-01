package com.laponhcet.action.fee;

import java.util.List;

import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteFeeStudentSpecificFeeSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
	protected void executeLogic() {
		List<DTOBase> list = (List<DTOBase>) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
		int id = getRequestInt("txtSelectedRecord");
		DTOUtil.removeObjById(list, id);
	}
}
