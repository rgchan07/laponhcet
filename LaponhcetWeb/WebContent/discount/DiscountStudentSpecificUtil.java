package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.DiscountStudentSpecificDAO;
import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicSectionDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;


public class DiscountStudentSpecificUtil  implements Serializable {
	private static final long serialVersionUID = 1L;


	public static void setStudentPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			
			StudentDTO student = (StudentDTO) dto;
			student.setPaginationRecord(new String[]{ student.getCode(), student.getLastName(), student.getFirstName(), student.getMiddleName(),  DiscountStudentSpecificUtil.getSelectStudentButtonStr(student.getCode())});
		}
	}
	
	public static String getSelectStudentButtonStr(String studentCode){
		StringBuffer str = new StringBuffer();
		str.append("<button class=\"btn btn-sm btn-primary pull-right m-t-n-xs\" onclick=\"discountStudentAction('" + studentCode + "', 'US0185')\"><strong>Select</strong></button");
		return str.toString();
		
	}
	
	public static List<DTOBase> getAYSemester(List<DTOBase>  semesterList) {
		for(DTOBase obj: semesterList){
			SemesterDTO semester = (SemesterDTO) obj;
			AcademicYearDTO academicYear = new AcademicYearDAO().getAcademicYearByCode(semester.getAcademicYear().getCode());
			semester.setDisplayText(academicYear.getName() + semester.getDescription2());
		}
		return semesterList;
	}
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) dto;
			StudentDTO student = new StudentDAO().getStudentByCode(discount.getStudent().getCode());
			DiscountTypeDTO discountType = new DiscountTypeDAO().getDiscountTypeByCode(discount.getDiscountType().getCode());
			discount.setPaginationRecord(new String[]{ discount.getSemester().getDescription1(), discount.getStudent().getCode(), 
			student.getLastName(), student.getFirstName(),  student.getMiddleName(), discountType.getName(), student.getAcademicProgram().getName(),  pagination.getLinkButtonStr(sessionInfo, discount.getId()).replace("~", ",")});
		}
	}
	

	
	
	 public static List<DTOBase> searchByStudent(SemesterDTO semester, String searchValue) {
		    List<DTOBase> userList = new UserDAO().getUserListByUserGroupCodeSearchByNameCode("2", searchValue);
		    List<DTOBase> discountList = new ArrayList<DTOBase>();
		    for (DTOBase userObj : userList) {
		      UserDTO user = (UserDTO)userObj;
		      DiscountStudentSpecificDTO discount = new DiscountStudentSpecificDAO().getDiscountBySemesterCodeStudentCode(semester.getCode(), user.getCode());
		      if (discount != null) {
		        discount.setStudent(StudentUtil.getStudent(userObj));
		        discountList.add(discount);
		      }
	}
			return discountList;
	 }
}