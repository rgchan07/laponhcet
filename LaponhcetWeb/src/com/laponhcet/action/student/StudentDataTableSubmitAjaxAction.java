package com.laponhcet.action.student;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class StudentDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
 		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			// Search By Name Code
			if(searchCriteria.equalsIgnoreCase(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) { 
				List<DTOBase> userListByCodeName = new UserDAO().getUserListByUserGroupCodeSearchByNameCode(UserGroupDTO.USER_GROUP_STUDENT_CODE, searchValue);
				pagination.setRecordList(StudentUtil.getStudentListByUserList(pagination, userListByCodeName, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST)));
			}
			/*// Search By Academic Program
			else if(searchCriteria.equalsIgnoreCase(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[1])) {
				List<DTOBase> studentListByAcademicProgramCode = new StudentDAO().getStudentListSearchByAcademicProgramCode(searchValue);
				pagination.setRecordList(StudentUtil.getStudentListByAcademicProgram(userList, studentListByAcademicProgramCode));
			}*/
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
		int currentPageTotalRecord = pagination.getCurrentPageRecordList().size();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("totalRecord", pagination.getRecordList().size());
			jsonObj.put("totalPage", pagination.getTotalPage());
			jsonObj.put("currentPage", pagination.getCurrentPage());
			jsonObj.put("recordPerPage", pagination.getRecordPerPage());
			jsonObj.put("currentPageTotalRecord", currentPageTotalRecord);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<currentPageTotalRecord; i++) {
			StudentDTO student = (StudentDTO) pagination.getCurrentPageRecordList().get(i);
			UserDTO user = new UserDAO().getUserByCode(student.getCode());
			String profilePict = StringUtil.isEmpty(user.getProfilePict())?UserDTO.PROFILE_PICT_DEFAULT:user.getProfilePict();
			StringBuffer strBuff = new StringBuffer();	
			strBuff.append("<div class='ibox'>");
			strBuff.append("	<div class='ibox-content product-box'>");
			strBuff.append("		<div class='product-imitation' style=\"background-image: url('" + profilePict + "');background-size: 100% auto;\"></div>");
			strBuff.append("		<div class='product-desc'>");
			strBuff.append("			<span class='product-price'>" + student.getAcademicProgram().getCode() + "</span>");
			strBuff.append("			<a href='#' class='product-name'>" + student.getLastName());
			strBuff.append("				<font class='product-name-sub'>" + student.getFirstName() + ' ' + student.getMiddleName() + "</font>");
			strBuff.append("			</a>");
			strBuff.append("			<div class='m-t-xs'>");
			strBuff.append("				<i class='fa fa-id-card-o'></i>&nbsp;&nbsp;&nbsp;" + student.getCode() + "<br />");
			strBuff.append("				<i class='fa fa-calendar'></i>&nbsp;&nbsp;&nbsp;&nbsp;" + DateTimeUtil.getDateTimeToStr(student.getBirthDate(), "MMMM dd, YYYY") + "\r\n");
			strBuff.append("			</div>");
			strBuff.append("			<div class='m-t text-center'>");
			strBuff.append("				<a href='#' class='btn btn-xs btn-primary'>View <i class='fa fa-long-arrow-right'></i></a>");
			strBuff.append("				<a href='#' class='btn btn-xs btn-success m-l-xs m-r-xs'>Update <i class='fa fa-pencil'></i></a>");
			strBuff.append("				<a href='#' class='btn btn-xs btn-danger'>Delete <i class='fa fa-trash'></i></a>");
			strBuff.append("			</div>");
			strBuff.append("		</div>");
			strBuff.append("	</div>");
			strBuff.append("</div>");		
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put(Pagination.PAGINATION_GRID_DATA, strBuff.toString());
				jsonArray.put(jsonObjDetails);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			jsonObj.put("details", jsonArray);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

