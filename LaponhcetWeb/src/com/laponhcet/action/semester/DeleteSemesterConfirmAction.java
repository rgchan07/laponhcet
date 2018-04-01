package com.laponhcet.action.semester;

import java.util.List;

import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.SemesterUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class DeleteSemesterConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_PAGINATION);
			pagination.updateList((SemesterDTO) getSessionAttribute(SemesterDTO.SESSION_SEMESTER), DAOBase.DAO_ACTION_DELETE);
			SemesterUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());

	}
	protected void executeLogic() {
		execute(SemesterDTO.SESSION_SEMESTER, new SemesterDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
