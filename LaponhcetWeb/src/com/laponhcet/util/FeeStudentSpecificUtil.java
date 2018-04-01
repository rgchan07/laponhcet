package com.laponhcet.util;

import java.io.Serializable;

import com.laponhcet.dao.FeeDAO;

import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.FeeStudentSpecificDTO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class FeeStudentSpecificUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) dto;
			FeeDTO fee = new FeeDAO().getFeeByCode(feeStudentSpecific.getFee().getCode());
			feeStudentSpecific.setPaginationRecord(new String[]{fee.getName(), StringUtil.getFormattedNum(feeStudentSpecific.getAmount(), "0.00"), getRecordButtonStr(sessionInfo, feeStudentSpecific)});
		}
	}
	
	public static void setPaginationRecordFeeStudentSpecificStudentPagination(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			StudentDTO student = (StudentDTO) dto;
			student.setPaginationRecord(new String[]{student.getCode(), student.getLastName(), student.getFirstName(), student.getMiddleName(), getRecordButtonStrForUser(sessionInfo, student)});
		}
	}
	
	public static String getRecordButtonStr(SessionInfo sessionInfo, FeeStudentSpecificDTO feeStudentSpecific){
		StringBuffer str = new StringBuffer();
		str.append("<button class='fa fa-times btn-rounded btn-outline btn btn-danger m-l-xs' onclick=\"recordAction('" + feeStudentSpecific.getId() + "','" + sessionInfo.getDeleteSubmitLink().getCode() +  "')\"></button>");
		return str.toString();
	}
	
	public static String getRecordButtonStrForUser(SessionInfo sessionInfo, StudentDTO student){
		StringBuffer str = new StringBuffer();
		str.append("<button class='fa fa-pencil-square-o btn-rounded btn-outline btn btn-primary m-l-xs' onclick=\"recordAction('" + student.getId() + "','US0164')\"></button>");
		return str.toString();
	}
	
	public static String getStudentName(StudentDTO student) {
		String name = student.getCode().isEmpty()?"":student.getName(true, false, true);
		return name;
	}
	
	/*public static List<DTOBase> getStudentListByCodeName(List<DTOBase> userListByCodeName, List<DTOBase> studentList){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase obj : userListByCodeName){
			UserDTO user = (UserDTO) obj;
			StudentDTO student = (StudentDTO) DTOUtil.getObjByCode(studentList, user.getCode());
			newStudentList.add(setStudent(student, user));
		}
		return newStudentList;
	}*/
}
