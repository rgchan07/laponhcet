package com.laponhcet.action.academicprogram;

import java.util.List;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicProgramSubgroupDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class AcademicProgramAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM);
		List<DTOBase> academicProgramGroupList =  (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
		List<DTOBase> academicProgramSubroupList =  (List<DTOBase>) getSessionAttribute(AcademicProgramSubgroupDTO.SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST);
		List<DTOBase> userTeacherList =  (List<DTOBase>) getSessionAttribute(UserDTO.SESSION_USER_LIST);
		
		int academicProgramGroupId = getRequestInt("cboAcademicProgramGroup");
		if(academicProgramGroupId > 0) {
			academicProgram.setAcademicProgramGroup((AcademicProgramGroupDTO) DTOUtil.getObjById(academicProgramGroupList, academicProgramGroupId));
		}
		
		int academicProgramSubgroupId = getRequestInt("cboAcademicProgramSubgroup");
		if(academicProgramSubgroupId > 0) {
			academicProgram.setAcademicProgramSubgroup((AcademicProgramSubgroupDTO) DTOUtil.getObjById(academicProgramSubroupList, academicProgramSubgroupId));
		}
		
		int userTeacherId = getRequestInt("cboHeadUser");
		if(userTeacherId > 0) {
			academicProgram.setHeadUser((UserDTO) DTOUtil.getObjById(userTeacherList, userTeacherId));
		}
		
		academicProgram.setCode(getRequestString("txtCode"));
		academicProgram.setName(getRequestString("txtName"));
		academicProgram.setDescription(getRequestString("txtDescription"));
		academicProgram.setMajor(getRequestString("txtMajor"));
		academicProgram.setGraduationYearLevel(getRequestInt("txtGraduationYearLevel"));
		academicProgram.setAcademicTerm(getRequestString("rbAcademicTerm"));
		academicProgram.setLogo(getRequestString("txtLogo", true));
		System.out.println("academicProgram.getLogo(): " + academicProgram.getLogo());
	}
	
	protected void validateInput() {
		if(!sessionInfo.isCurrentLinkDeleteSubmit()){
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM);
			if(academicProgram.getAcademicProgramGroup().getId() == 0) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Group");
			}
			else if(academicProgram.getAcademicProgramSubgroup().getId() == 0) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Subgroup");
			}
			else if(StringUtil.isEmpty(academicProgram.getCode())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Code");
			}
			else if(StringUtil.isEmpty(academicProgram.getName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
			}
			else if(StringUtil.isEmpty(academicProgram.getDescription())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Description");
			}
			else if(academicProgram.getGraduationYearLevel() <= 0) {
				actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Graduation Year Level");
			}
			
			else {
				AcademicProgramDTO existingCode = new AcademicProgramDAO().getAcademicProgramByCode(academicProgram.getCode());
				AcademicProgramDTO existingName = new AcademicProgramDAO().getAcademicProgramByName(academicProgram.getName());
				if(sessionInfo.isPreviousLinkAdd()) {
					if(existingName != null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
					}else if(existingCode != null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
					}
				}
				else {
					AcademicProgramDTO academicProgramOrig = (AcademicProgramDTO) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM +"_ORIG");
					if(!academicProgramOrig.getName().equalsIgnoreCase(academicProgram.getName())) {
						if(existingName != null) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
						}
					}if(!academicProgramOrig.getCode().equalsIgnoreCase(academicProgram.getCode())) {
						if(existingCode != null) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
						}
					}
				}
			}
		}
	}
}

