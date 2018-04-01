package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		DiscountTypeDTO discountType = (DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
		discountType.setSchoolTerm(getRequestString("rbSchoolTerm"));
		boolean isSchoolTermAcademicYear = discountType.getSchoolTerm().equalsIgnoreCase(SettingsUtil.SCHOOL_TERM_ACADEMIC_YEAR);
		if(isSchoolTermAcademicYear) {
			if(getRequestInt("cboAcademicYear") > 0) {
				discountType.setAcademicYear((AcademicYearDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST), getRequestInt("cboAcademicYear")));
			}
		}
		else {
			if(getRequestInt("cboSemester") > 0) {
				discountType.setSemester((SemesterDTO) DTOUtil.getObjById((List<DTOBase>) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST), getRequestInt("cboSemester")));
			}
		}
		
		String name = getRequestString("txtName");
		boolean isPercentage = getRequestBoolean("rbPercentage");
		
		discountType.setName(name);
		discountType.setPercentage(isPercentage);
		
		if(discountType.isPercentage()) {
			for(DTOBase discountTypePercentageObj: discountType.getDiscountTypePercentageList()) {
				DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) discountTypePercentageObj;
				double percent = getRequestDouble("txt" + discountTypePercentage.getFee().getCode());
				if(percent > 0) {
					discountTypePercentage.setPercent(percent);
				}
				else {
					discountTypePercentage.setPercent(0d);
				}
			}
		}
	}
	
	protected void validateInput() {
		/*DiscountTypeDTO discountType = (DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNTTYPE);
		if(StringUtil.isEmpty(discountType.getName())) {
			message.constructMsg(Message.MSG_CLASS_EMPTY, "Name");
		}
		else {
			DiscountTypeDTO existing = DiscountTypeUtil.getDiscountTypeByName(new DiscountTypeDAO().getDiscountTypeList(), discountType.getName());
			if(sessionInfo.isPreviousLinkAdd()) {
				if(existing != null) {
					message.constructMsg(Message.MSG_CLASS_EXIST, "Name");
				}
			}
			else {
				DiscountTypeDTO orig = (DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNTTYPE +"_ORIG");
				if(!orig.getName().equalsIgnoreCase(discountType.getName())) {
					if(existing != null) {
						message.constructMsg(Message.MSG_CLASS_EXIST, "Name");
					}
				}
			}
		}*/
	}
}