package com.laponhcet.action.course;

import java.util.List;

import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.util.CourseUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class UpdateCourseConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION);
			pagination.updateList((CourseDTO) getSessionAttribute(CourseDTO.SESSION_COURSE), DAOBase.DAO_ACTION_UPDATE);
			CourseUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(CourseDTO.SESSION_COURSE, new CourseDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}