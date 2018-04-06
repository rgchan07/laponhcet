package com.laponhcet.action.teacher;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.TeacherUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListTeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0096", "US0068", "US0073"}, new String[] {"US0069", "US0074", "US0076"}, new String[] {"US0070", "US0075", "US0077"}, "US0071", "US0072");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> teacherList = new TeacherDAO().getTeacherList();
			pagination.setName(TeacherDTO.SESSION_TEACHER_PAGINATION);
			pagination.setSearchCriteria(TeacherDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Pict", "Code","Last name","First name","Middle name","Availability",""});
			pagination.setColumnWidthList(new String[] {"10", "5","15","15","15","25","15"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(teacherList);
			pagination.setRecordList(teacherList);
			pagination.setAjaxResultDetailsList(new String[] {"pict","code","lastName","firstName","middleName","availability",Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(TeacherDTO.SESSION_TEACHER, new TeacherDTO());
		}else {
			pagination = (Pagination) getSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION);
		}
		TeacherUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		setSessionAttribute(TeacherDTO.SESSION_TEACHER_PAGINATION, pagination);
	}
}
