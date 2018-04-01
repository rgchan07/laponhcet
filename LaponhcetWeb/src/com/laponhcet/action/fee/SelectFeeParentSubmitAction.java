package com.laponhcet.action.fee;

import java.util.List;
import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class SelectFeeParentSubmitAction extends FeeAction {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
	protected void executeLogic() {
		FeeDTO fee = (FeeDTO) getSessionAttribute(FeeDTO.SESSION_FEE);
		List<DTOBase> feeList = (List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST);
		int parentId = getRequestInt("cboParent");	
		if(parentId > 0) {
			FeeDTO parent = (FeeDTO) DTOUtil.getObjById(feeList, getRequestInt("cboParent"));
			fee.setCodeParent(parent.getCode());
			fee.setAcademicProgramCodes(parent.getAcademicProgramCodes());
			setSessionAttribute(FeeDTO.SESSION_FEE + "_PARENT", parent);
		}
		else {
			fee.setCodeParent("");
			fee.setAcademicProgramCodes("");
			setSessionAttribute(FeeDTO.SESSION_FEE + "_PARENT", new FeeDTO());
		}
	}
}