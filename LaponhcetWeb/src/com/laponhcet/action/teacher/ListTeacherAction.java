package com.laponhcet.action.teacher;

import java.util.List;

import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;

public class ListTeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0106", "US0068", "US0073"}, new String[] {"US0069", "US0074", "US0076"}, new String[] {"US0070", "US0075", "US0077"}, "US0071", "US0072");
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit() && !sessionInfo.isPreviousLinkView()) {
			Pagination pagination = new Pagination();
			List<DTOBase> teacherList = new TeacherDAO().getTeacherList();
			pagination.setName(TeacherDTO.SESSION_TEACHER_PAGINATION);
			pagination.setSearchCriteria(TeacherDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Pict", "Code","Last name","First name","Middle name","Availability",""});
			pagination.setColumnWidthList(new String[] {"10", "5","15","15","15","30","10"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(teacherList);
			pagination.setRecordList(teacherList);
			pagination.setAjaxResultDetailsList(new String[] {"pict","code","lastName","firstName","middleName","availability","button"});
			setPaginationRecord(pagination);
			setSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION, pagination);
			setSessionAttribute(TeacherDTO.SESSION_TEACHER, new TeacherDTO());
		}else {
			Pagination pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
			setPaginationRecord(pagination);
		}
	}
	
	private void setPaginationRecord(Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			TeacherDTO teacher = (TeacherDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(teacher.getCode());
			teacher.setPaginationRecord(new String[]{"<img class='img-thumbnail img-md' src='"+ user.getProfilePict() +"'>", user.getCode(), user.getLastName(), user.getFirstName(), user.getMiddleName(), TeacherUtil.getNameList(TeacherUtil.getArrNameProgramGroupList(teacher.getAcademicProgramCodes())), TeacherUtil.getRecordButtonStr(sessionInfo, teacher)});
		}
	}
}
