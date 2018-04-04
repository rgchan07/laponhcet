package com.laponhcet.action.teacher;

import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteTeacherSubmitAction extends TeacherAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
			DTOBase userObj = DTOUtil.getObjByCode(pagination.getCurrentPageRecordList(), getRequestString("txtSelectedRecord"));
			TeacherDTO teacher = TeacherUtil.getTeacher(userObj);
			setSessionBeforeTrans(TeacherDTO.SESSION_TEACHER, teacher.getTeacher());
		}
	}
}
