package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicProgramDTO;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.WebUtil;

public class AcademicProgramUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static List<DTOBase> getAcademicProgramListByAcademicProgramGroupCode(List<DTOBase> academicProgramList, String academicProgramGroupCode) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase obj: academicProgramList) {
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
			if(academicProgram.getAcademicProgramGroup().getCode().equalsIgnoreCase(academicProgramGroupCode)) {
				resultList.add(academicProgram);
			}
		}
		return resultList;
	}
	
	public static String getAcademicProgramCodes(List<DTOBase> academicProgramList, String academicProgramCodes){
		String academicProgramStr = "";
		if(!academicProgramCodes.isEmpty()) {
			String[] academicProgramArr = academicProgramCodes.split("~");
			for (int i=0; i < academicProgramArr.length ; i++){
				AcademicProgramDTO academicProgram = (AcademicProgramDTO) DTOUtil.getObjByCode(academicProgramList, academicProgramArr[i]);
				if(academicProgramStr.length() >= 1) {
					academicProgramStr += "~";
				}
				academicProgramStr += academicProgram.getDisplayText();
			}
		}
		return WebUtil.getTable("table", academicProgramStr.split("~"), 2);
	}
}
