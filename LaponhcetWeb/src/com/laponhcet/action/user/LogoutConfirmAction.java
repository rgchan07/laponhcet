package com.laponhcet.action.user;

import com.laponhcet.util.SettingsUtil;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.LinkDAO;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;

public class LogoutConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void executeLogic() {
		sessionInfo = new SessionInfo();
		UserDTO user = new UserDAO().getUserByCode(SettingsUtil.GUEST_CODE);
		resetSessionInfo(new LinkDAO().getLinkByCode(SettingsUtil.GUEST_HOME_LINK_CODE), user, new LinkDAO().getLinkByCode(SettingsUtil.GUEST_HOME_LINK_CODE));
	}
}
