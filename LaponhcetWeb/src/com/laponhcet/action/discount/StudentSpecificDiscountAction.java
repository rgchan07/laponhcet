package com.laponhcet.action.discount;

import java.util.List;

import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.util.DiscountTypeUtil;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;

public class StudentSpecificDiscountAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	
	protected void setInput() {
		DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT);
		List<DTOBase> semesterList = (List<DTOBase>) getSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
		List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
		List<DTOBase> discountTypeList = (List<DTOBase>) getSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_LIST);
		
		if(discount.getStudent().getAcademicProgram().getAcademicProgramGroup().getCode().equalsIgnoreCase(AcademicProgramGroupDTO.ACADEMIC_PROGRAM_GROUP_CODE_BASICEDUCATION)){
			System.out.println("AcademicProgramGroup:" + discount.getStudent().getAcademicProgram().getAcademicProgramGroup().getCode());
		int academicYearId = getRequestInt("cboAcademicYear");
		if (academicYearId > 0) {
			 discount.setAcademicYear((AcademicYearDTO) DTOUtil.getObjById(academicYearList, academicYearId));
		}
		}else{
		 int semesterId = getRequestInt("cboSemester");
		 if (semesterId > 0) {
			 discount.setSemester((SemesterDTO) DTOUtil.getObjById(semesterList, semesterId));
		 }
		}
		
		int discountTypeId = getRequestInt("cboDiscountType");
	  
	    if (discountTypeId > 0) {
	      discount.setDiscountType((DiscountTypeDTO)DTOUtil.getObjById(discountTypeList, discountTypeId));
	      discount.getDiscountType().setDiscountTypePercentageList(DiscountTypeUtil.getDiscountTypePercentageListByDiscountTypeCode(discount.getDiscountType().getCode()));
	       discount.setAmount(0.0D);
	      }
	      else {
	    	  if(discount.getDiscountType().isPercentage()){
	        discount.setAmount(getRequestDouble("txtAmount"));
	      }
	    }
		
		discount.setRemark(getRequestString("txtRemarks", true));

	}
}
