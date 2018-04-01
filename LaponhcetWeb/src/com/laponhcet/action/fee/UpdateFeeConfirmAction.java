package com.laponhcet.action.fee;

import java.util.List;

import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.util.FeeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateFeeConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(FeeDTO.SESSION_FEE_PAGINATION);
			pagination.updateList((FeeDTO) getSessionAttribute(FeeDTO.SESSION_FEE), DAOBase.DAO_ACTION_UPDATE);
			FeeUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST), (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(FeeDTO.SESSION_FEE, new FeeDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
