package com.laponhcet.action.fee;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.ActionBase;

public class AddFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars(){
		sessionInfo.setTransitionLink(new String[] {"U00035", "U00030", "U00036"}, new String[] {"U00031", "U00037", "U00039"}, new String[] {"U00032", "U00038", "U00040"}, "U00033", "U00034");	
		if(!sessionInfo.isPreviousLinkAddSubmit()){
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(FeeDTO.SESSION_FEE_LIST, new FeeDAO().getFeeList()); // feeParentList
			setSessionAttribute(FeeDTO.SESSION_FEE + "_PARENT",  new FeeDTO()); // feeParent
			setSessionBeforeTrans(FeeDTO.SESSION_FEE, new FeeDTO());
		}
	}
}