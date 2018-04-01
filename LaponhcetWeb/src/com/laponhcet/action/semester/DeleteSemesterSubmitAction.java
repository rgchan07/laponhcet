package com.laponhcet.action.semester;

import com.laponhcet.dto.SemesterDTO;

public class DeleteSemesterSubmitAction extends SemesterAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars(){
	//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			setSessionBeforeTrans(SemesterDTO.SESSION_SEMESTER, getSelectedPaginationObjById(SemesterDTO.SESSION_SEMESTER_PAGINATION, getRequestInt("txtSelectedRecord")));
		}
	}
}
