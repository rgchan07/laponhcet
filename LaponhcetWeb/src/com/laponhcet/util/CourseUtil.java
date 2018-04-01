package com.laponhcet.util;
import java.io.Serializable;
import java.util.List;

import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class CourseUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			CourseDTO course = (CourseDTO) dto;
			course.setPaginationRecord(new String[] {course.getCode(), course.getDescription(), StringUtil.getFormattedNum(course.getCreditUnit(), "0.0"), StringUtil.getFormattedNum(course.getPayUnit(), "0.0"), StringUtil.getFormattedNum(course.getLectureHour(), "0.0"), StringUtil.getFormattedNum(course.getLaboratoryHour(), "0.0"), course.getCourseGroup().getName(), AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, course.getAcademicProgramCodes()), pagination.getLinkButtonStr(sessionInfo, course.getId()).replace("~", ",")});
		}
	}
}
