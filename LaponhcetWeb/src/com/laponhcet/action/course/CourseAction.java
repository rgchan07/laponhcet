package com.laponhcet.action.course;

import java.util.List;

import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.dto.CourseGroupDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class CourseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		CourseDTO course = (CourseDTO) getSessionAttribute(CourseDTO.SESSION_COURSE);
		List<DTOBase> courseGroupList = (List<DTOBase>) getSessionAttribute(CourseGroupDTO.SESSION_COURSE_GROUP_LIST);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> academicProgramGroupList = (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
		course.setCode(getRequestString("txtCode"));
		course.setDescription(getRequestString("txtDescription"));
		course.setPayUnit(getRequestDouble("txtPayUnit"));
		course.setCreditUnit(getRequestDouble("txtCreditUnit"));
		course.setLectureHour(getRequestDouble("txtLectureHour"));
		course.setLaboratoryHour(getRequestDouble("txtLaboratoryHour"));
		course.setAcademicProgramCodes(getRequestString("txtAcademicProgramCodes"));
		int courseGroupId = getRequestInt("cboCourseGroup");
		if(courseGroupId > 0) {
			course.setCourseGroup((CourseGroupDTO) DTOUtil.getObjById(courseGroupList, getRequestInt("cboCourseGroup")));
		}
		String academicProgramCodes = "";
		for(int i=0; i<academicProgramGroupList.size(); i++) {
			AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)academicProgramGroupList.get(i);
			String selectedAcademicProgramCodes = getSelectedCheckBox(AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode()), "AcademicProgram"+academicProgramGroup.getCode());
			if(selectedAcademicProgramCodes.length() >=1 && academicProgramCodes.length() >= 1) {
				academicProgramCodes += "~";
			}
			academicProgramCodes += selectedAcademicProgramCodes;
		}
		course.setAcademicProgramCodes(academicProgramCodes);
	}
	
	protected void validateInput() {
		CourseDTO course = (CourseDTO) getSessionAttribute(CourseDTO.SESSION_COURSE);
		if(StringUtil.isEmpty(course.getCode())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Code");
		}
		else if(StringUtil.isEmpty(course.getDescription())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Description");
		}
		else if(course.getPayUnit() < 0) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Pay Unit");
		}
		else if(course.getCreditUnit() <= 0) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Credit Unit");
		}
		else if(course.getLectureHour() < 0) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Lecture Hour");
		}
		else if(course.getLaboratoryHour() < 0) {
			actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Laboratory Hour");
		}
		else if(StringUtil.isEmpty(course.getAcademicProgramCodes())) {
			actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Program");
		}
		
		else{
			if(sessionInfo.isPreviousLinkAdd()){
				CourseDTO courseExist = (CourseDTO) new CourseDAO().getCourseByCode(course.getCode());
				if(courseExist!=null){
					actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
				}
			}else if(sessionInfo.isPreviousLinkUpdate()){
				CourseDTO courseOrig = (CourseDTO)getSessionAttribute(CourseDTO.SESSION_COURSE + "_ORIG");
				if(courseOrig.equals(course)){
					CourseDTO courseExist = (CourseDTO) new CourseDAO().getCourseByCode(course.getCode());
					if(courseExist!=null){
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Code");
					}
				}
			}
		}
	}
}
