package com.laponhcet.action.merchandise;

import com.laponhcet.dao.MerchandiseDAO;
import com.laponhcet.dto.MerchandiseDTO;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateMerchandiseConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		MerchandiseDTO merchandise = (MerchandiseDTO) getSessionAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
		PaginationData paginationData = (PaginationData) getSessionAttribute(PaginationData.SESSION_PAGINATIONDATA + "_" + MerchandiseDTO.SESSION_PAGINATION_NAME);
		DTOUtil.replaceObjById(paginationData.getRecordListUnfiltered(), merchandise);
		DTOUtil.replaceObjById(paginationData.getRecordList(), merchandise);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(MerchandiseDTO.SESSION_MERCHANDISE, new MerchandiseDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
}
