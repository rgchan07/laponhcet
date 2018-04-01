package com.laponhcet.action.academicprogram;


import com.mytechnopal.PaginationData;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;

import java.util.ArrayList;
import java.util.List;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.AcademicProgramSubgroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicProgramSubgroupDTO;

public class ListAcademicProgramAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0024", "US0029"}, new String[] {"US0025", "US0030", "US0032"}, new String[] {"US0026", "US0031", "US0033"}, "US0027", "US0028");
		if((!sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit())) {
			PaginationData paginationData = new PaginationData();
			List<DTOBase> academicProgramList = new AcademicProgramDAO().getAcademicProgramList();
			paginationData.setName(AcademicProgramDTO.SESSION_PAGINATION_ACADEMIC_PROGRAM_lIST);
			paginationData.setSearchCriteria(AcademicProgramDTO.ACADEMIC_PROGRAM_PAGINATION_SEARCH_CRITERIA_LIST[0]);
			paginationData.setColumnNameList(new String[] {"Code","Name", "Description", ""});
			paginationData.setColumnWidthList(new String[] {"15","25", "47", "13"});	
			paginationData.setAjaxLinkCode(sessionInfo.getPaginationDataLink().getCode());
			paginationData.setRecordListUnfiltered(academicProgramList);
			paginationData.setRecordList(academicProgramList);
			paginationData.setAjaxResultDetailsList(new String[] {"code", "name", "description", "button"});
			setPaginationRecord(paginationData, sessionInfo);
			setSessionAttribute(AcademicProgramDTO.SESSION_PAGINATION_ACADEMIC_PROGRAM_lIST, paginationData);

			List<DTOBase> userList = new UserDAO().getUserListByUserGroupCode(UserGroupDTO.USER_GROUP_TEACHER_CODE);
			List<DTOBase> teacherList = new ArrayList<DTOBase> ();
			for(DTOBase obj : userList){
				UserDTO userTeacher = (UserDTO) obj;
				userTeacher.setDisplayText(userTeacher.getName(true, false, true));
				teacherList.add(userTeacher);
			}
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST,  new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramSubgroupDTO.SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST, new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList());
			setSessionAttribute(UserDTO.SESSION_USER_LIST, teacherList);
			setSessionBeforeTrans(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, new AcademicProgramDTO());
		}
	}
	
	private void setPaginationRecord(PaginationData paginationData, SessionInfo sessionInfo) {
		for(DTOBase dto: paginationData.getCurrentPageRecordList()) {
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) dto;
			academicProgram.setPaginationRecord(new String[]{academicProgram.getCode(), academicProgram.getName(), academicProgram.getDescription(), AcademicProgramUtil.getRecordButtonStr(sessionInfo, academicProgram)});
		}
	}	
}

