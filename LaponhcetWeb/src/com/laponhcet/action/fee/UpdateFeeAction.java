package com.laponhcet.action.fee;
import java.util.List;

import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateFeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			List<DTOBase> feeList = (List<DTOBase>) getSessionAttribute(FeeDTO.SESSION_FEE_LIST);
			FeeDTO fee = (FeeDTO) getSelectedPaginationObjById(FeeDTO.SESSION_FEE_PAGINATION, getRequestInt("txtSelectedRecord"));			
			FeeDTO parent = (FeeDTO) DTOUtil.getObjByCode(feeList, fee.getCodeParent());
			if(parent == null) {
				setSessionAttribute(FeeDTO.SESSION_FEE + "_PARENT",  new FeeDTO());
			}
			else {
				setSessionAttribute(FeeDTO.SESSION_FEE + "_PARENT", parent);
			}
			setSessionAttribute(FeeDTO.SESSION_FEE + "_ORIG", fee);
			setSessionBeforeTrans(FeeDTO.SESSION_FEE, fee.getFee());
		}
	}
}