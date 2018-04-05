package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListDiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00047", "U00042", "U00048" }, new String[] { "U00043", "U00049", "U00051" }, new String[] { "U00044", "U00050", "U00052" }, "U00045", "U00046");
			Pagination pagination = null;
			if(!sessionInfo.isPreviousLinkView()  && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> discountTypeList = new DiscountTypeDAO().getDiscountTypeList();
			pagination.setName(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
			pagination.setSearchCriteria(DiscountTypeDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Name", "Percentage", ""});
			pagination.setColumnWidthList(new String[] {"50", "25","25" });
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(discountTypeList);
			pagination.setRecordList(discountTypeList);
			pagination.setAjaxResultDetailsList(new String[] { "name", "percentage", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			
			setSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_LIST, new DiscountTypeDAO().getDiscountTypeList());
			setSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, new DiscountTypeDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION);
		}
			DiscountTypeUtil.setPaginationRecord(sessionInfo, pagination);
			setSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_PAGINATION, pagination);
	}

}
