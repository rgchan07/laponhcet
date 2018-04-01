package com.laponhcet.action.message;

import java.util.List;

import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageTypeDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class MessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		MessageDTO message = (MessageDTO) getSessionAttribute(MessageDTO.SESSION_MESSAGE);	
		List<DTOBase> messageTypeList = (List<DTOBase>) getSessionAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST);
		message.setContent(getRequestString("txtContent", true));
		message.setPriority(Integer.valueOf(getRequestString("cboPriority")));
		message.setValidTimestampStart(DateTimeUtil.getStrToDateTime(getRequestString("txtValidTimestampStart"), "MM/dd/yyyy"));
		message.setValidTimestampEnd(DateTimeUtil.getStrToDateTime(getRequestString("txtValidTimestampEnd"), "MM/dd/yyyy"));

		String messageTypeListArr = "";
		for(int i = 0; i < messageTypeList.size(); i++) {
			MessageTypeDTO messageType = (MessageTypeDTO) messageTypeList.get(i);
			System.out.println(" messageType.getDescription() >> " +  messageType.getDescription().replace(" ", ""));
			List<DTOBase> objectList = MessageUtil.getMessageTypeObjectList(messageType.getCode());
			if(objectList.size() >= 1) {
				messageTypeListArr += getSelectedCheckBox(objectList, messageType.getDescription().replace(" ", "") + messageType.getCode()) + (i!=messageTypeList.size()?"~":"");
			}
		}
		if(messageTypeListArr.length()>=1 && StringUtil.isEmpty(getRequestString("txtUserMessagerRecipientCodes"))){
			message.setMessageTypeCodes(MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[0] + "|" + String.join("~", messageTypeListArr));
		}else{
			message.setMessageTypeCodes(MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[1]);
		}
		message.setContentSMS(getRequestString("txaContentSMS", true));
		message.setContentWebHeadline(getRequestString("txtContentWebHeading", true));
		message.setContentFaceKeeper(getRequestString("txtContentFaceKeeper", true));
		System.out.println("message.getMessageTypeCodes() >>" + message.getMessageTypeCodes());
		
	}
	
	protected void validateInput() {
	}
}
