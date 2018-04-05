package com.laponhcet.action.discount;

import java.util.List;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.DiscountTypeDAO;

import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.SemesterDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.DiscountStudentSpecificUtil;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;

public class AddStudentSpecificDiscountAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0178", "US0173", "US0179"}, new String[] {"US0174", "US0180", "US0182"}, new String[] {"US0175", "US0181", "US0183"}, "US0176", "US0177");
			Pagination pagination = new Pagination();
			List<DTOBase> studentList = StudentUtil.getStudentList(new StudentDAO().getStudentList());
			pagination.setName(StudentDTO.SESSION_STUDENT_PAGINATION);
			pagination.setSearchCriteria(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] { "Code", "Last Name","First Name", "Middle Name", "Program", ""});
			pagination.setColumnWidthList(new String[] { "18","18" ,"18", "18", "18", "10"});	
			pagination.setAjaxLinkCode("US0184");
			pagination.setRecordListUnfiltered(studentList);
			pagination.setRecordList(studentList);
			pagination.setAjaxResultDetailsList(new String[] { "code", "lastName", "firstName", "middleName", "program", "button"});
			DiscountStudentSpecificUtil.setStudentPaginationRecord(sessionInfo, pagination, (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST));
			setSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION, pagination);
			
		if(!sessionInfo.isCurrentLinkAddSubmit() && !sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("US0185")){
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(UserDTO.SESSION_USER_LIST, new UserDAO().getUserListByUserGroupCode(UserGroupDTO.USER_GROUP_STUDENT_CODE));
			setSessionAttribute(StudentDTO.SESSION_STUDENT, new StudentDTO());
			setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST, new AcademicYearDAO().getAcademicYearList());
			setSessionAttribute(SemesterDTO.SESSION_SEMESTER_LIST, new SemesterDAO().getSemesterList());
			setSessionAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE_LIST, new DiscountTypeDAO().getDiscountTypeList());
			setSessionBeforeTrans(DiscountStudentSpecificDTO.SESSION_DISCOUNT, new DiscountStudentSpecificDTO());
			
		}
	}
}
		
