package com.laponhcet.action.fee;


import java.util.ArrayList;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class UpdateStudentSpecificFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"", "", "US0145"}, new String[] {"", "US0146", ""}, new String[] {"", "US0147", ""}, "", "");	
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = null;
			if(!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
				pagination = new Pagination();
				pagination.setName(StudentDTO.SESSION_STUDENT_PAGINATION);
				pagination.setSearchCriteria(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
				pagination.setColumnNameList(new String[] {"Code", "Last Name","First Name", "Middle Name", ""});
				pagination.setColumnWidthList(new String[] {"10","18" ,"18", "18", "5"});	
				pagination.setAjaxLinkCode("US0148");
				pagination.setAjaxResultDetailsList(new String[] {"code", "lastName", "firstName", "middleName","button"});
			}
			else{
				pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
			}
			setSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION, pagination);
			setSessionAttribute(FeeDTO.SESSION_FEE_LIST, new FeeDAO().getFeeList());
			setSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST, new ArrayList<DTOBase>());
			setSessionBeforeTrans(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC, new FeeStudentSpecificDTO());
		}
	}
}