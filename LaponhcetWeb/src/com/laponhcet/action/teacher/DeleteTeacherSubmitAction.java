package com.laponhcet.action.teacher;

import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.base.DTOBase;

public class DeleteTeacherSubmitAction extends TeacherAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		DTOBase userObj = (DTOBase) getSelectedPaginationObjById(TeacherDTO.SESSION_TEACHER_PAGINATION, getRequestInt("txtSelectedRecord"));
		TeacherDTO teacher = TeacherUtil.getTeacher(userObj);
		setSessionBeforeTrans(TeacherDTO.SESSION_TEACHER, teacher);
	}
}
