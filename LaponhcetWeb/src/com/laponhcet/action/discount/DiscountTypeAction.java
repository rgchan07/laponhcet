package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicSectionDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class DiscountTypeAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setInput() {
		DiscountTypeDTO discountType = (DiscountTypeDTO) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
		boolean isSchoolTermAcademicYear = discountType.getSchoolTerm().equalsIgnoreCase(SettingsUtil.SCHOOL_TERM_ACADEMIC_YEAR);
	
		discountType.setSchoolTerm(getRequestString("rbSchoolTerm"));
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
		discountType.setName(getRequestString("txtName"));
		discountType.setPercentage(getRequestBoolean("rbPercentage"));

		 if (discountType.isPercentage()) {
		      for (DTOBase discountTypePercentageObj : discountType.getDiscountTypePercentageList()) {
		        DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountTypePercentageObj;
				  double percent = getRequestDouble("txt" + discountTypePercentage.getFee().getCode());
			        if (percent > 0.0D) {
			        	discountTypePercentage.setPercent(percent);
			        }
			        else {
			        	discountTypePercentage.setPercent(0.0D);
			        }
		      }
		    }
	}
	 protected void validateInput() {
//		 if (!sessionInfo.isCurrentLinkDeleteSubmit()) {
//			 DiscountTypeDTO discountType = (DiscountTypeDTO)getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
//			 if (StringUtil.isEmpty(discountType.getName())) {
//				 actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");  
//			 }
//			 else {
//			      DiscountTypeDTO existing =new DiscountTypeDAO().getDiscountTypeByName(discountType.getName());
//			      if (sessionInfo.isPreviousLinkAdd()) {
//			        if (existing != null) {
//			        	 actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");  
//			        }
//			      }
//			      else {
//			        DiscountTypeDTO orig = (DiscountTypeDTO)getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE+"_ORIG");
//			        if ((!orig.getName().equalsIgnoreCase(discountType.getName())) && 
//			          (existing != null)) {
//			        	 actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");  
//			        }
//				}
//			}
//		}
	}
}