package com.laponhcet.util;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.VenueDTO;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.DataAndSessionBase;

public class VenueUtil extends DataAndSessionBase {
	private static final long serialVersionUID = 1L;
	
	public static String getRecordButtonStr(SessionInfo sessionInfo, VenueDTO venue){
		StringBuffer str = new StringBuffer();
		str.append("<button class='fa fa-pencil fa-sm btn-rounded btn-outline btn btn-success' onclick=\"recordAction('" + venue.getId() + "','" + sessionInfo.getUpdateLink().getCode() +  "')\"></button>");
		str.append("<button class='fa fa-times btn-rounded btn-outline btn btn-danger m-l-xs' onclick=\"recordAction('" + venue.getId() + "','" + sessionInfo.getDeleteSubmitLink().getCode()  +  "')\"></button>");
		/*if(!merchandise.getStatus().equalsIgnoreCase("APPROVED")){
			str.append("<button class='fa fa-check btn-rounded btn-outline btn btn-info m-l-xs' onclick=\"recordAction('" + merchandise.getId() + "','U00142')\"></button>");
		}*/
		return str.toString();
	}
	
	public static List<DTOBase> getArrNameProgramGroupList(String code) {
		String[] codes = code.split("~");
		List<DTOBase> newListLink = new ArrayList<DTOBase>();
		for(int i=0; i < codes.length ; i++){
			newListLink.add(new AcademicProgramGroupDAO().getAcademicProgramGroupByName(codes[i]));
		}
		return newListLink;
	}
	
	public static String getNameList(List<DTOBase> list) {
		String name = "";
		for(DTOBase obj: list) {
			AcademicProgramGroupDTO apg = (AcademicProgramGroupDTO) obj;
			name += " | " + apg.getName();
		}
		return name.substring(3);
	}
	
	public static List<DTOBase> getCodes(List<DTOBase> list, AcademicProgramGroupDTO code) {
		return list;
	}
}
