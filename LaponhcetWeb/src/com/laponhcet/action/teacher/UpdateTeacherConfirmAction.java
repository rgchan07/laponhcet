package com.laponhcet.action.teacher;

import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateTeacherConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		TeacherDTO teacher = (TeacherDTO) getSessionAttribute(TeacherDTO.SESSION_TEACHER);
		Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), teacher);
		DTOUtil.replaceObjById(pagination.getRecordList(), teacher);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(TeacherDTO.SESSION_TEACHER, new TeacherDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}