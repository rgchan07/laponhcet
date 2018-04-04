package com.laponhcet.action.teacher;

import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteTeacherConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
		Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
		DTOUtil.removeObjById(pagination.getRecordListUnfiltered(), teacher.getId());
		DTOUtil.removeObjById(pagination.getRecordList(), teacher.getId());
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	protected void executeLogic() {
		execute(TeacherDTO.SESSION_TEACHER, new TeacherDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
