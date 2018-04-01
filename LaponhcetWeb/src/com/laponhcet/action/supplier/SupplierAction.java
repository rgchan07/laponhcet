package com.laponhcet.action.supplier;

import java.util.Date;

import com.laponhcet.dto.SupplierDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.util.StringUtil;

public class SupplierAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		SupplierDTO supplier = (SupplierDTO) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER);
		
		String lastName = getRequestString("txtLastName");
		String firstName = getRequestString("txtFirstName");
		String middleName = getRequestString("txtMiddleName");
		Date birthDate = getRequestDateTime("txtBirthDate", "MM/dd/yyyy");
		
		supplier.setLastName(lastName);
		supplier.setFirstName(firstName);
		supplier.setMiddleName(middleName);
		supplier.setBirthDate(birthDate);
	}
	
	protected void validateInput() {
		SupplierDTO supplier = (SupplierDTO) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER);
		if(StringUtil.isEmpty(supplier.getLastName())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Last Name");
		}
		else if(StringUtil.isEmpty(supplier.getFirstName())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "First Name");
		}
		else if(StringUtil.isEmpty(supplier.getMiddleName())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Middle Name");
		}
		else if(supplier.getBirthDate() == null) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Birthday");
		}
	}
}
