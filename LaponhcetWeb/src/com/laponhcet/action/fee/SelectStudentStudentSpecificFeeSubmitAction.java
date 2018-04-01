package com.laponhcet.action.fee;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.FeeStudentSpecificDAO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class SelectStudentStudentSpecificFeeSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getUpdateLink());
	}
	
	protected void executeLogic() {
		FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC);
		List<DTOBase> feeStudentSpecificList = new FeeStudentSpecificDAO().getFeeStudentSpecificList();
		List<DTOBase> list = (List<DTOBase>) getSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
		StudentDTO student = (StudentDTO) getSelectedPaginationObjById(StudentDTO.SESSION_STUDENT_PAGINATION, getRequestInt("txtSelectedRecord"));
		feeStudentSpecific.setStudent(student);
		if(list.size()>0) {
			for(DTOBase feeStudentObj: list) {
				FeeStudentSpecificDTO feeStudent = (FeeStudentSpecificDTO) feeStudentObj;
				if(!feeStudent.getStudent().getCode().equalsIgnoreCase(student.getCode()) || !feeStudent.getSemester().getCode().isEmpty() || !feeStudent.getAcademicYear().getCode().isEmpty()) {
					list = new ArrayList<DTOBase>();
				}
			}
		}
		for(DTOBase obj: feeStudentSpecificList) {
			FeeStudentSpecificDTO feeStudentSpecificDTO = (FeeStudentSpecificDTO) obj;
			if(feeStudentSpecificDTO.getStudent().getCode().equalsIgnoreCase(student.getCode())) {
				list.add(feeStudentSpecificDTO);
			}
		}
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
		setSessionAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST, list);
	}
}
