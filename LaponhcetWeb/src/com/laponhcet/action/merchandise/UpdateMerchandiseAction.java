package com.laponhcet.action.merchandise;

import com.laponhcet.dto.MerchandiseDTO;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateMerchandiseAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			PaginationData paginationData = (PaginationData) getSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + MerchandiseDTO.SESSION_PAGINATION_NAME);
			DTOBase obj = DTOUtil.getObjById(paginationData.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			MerchandiseDTO merchandise = (MerchandiseDTO) obj;
			
			setSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE + "_ORIG", merchandise);
			setSessionBeforeTrans(MerchandiseDTO.SESSION_MERCHANDISE, merchandise.getMerchandise());
			}
		}
}
