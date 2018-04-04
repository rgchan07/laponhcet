package com.laponhcet.action.teacher;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateTeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
			DTOBase userObj = DTOUtil.getObjByCode(pagination.getCurrentPageRecordList(), getRequestString("txtSelectedRecord"));
			TeacherDTO teacher = TeacherUtil.getTeacher(userObj);
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(TeacherDTO.SESSION_TEACHER + "_ORIG", teacher);
			setSessionBeforeTrans(TeacherDTO.SESSION_TEACHER, teacher.getTeacher());
		}
	}
}
