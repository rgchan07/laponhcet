package com.laponhcet.action.unit;

import java.util.List;

import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class AddUnitConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	@SuppressWarnings("unchecked")
	protected void executeLogic() {
		if(sessionInfo.getCallBackLink() != null) {
			sessionInfo.setCurrentLink(sessionInfo.getCallBackLink().getLinkDTO());
			sessionInfo.setTransitionLink(new String[] {"UI0002", "UI0007"}, new String[] {"UI0003", "UI0008", "UI0010"}, new String[] {"UI0004", "UI0009", "UI0011"}, "UI0005", "UI0006");
			sessionInfo.setTransactionFinished(false);
			
			UnitDTO unit = (UnitDTO) getSessionAttribute(UnitDTO.SESSION_UNIT);
			MerchandiseDTO merchandise = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
			List<DTOBase> unitList = (List<DTOBase>) getSessionAttribute(UnitDTO.SESSION_UNIT_LIST);
			unit.setDisplayText(unit.getName());
			
			unitList.add(unit);
			merchandise.setUnit(unit);
		}
		execute(UnitDTO.SESSION_UNIT, new UnitDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
