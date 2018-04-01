package com.laponhcet.action.user;

import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dao.UserLinkDAO;
import com.mytechnopal.util.DTOUtil;


public class UpdateUserAccessConfirmAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ACCESS");
		Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), user);
		DTOUtil.replaceObjById(pagination.getRecordList(), user);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(UserDTO.SESSION_USER + "_ACCESS", new UserDAO(), DAOBase.DAO_ACTION_UPDATE);
		UserDTO user = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ACCESS");
		UserDTO userOrig = (UserDTO) getSessionAttribute(UserDTO.SESSION_USER + "_ORIG");
		if(!user.equals(userOrig)){
			new UserLinkDAO().executeDeleteList(userOrig.getUserLinkList());
			new UserLinkDAO().executeAddList(user.getUserLinkList());
		}else{
			actionResponse.constructMessage(ActionResponse.TYPE_INFO, "No changes has been done.");
		}
	}
}
