package com.laponhcet.action.fee;

import java.util.List;

import com.laponhcet.dao.FeeStudentSpecificDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.util.FeeStudentSpecificUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateStudentSpecificFeeConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			sessionInfo.setCurrentLink(sessionInfo.getListLink());
		}
	}
	
	protected void executeLogic() {
		executeList((List<DTOBase>) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST), new FeeStudentSpecificDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
