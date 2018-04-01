package com.laponhcet.action.course;

import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddCourseConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(CourseDTO.SESSION_COURSE, new CourseDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
