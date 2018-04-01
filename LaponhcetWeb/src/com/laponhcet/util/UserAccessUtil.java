package com.laponhcet.util;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.UserUtil;

public class UserAccessUtil extends UserUtil {

	private static final long serialVersionUID = 1L;

	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name or Code"};
	
	public static String getRecordButtonStr(SessionInfo sessionInfo, UserDTO user){
		StringBuffer str = new StringBuffer();
		if(sessionInfo.getUpdateLink() != null){
			str.append("<button class='fa fa-pencil fa-sm btn-rounded btn-outline btn btn-success' title='Update' onclick=\"recordAction('" + user.getId() + "','" + sessionInfo.getUpdateLink().getCode() +  "')\"></button>");
		}else if(sessionInfo.getUpdateLink() != null){
		str.append("<button class='fa fa-trash btn-rounded btn-outline btn btn-danger m-l-xs' title='Delete' onclick=\"recordAction('" + user.getId() + "','" + sessionInfo.getDeleteSubmitLink().getCode()  +  "')\"></button>");
		}
		return str.toString();
	}
}
