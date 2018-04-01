package com.laponhcet.action.academicyear;

import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.util.AcademicYearUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateAcademicYearConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute( AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION);
			pagination.updateList((AcademicYearDTO) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR), DAOBase.DAO_ACTION_UPDATE);
			AcademicYearUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(AcademicYearDTO.SESSION_ACADEMIC_YEAR, new AcademicYearDAO(), DAOBase.DAO_ACTION_UPDATE);
	}

}
