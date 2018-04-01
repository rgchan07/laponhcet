package com.laponhcet.action.guest.register;
import java.io.IOException;
import org.json.JSONObject;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DAOBase;

public class RegisterConfirmAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void executeLogic() {
		JSONObject jsonObj = getJsonObj();
		execute(RegisterDTO.SESSION_REGISTER, new RegisterDAO(), DAOBase.DAO_ACTION_ADD, jsonObj);
		if(actionResponse.getType().equalsIgnoreCase(ActionResponse.TYPE_SUCCESS)) {
			actionResponse.constructMessage(actionResponse.getType(), "Registered. Email had been sent for your confirmation", jsonObj);
		}
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}