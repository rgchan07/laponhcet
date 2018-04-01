package com.laponhcet.action.academicyear;

import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class AddAcademicYearConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		AcademicYearDTO academicYear = (AcademicYearDTO) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR);
		execute(academicYear, new AcademicYearDAO(), DAOBase.DAO_ACTION_ADD);
		if(actionResponse.isSuccess()) {
			List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
		    academicYear.setDisplayText(academicYear.getName() + " [" + DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM-dd-yyyy") + " to " + DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM-dd-yyyy") + "]");
			academicYearList.add(academicYear);
		}
	}
}
