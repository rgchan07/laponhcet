package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.util.DiscountStudentSpecificUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class UpdateStudentSpecificDiscountConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void init() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		if(actionResponse.isSuccess()) {
			DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT);
		Pagination pagination = (Pagination) getSessionAttribute( DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
		pagination.updateList((DiscountStudentSpecificDTO) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT), DAOBase.DAO_ACTION_UPDATE);
		DiscountStudentSpecificUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST) );
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), discount);
		//replaceObjById(pagination.getRecordList(), discount);
	}
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(DiscountStudentSpecificDTO.SESSION_DISCOUNT, new DiscountStudentSpecificDAO(), DAOBase.DAO_ACTION_UPDATE);
		actionResponse.constructMessage(ActionResponse.TYPE_FAIL, "fail");
	}

	
}

