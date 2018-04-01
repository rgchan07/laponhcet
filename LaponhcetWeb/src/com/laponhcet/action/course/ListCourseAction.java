package com.laponhcet.action.course;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dao.CourseGroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.dto.CourseGroupDTO;
import com.laponhcet.util.CourseUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListCourseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0128", "US0123", "US0129"}, new String[] {"US0124", "US0130", "US0132"}, new String[] {"US0125", "US0131", "US0133"}, "US0126", "US0127");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> courseList = new CourseDAO().getCourseList();
			pagination.setName(CourseDTO.SESSION_COURSE_PAGINATION);
			pagination.setSearchCriteria(CourseDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Code", "Description", "Credit Unit", "Pay Unit", "Lecture Hrs","Laboratory Hrs", "Course Group", "Program Owner", ""});
			pagination.setColumnWidthList(new String[] {"7","30","8","8","8","8","9","15","7"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(courseList);
			pagination.setRecordList(courseList);
			pagination.setAjaxResultDetailsList(new String[] {"code", "description", "credit_unit", "pay_unit", "lecture_hour", "laboratory_hour", "course_group", "program", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(CourseGroupDTO.SESSION_COURSE_GROUP_LIST, new CourseGroupDAO().getCourseGroupList());
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(CourseDTO.SESSION_COURSE, new CourseDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION);
		}
		CourseUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		setSessionAttribute(CourseDTO.SESSION_COURSE_PAGINATION, pagination);
	}
}
