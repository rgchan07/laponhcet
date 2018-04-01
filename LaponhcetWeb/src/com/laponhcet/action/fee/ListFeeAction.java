package com.laponhcet.action.fee;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.util.FeeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00035", "U00030", "U00036"}, new String[] {"U00031", "U00037", "U00039"}, new String[] {"U00032", "U00038", "U00040"}, "U00033", "U00034");	
		Pagination pagination = null;
		List<DTOBase> feeList = new FeeDAO().getFeeList();
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			pagination.setName(FeeDTO.SESSION_FEE_PAGINATION);
			pagination.setSearchCriteria(FeeDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Parent", "Name", "Program(s)", "Mandatory",""});
			pagination.setColumnWidthList(new String[] {"20", "20", "30", "10", "20"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(feeList);
			pagination.setRecordList(feeList);
			pagination.setAjaxResultDetailsList(new String[] {"parent", "name", "academicProgramCodes", "mandatory", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(FeeDTO.SESSION_FEE, new FeeDTO());
		}else {
			pagination = (Pagination) getSessionAttribute(FeeDTO.SESSION_FEE_PAGINATION);
		}
		
		List<DTOBase> academicProgramList = new AcademicProgramDAO().getAcademicProgramList();
		setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, academicProgramList);
		setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
		setSessionAttribute(FeeDTO.SESSION_FEE_LIST, feeList);
		FeeUtil.setPaginationRecord(sessionInfo, pagination, feeList, academicProgramList);
		setSessionAttribute(FeeDTO.SESSION_FEE_PAGINATION, pagination);
	}
}
