package com.laponhcet.action.teacher;

import java.util.List;

import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateTeacherConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
			pagination.updateList((TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER), DAOBase.DAO_ACTION_UPDATE);
			TeacherUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(TeacherDTO.SESSION_TEACHER, new TeacherDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
