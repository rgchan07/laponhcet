package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;

import com.laponhcet.util.DiscountStudentSpecificUtil;

import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;


public class ListStudentSpecificDiscountAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0178", "US0173", "US0179"}, new String[] {"US0174", "US0180", "US0182"}, new String[] {"US0175", "US0181", "US0183"}, "US0176", "US0177");
			Pagination pagination = null;
			if(!sessionInfo.isPreviousLinkView()  && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
			List<DTOBase> discountList = new DiscountStudentSpecificDAO().getDiscountList();
			pagination.setName(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
			pagination.setColumnNameList(new String[] {"AY/Semester", "Id", "Last Name", "First Name", "Middle Name", "Discount Type", "Program", ""});
			pagination.setColumnWidthList(new String[] { "10", "10","15", "12", "15","13", "10", "15"});
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(discountList);
			pagination.setRecordList(discountList);
			pagination.setAjaxResultDetailsList(new String[] {"aySemester", "id", "lastName", "firstName", "middleName", "discountType", "program", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_LIST, new DiscountStudentSpecificDAO().getDiscountList());
			setSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT, new DiscountStudentSpecificDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION);
		}
			DiscountStudentSpecificUtil.setPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST));
			setSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT_PAGINATION, pagination);
	}

}
