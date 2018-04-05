package com.laponhcet.action.discount;


import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.util.DTOUtil;

public class SelectStudentSpecificDiscountSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		 DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) getSessionAttribute(DiscountStudentSpecificDTO.SESSION_DISCOUNT);
			System.out.println(discount);
			Pagination studentPagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
			StudentDTO student = StudentUtil.getStudent(DTOUtil.getObjByCode(studentPagination.getRecordList(), getRequestString("txtSelectedRecord")));
			System.out.println("RecordListSize: " + studentPagination.getRecordList().size());
			System.out.println("SelectedValue: "+ getRequestInt("txtSelectedRecord"));
			System.out.println("Is Student null?: "+ student == null);
			discount.setStudent(student);

			sessionInfo.setTransitionLink(new String[] {"US0178", "US0173", "US0179"}, new String[] {"US0174", "US0180", "US0182"}, new String[] {"US0175", "US0181", "US0183"}, "US0176", "US0177");
			sessionInfo.setCurrentLink(sessionInfo.getAddLink());
	
	}
}

