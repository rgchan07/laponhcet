package com.laponhcet.action.supplier;

import com.laponhcet.action.merchandise.MerchandiseAction;
import com.laponhcet.dto.SupplierDTO;

public class AddSupplierAction extends MerchandiseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		System.out.println(sessionInfo.getPreviousLink().getCode());
		if(sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("UI0002")) {
			sessionInfo.setTransitionLink(new String[] {"UM0002", "UM0007"}, new String[] {"UM0003", "UM0008", "UM0010"}, new String[] {"UM0004", "UM0009", "UM0011"}, "UM0005", "UM0006", sessionInfo.getPreviousLink().getCode());
		}
		else{
			sessionInfo.setTransitionLink(new String[] {"UM0002", "UM0007"}, new String[] {"UM0003", "UM0008", "UM0010"}, new String[] {"UM0004", "UM0009", "UM0011"}, "UM0005", "UM0006");
		}
		
		if(!sessionInfo.isPreviousLinkUpdate()) {
			setSessionBeforeTrans(SupplierDTO.SESSION_SUPPLIER, new SupplierDTO());
		}
	}
}
