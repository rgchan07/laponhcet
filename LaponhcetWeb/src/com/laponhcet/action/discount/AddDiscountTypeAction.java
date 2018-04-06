package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.DiscountTypePercentageUtil;
import com.laponhcet.util.DiscountTypeUtil;
import com.laponhcet.util.FeeUtil;
import com.laponhcet.util.SemesterUtil;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class AddDiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"U00047", "U00042", "U00048" }, new String[] { "U00043", "U00049", "U00051" }, new String[] { "U00044", "U00050", "U00052" }, "U00045", "U00046");
		if (!sessionInfo.isPreviousLinkAddSubmit()) {
		DiscountTypeDTO discountType = new DiscountTypeDTO();
		List<DTOBase> semesterList = new SemesterDAO().getSemesterList();
		discountType.setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByFeeListParent(FeeUtil.getFeeListParent(new FeeDAO().getFeeList())));
		setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST, new AcademicYearDAO().getAcademicYearList());
		setSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST, SemesterUtil.getSemesterAndAcademicYear(semesterList));
		setSessionAttribute(DiscountTypePercentageDTO.SESSION_DISCOUNT_TYPE_PERCENTAGE, new DiscountTypePercentageDTO());
		setSessionBeforeTrans(DiscountTypeDTO.SESSION_DISCOUNT_TYPE, discountType);
		}
	}
}
