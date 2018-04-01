package com.laponhcet.action.semester;

import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.SemesterUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListSemesterAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0116", "US0111", "US0117"}, new String[] {"US0112", "US0118", "US0120"}, new String[] {"US0113", "US0119", "US0121"}, "US0114", "US0115");
		Pagination pagination = null;
		List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> semesterList = (List<DTOBase>) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
			pagination.setName(SemesterDTO.SESSION_SEMESTER_PAGINATION);
			pagination.setSearchCriteria(SemesterDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Academic Year","Name","Date Start","Date End",""});
			pagination.setColumnWidthList(new String[] {"35","15","15","15","20"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(semesterList);
			pagination.setRecordList(semesterList);
			pagination.setAjaxResultDetailsList(new String[] {"academicYear","name","dateStart","dateEnd", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			setSessionAttribute(SemesterDTO.SESSION_SEMESTER_PAGINATION, pagination);
			setSessionAttribute(SemesterDTO.SESSION_SEMESTER, new SemesterDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_PAGINATION);
		}
		SemesterUtil.setPaginationRecord(sessionInfo, pagination, academicYearList);
	}
}
