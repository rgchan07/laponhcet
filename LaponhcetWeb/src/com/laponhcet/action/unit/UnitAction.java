package com.laponhcet.action.unit;

import com.laponhcet.dao.UnitDAO;
import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.util.StringUtil;

public class UnitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		UnitDTO unit = (UnitDTO) getSessionAttribute(UnitDTO.SESSION_UNIT);		
		String name = getRequestString("txtName");		
		unit.setName(name);
		}
	
	protected void validateInput() {
		UnitDTO unit = (UnitDTO) getSessionAttribute(UnitDTO.SESSION_UNIT);
		if(StringUtil.isEmpty(unit.getName())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
		}
		else{
			UnitDTO unitExist = new UnitDAO().getUnitByName(unit.getName());
			if(unitExist!=null){
				actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
			}
		}
		
	}
}
