package com.laponhcet.util;


import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.DataAndSessionBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.DateTimeUtil;

public class AcademicYearUtil extends DataAndSessionBase {
	private static final long serialVersionUID = 1L;
	
	/*public static List<DTOBase> getAcademicYearListSearchByAcademicProgram(String searchValue) {
		List<DTOBase> academicYearList = new ArrayList<DTOBase>();
		List<DTOBase> academicProgramList = new AcademicProgramDAO().getAcademicProgramListSearchByCodeName(searchValue);
		
		for(DTOBase obj: academicProgramList){
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
			academicYearList = DTOUtil.addObjList(new AcademicYearDAO().getAcademicYearListByProgram(academicProgram.getCode()), academicYearList);
			
		}
		return academicYearList;	
	}*/
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			AcademicYearDTO academicYear = (AcademicYearDTO) dto;
			academicYear.setPaginationRecord(new String[] {academicYear.getName(), DateTimeUtil.getDateTimeToStr(academicYear.getDateStart(), "MM-dd-yyyy"), DateTimeUtil.getDateTimeToStr(academicYear.getDateEnd(), "MM-dd-yyyy"), AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, academicYear.getAcademicProgramCodes()), pagination.getLinkButtonStr(sessionInfo, academicYear.getId()).replace("~", ",") });
		}
	}
}
