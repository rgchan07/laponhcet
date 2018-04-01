package com.laponhcet.action.academicyear;


import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class AcademicYearAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		AcademicYearDTO academicYear = (AcademicYearDTO) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> academicProgramGroupList = (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
		
		academicYear.setName(getRequestString("txtName"));
		academicYear.setDateStart(getRequestDateTime("txtDateStart", "MM/dd/yyyy"));
		academicYear.setDateEnd(getRequestDateTime("txtDateEnd", "MM/dd/yyyy"));

		String academicProgramCodes = "";
		for(int i=0; i<academicProgramGroupList.size(); i++) {
			AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)academicProgramGroupList.get(i);
			String selectedAcademicProgramCodes = getSelectedCheckBox(AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode()), "AcademicProgram"+academicProgramGroup.getCode());
			if(selectedAcademicProgramCodes.length() >=1 && academicProgramCodes.length() >= 1) {
				academicProgramCodes += "~";
			}
			academicProgramCodes += selectedAcademicProgramCodes;
		}
		academicYear.setAcademicProgramCodes(academicProgramCodes);
	}
	

	protected void validateInput() {
		AcademicYearDTO academicYear = (AcademicYearDTO) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR);
		String dateStartStr = DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM/dd/yyyy");
		String dateEndStr = DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM/dd/yyyy");
		if (StringUtil.isEmpty(academicYear.getName())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
	    }
		else if(StringUtil.isEmpty(dateStartStr)) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Start Date");
		}
		else if(StringUtil.isEmpty(dateEndStr)) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "End Date");
		}
		else if(!DateTimeUtil.isValidDateTime(dateStartStr, "MM/dd/yyyy")) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Start Date");
		}
		else if(!DateTimeUtil.isValidDateTime(dateEndStr, "MM/dd/yyyy")) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "End Date");
		}
		else if(academicYear.getDateEnd().before(academicYear.getDateStart())) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Dates");
		}
		else if(StringUtil.isEmpty(academicYear.getAcademicProgramCodes())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");    
		}
	}
}