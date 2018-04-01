package com.laponhcet.action.unit;

import com.laponhcet.action.merchandise.MerchandiseAction;
import com.laponhcet.dto.UnitDTO;

public class AddUnitAction extends MerchandiseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("UI0002")) {
			sessionInfo.setTransitionLink(new String[] {"UU0002", "UU0007"}, new String[] {"UU0003", "UU0008", "UU0010"}, new String[] {"UU0004", "UU0009", "UU0011"}, "UU0005", "UU0006", sessionInfo.getPreviousLink().getCode());
		}
		else {
			sessionInfo.setTransitionLink(new String[] {"UU0002", "UU0007"}, new String[] {"UU0003", "UU0008", "UU0010"}, new String[] {"UU0004", "UU0009", "UU0011"}, "UU0005", "UU0006");
		}

		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			UnitDTO unit = new UnitDTO();
			setSessionBeforeTrans(UnitDTO.SESSION_UNIT, unit);
		}
	}
}
