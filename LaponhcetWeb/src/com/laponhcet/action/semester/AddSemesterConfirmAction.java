package com.laponhcet.action.semester;

import java.util.List;

import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AddSemesterConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		SemesterDTO semester = (SemesterDTO) getSessionAttribute(SemesterDTO.SESSION_SEMESTER);
		execute(semester, new SemesterDAO(), DAOBase.DAO_ACTION_ADD);
		if(actionResponse.isSuccess()) {
			List<DTOBase> semesterList = (List<DTOBase>) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
			semesterList.add(semester);
		}
	}
}