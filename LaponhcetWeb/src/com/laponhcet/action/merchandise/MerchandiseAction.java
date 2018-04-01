package com.laponhcet.action.merchandise;

import java.util.List;

import org.json.JSONObject;

import com.laponhcet.dao.MerchandiseDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.dto.UnitDTO;
import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class MerchandiseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setInput() {
		if(!sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("UU0002") || !sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("UM0002")) {
			MerchandiseDTO merchandise = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
			String code = getRequestString("txtCode");
			String name = getRequestString("txtName");
			String location = getRequestString("txtLocation");
			double beginningBalance = getRequestDouble("txtBeginningBalance");
			double qtyOnStock = getRequestDouble("txtQuantityOnStock");
			UnitDTO unit = ((UnitDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(UnitDTO.SESSION_UNIT_LIST), getRequestInt("cboUnit")));
			double unitPrice = getRequestDouble("txtUnitPrice");
			String costingMethod = getRequestString("txtCostingMethod");
			double nrv = getRequestDouble("txtNrv");
			double qtyReorder = getRequestDouble("txtQtyReorder");
			SupplierDTO supplier = ((SupplierDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(SupplierDTO.SESSION_SUPPLIER_LIST), getRequestInt("cboSupplier")));
			
			merchandise.setCode(code);
			merchandise.setName(name);
			merchandise.setLocation(location);
			merchandise.setQtyBeginning(beginningBalance);
			merchandise.setQtyOnStock(qtyOnStock);
			merchandise.setUnit(unit);
			merchandise.setUnitPrice(unitPrice);
			merchandise.setCostingMethod(costingMethod);
			merchandise.setNrv(nrv);
			merchandise.setQtyReorder(qtyReorder);
			merchandise.setSupplier(supplier);
		}
	}

	protected void validateInput() {
	/*"UM0002 is AddSupplierAction Sir"*/
	/*If I uncomment that line of code, the prompt is keep on showing Sir.*/
	if(!sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("UU0002")/*||!sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("UM0002")*/) {
		if(!sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("UI0005")){
		System.out.println(sessionInfo.getCurrentLink().getCode());
			MerchandiseDTO merchandise = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
				ActionResponse actionResponse = super.actionResponse;
				if(StringUtil.isEmpty(merchandise.getCode())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Code");	
				}
				else if(StringUtil.isEmpty(merchandise.getName())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
				}
				else if(StringUtil.isEmpty(merchandise.getLocation())) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Location");
				}
				else if (merchandise.getQtyBeginning() == 0) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Beginning Balance");
				}
				else if(merchandise.getQtyBeginning() < 1) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Beginning Balance should be at least equal to 1");
				}
				else if(merchandise.getQtyOnStock() == 0) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Quantity on Stock");
				}
				else if(merchandise.getQtyOnStock() < 1) {
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Quantity on Stock should be at least equal to 1");
				}
				else if(merchandise.getUnitPrice() == 0) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Unit Price");
				}
				else if(merchandise.getQtyReorder() == 0) {
					actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Quantity Reorder");
				}
				else {
					MerchandiseDTO merchandiseCodeExist = new MerchandiseDAO().getMerchandiseByCode(merchandise.getCode());
					MerchandiseDTO merchandiseNameExist = new MerchandiseDAO().getMerchandiseByName(merchandise.getName());
					
					if(sessionInfo.isPreviousLinkAdd()) {
						if(merchandiseNameExist!=null){
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
						}
						if(actionResponse.isSuccess() && merchandiseCodeExist!=null) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
						}
						if(actionResponse.isSuccess() && merchandise.getQtyBeginning() != merchandise.getQtyOnStock()) {
							actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Beginning Balance and Quantity on Stock must be EQUAL, unless during update.");
						}
					}
					else if(sessionInfo.isPreviousLinkUpdate()) {
						MerchandiseDTO merchandiseOrig = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE + "_ORIG");
						if(merchandiseCodeExist!=null) {					
							if(!merchandiseOrig.getCode().equalsIgnoreCase(merchandise.getCode())) {
								actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
							}
						}
						if(actionResponse.isSuccess() && merchandiseNameExist!=null) {
							if(!merchandiseOrig.getName().equalsIgnoreCase(merchandise.getName())) {
								actionResponse.constructMessage(ActionResponse.TYPE_INFO, "Duplicate of Name");
							}
						}
					}
				}
			}
		}
	}	
}