package com.laponhcet.action.merchandise;

import com.laponhcet.dto.MerchandiseDTO;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteMerchandiseSubmitAction extends MerchandiseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			PaginationData paginationData = (PaginationData) getSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + MerchandiseDTO.SESSION_PAGINATION_NAME);
			DTOBase obj = DTOUtil.getObjById(paginationData.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			MerchandiseDTO merchandise = (MerchandiseDTO) obj;
			setSessionBeforeTrans(MerchandiseDTO.SESSION_MERCHANDISE, merchandise.getMerchandise());
		}
	}
}
