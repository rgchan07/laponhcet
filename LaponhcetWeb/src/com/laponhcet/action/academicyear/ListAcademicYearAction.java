package com.laponhcet.action.academicyear;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.util.AcademicYearUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListAcademicYearAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0104", "US0099", "US0105" }, new String[] { "US0100", "US0106", "US0108" }, new String[] { "US0101", "US0107", "US0109" }, "US0102", "US0103");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> academicYearList =  (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
			pagination.setName(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION);
			pagination.setSearchCriteria(AcademicYearDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Name", "Start Date", "End Date", "<p align='center'>Academic Programs</p>", ""});
			pagination.setColumnWidthList(new String[] {"15", "15", "15", "35", "20" });
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(academicYearList);
			pagination.setRecordList(academicYearList);
			pagination.setAjaxResultDetailsList(new String[] {"name", "date_start", "date_end", "academic_program", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR, new AcademicYearDTO());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
		}
		else {
			pagination = (Pagination) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION);
		}
		
		AcademicYearUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST));
		setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION, pagination);
	}
}