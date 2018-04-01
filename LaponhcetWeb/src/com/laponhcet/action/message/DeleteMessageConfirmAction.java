package com.laponhcet.action.message;

import java.util.List;
import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.dto.CourseGroupDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.util.CourseUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class DeleteMessageConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void init() {
		validateTrans();
	}
	@SuppressWarnings("unchecked")
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			Pagination pagination = (Pagination) getSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION);
			pagination.updateList((CourseDTO) getSessionAttribute(CourseDTO.SESSION_COURSE), DAOBase.DAO_ACTION_DELETE);
			CourseUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST), (List<DTOBase>) getSessionAttribute(CourseGroupDTO.SESSION_COURSE_GROUP_LIST));
		}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	protected void executeLogic() {
		execute(MessageDTO.SESSION_MESSAGE, new MessageDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}
