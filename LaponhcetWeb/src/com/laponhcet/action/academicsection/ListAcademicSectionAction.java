package com.laponhcet.action.academicsection;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicSectionDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicSectionDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;



public class ListAcademicSectionAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0035", "US0040"}, new String[] {"US0036", "US0041", "US0043"}, new String[] {"US0037", "US0042", "US0044"}, "US0038", "US0039");
		if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			Pagination pagination = new Pagination();
			List<DTOBase> sectionList = new AcademicSectionDAO().getAcademicSectionList();
			pagination.setName(AcademicSectionDTO.SESSION_ACADEMIC_SECTION_PAGINATION);
			pagination.setSearchCriteria("Name");
			pagination.setColumnNameList(new String[] {"Academic Program", "Year Level", "Name",""});
			pagination.setColumnWidthList(new String[] {"50", "20", "30" });
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(sectionList);
			pagination.setRecordList(sectionList);
			pagination.setAjaxResultDetailsList(new String[] {"academicProgram", "yearLevel", "name","button"});
			setPaginationRecord(pagination, sessionInfo);
			setSessionAttribute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION_PAGINATION, pagination);			
		}
	}
	
	public static void setPaginationRecord(Pagination pagination, SessionInfo sessionInfo) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			AcademicSectionDTO section = (AcademicSectionDTO) dto;
			AcademicProgramDTO program = new AcademicProgramDAO().getAcademicProgramByName(section.getAcademicProgram().getName());
			}
	}
}

