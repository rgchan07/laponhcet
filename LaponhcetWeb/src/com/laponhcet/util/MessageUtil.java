package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.AcademicProgramSubgroupDAO;
import com.laponhcet.dao.MessageTypeDAO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageTypeDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserGroupDAO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;
import com.mytechnopal.webcontrol.CheckBoxWebControl;

public class MessageUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static Boolean isMessageTypeCodeExist(String messageTypeCodes, String messageTypeCode){
		boolean isExisting = false;
		if(!messageTypeCodes.isEmpty()){
			String[] messageTypeArr = messageTypeCodes.split("~");
			for (int i=0; i < messageTypeArr.length ; i++){
				if(messageTypeArr[i].equalsIgnoreCase(messageTypeCode)){
					isExisting = true;
				}
			}
		}
		return isExisting;
	}
	

	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> messageTypeList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			MessageDTO message = (MessageDTO) dto;
			message.setPaginationRecord(new String[] {getMessageTypeCodeDescription(messageTypeList, message.getMessageTypeCodes()), message.getContent(), pagination.getLinkButtonStr(sessionInfo, message.getId()).replace("~", ",")});
		}
	}
	
	public static String getMessageTypeCodeDescription(List<DTOBase> messageTypeList, String messageTypeCodes){
		String messageTypeDescription = "NONE";
		if(!messageTypeCodes.isEmpty()){
			String[] messageTypeArr = messageTypeCodes.split("~");
			for (int i=0; i < messageTypeArr.length ; i++){
				MessageTypeDTO messageType = (MessageTypeDTO) DTOUtil.getObjByCode(messageTypeList, messageTypeArr[i]);
				messageTypeArr[i] = messageType.getDescription();
			}
			messageTypeDescription = WebUtil.getTable(messageTypeArr, 2);
		}
		return  messageTypeDescription;
	}
	
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> childObjList, String selectedValue , String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "  m-t\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" +panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable(new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName + selectedValue, childObjList, strArrCodes.split("~"), StringUtil.getStrArr(childObjList), "onchange=\"toggleCheckParent('chk" + panelName + selectedValue + "', " + childObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getPanelCheckboxJavaScriptForComboBoxSelection(List<DTOBase> objectList, String selectedCodes){
		StringBuffer str = new StringBuffer();
		StringBuffer strHideToggle = new StringBuffer();
		//Initializes the Group Filter Combobox
	    str.append("<script>");
	    str.append("getGroupFilterCheckbox(" + MessageUtil.getSelectedGroupFilterCheckbox(selectedCodes).getCode() +");");

	    // Toggles the Group Panel CheckList
	    str.append("function getGroupFilterCheckbox(code){");
	    // Hides all Group Panel CheckList
	    strHideToggle.append(" function toggleHideAllCheckList(){");
	    String ifFirstObject = "";
	    for(DTOBase messageTypeObj: objectList){
	    	MessageTypeDTO messageType = (MessageTypeDTO) messageTypeObj;
	    	ifFirstObject = objectList.get(0).getCode().equalsIgnoreCase(messageType.getCode())?"":"else ";
	    	str.append( ifFirstObject + "if (code == " + messageType.getCode() + ") {");
		    str.append(" 	toggleHideAllCheckList();");
		    str.append(" 	$(\"#div" + messageType.getDescription().replace(" ", "") +"CheckList\").show();");
		    str.append(" }");

		    strHideToggle.append(" $(\"#div" + messageType.getDescription().replace(" ", "") +"CheckList\").hide();");
	    }
	    strHideToggle.append("  }");
	    str.append(" else{");
	    str.append(" 	toggleHideAllCheckList();");
	    str.append(" }");
	    str.append(" }");
	    str.append(strHideToggle.toString());
	    str.append("</script>");
	    return str.toString();

	}
	
	public static Boolean isFilterByGroup(String messageTypeCodes){
		Boolean isFilterByGroup = true;
		if(!StringUtil.isEmpty(messageTypeCodes)){
			String[] messageTypeCodesArr = messageTypeCodes.split("|");
			if(!messageTypeCodesArr[0].equalsIgnoreCase(MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[0])){
				isFilterByGroup = false;
			}
		}
		return isFilterByGroup;
	}
	
	public static DTOBase getSelectedGroupFilterCheckbox(String messageTypeCodes){
		DTOBase messageTypeObj = new DTOBase();
		messageTypeObj.setCode("1");
		if(!StringUtil.isEmpty(messageTypeCodes)){
			String[] messageTypeCodesArr = messageTypeCodes.split("|");
			String firstGroupSelected = messageTypeCodesArr[1];
			messageTypeObj = DTOUtil.getObjByCode(new MessageTypeDAO().getMessageTypeList(), firstGroupSelected);
		}
		return messageTypeObj;
	}
	
	public static String getCheckedCodes(String messageTypeCodes, int groupIndex){
		String checkedCodes = "";
		if(!StringUtil.isEmpty(messageTypeCodes)){
			String[] messageTypeCodesArr = messageTypeCodes.split("|");
			if(!messageTypeCodesArr[groupIndex].equalsIgnoreCase("")){
				String[] groupCheckedCodes = messageTypeCodesArr[groupIndex].split("~");
				String[] newGroupCheckedCodes = new String[]{};
				for(int x=1; x<groupCheckedCodes.length;x++){
					newGroupCheckedCodes[x-1] = groupCheckedCodes[x];
				}
				checkedCodes = String.join("~", newGroupCheckedCodes);
			}
		}
		System.out.println("checkedCodes >>" + checkedCodes);
		return checkedCodes;
	}
	
	public static String getAddRecipientButton(String userCode){
		StringBuffer str = new StringBuffer();
		str.append("<i class=\"fa fa-plus-square fa-lg\" type=\"button\" onclick=\"addRecipient(\"" + userCode + "\")\"></i>&nbsp;&nbsp;");
		return str.toString();
	}
	
	public static List<DTOBase> getMessageTypeObjectList(String messageTypeCode){
		List<DTOBase> objectList = new ArrayList<DTOBase>();
		if(messageTypeCode.equalsIgnoreCase("1")){
			objectList = new UserGroupDAO().getUserGroupList(true);
		}else if(messageTypeCode.equalsIgnoreCase("2")){
			objectList = new AcademicProgramGroupDAO().getAcademicProgramGroupList();
		}else if(messageTypeCode.equalsIgnoreCase("3")){
			objectList = new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList();
		}
		return objectList;
	}
}
