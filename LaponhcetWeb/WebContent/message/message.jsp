<%@page import="com.mytechnopal.dao.LinkDAO"%>
<%@page import="com.mytechnopal.base.WebControlBase"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>


<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination)session.getAttribute(UserDTO.SESSION_USER_PAGINATION);
	MessageDTO message = (MessageDTO) session.getAttribute(MessageDTO.SESSION_MESSAGE);
	MessageSMSDTO messageSMS =  (MessageSMSDTO) session.getAttribute(MessageSMSDTO.SESSION_MESSAGE_SMS);
	MessageIndividualDTO messageIndividual =  (MessageIndividualDTO) session.getAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL);
	List<DTOBase> messageTypeList = (ArrayList) session.getAttribute(MessageTypeDTO.SESSION_MESSAGE_TYPE_LIST);
	List<DTOBase> userList = (ArrayList) session.getAttribute(UserDTO.SESSION_USER_LIST);
	
	UserDTO userAccessSelected = (UserDTO) session.getAttribute(UserDTO.SESSION_USER);
	int firstLevelFilterCriteriaSelected = Integer.valueOf(MessageUtil.getSelectedGroupFilterCheckbox(message.getMessageTypeCodes()).getCode());
	
%>
<div class="col-lg-12 no-padding">
    <div class="ibox float-e-margins">
        <div class="ibox-content">	
         	<div class="row">
         		<div class="col-sm-3 no-padding">
	         		<%=new RadioButtonWebControl().getRadioButtonWebControl(sessionInfo, "col-sm-12", true, "Message By", "FirstLevelFilterCriteria", MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST, MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[firstLevelFilterCriteriaSelected], MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST, "", false) %>
	       			<div id="divGroupContent">
	       				<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-12", true, "Choose Group", "FilterGroupBy", messageTypeList , MessageUtil.getSelectedGroupFilterCheckbox(message.getMessageTypeCodes()), "", "", "onchange=\"getGroupFilterCheckbox(this.value)\"")%>
	       				<%
						for(DTOBase obj: messageTypeList) {
							MessageTypeDTO messageType = (MessageTypeDTO)obj;
							List<DTOBase> objectList = (ArrayList) MessageUtil.getMessageTypeObjectList(messageType.getCode());
							if(objectList.size() >= 1) {
						%>
		                	<%=MessageUtil.getPaneledCheckbox(sessionInfo, "col-sm-12", messageType.getDescription().replace(" ", ""), "panel panel-primary no-padding", true, messageType.getDescription(), objectList, message.getMessageTypeCodes(), String.join("~", StringUtil.getStrArr(objectList)) , 1, "") %>
						<%	
							}
						}
						%>
	       			</div>
	       			<div id="divIndividualContent">
	       				<input type="hidden" id="txtUserMessagerRecipientCodes" name="txtUserMessagerRecipientCodes" value="">
					   	<%//=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Search User", "Search User", "SearchUser", searchUserValue, 10, TextBoxWebControl.DATA_TYPE_STRING, "onblur=\"searchUser(this.value)\"")%>
	       				<%//=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12 m-b", false, "Search Recipient", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value);\"") %>
	       				<%//=new PaginationWebControl().getPaginationWebControl(sessionInfo, "col-sm-12", pagination, userAccessSelected.getId()) %>
	       			</div>
       			</div>
       			<div class="col-sm-9">
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4 no-padding", true, "Valid From", "Valid From", "ValidTimestampStart", DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4 ", true, "Valid Until", "Valid Until", "ValidTimestampEnd", DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4 no-padding", true, "Priority", "Priority", MessageDTO.PRIORITY_LIST, StringUtil.getStrFromStrArr(MessageDTO.PRIORITY_LIST, StringUtil.getNumToStr(message.getPriority(), false)), MessageDTO.PRIORITY_LIST, "", "", "") %>
					<input type="hidden" name="txtContent" id="txtContent" />
					<% if (!sessionInfo.isCurrentLinkAdd()){ %>
						<%="<div class='col-sm-12 no-padding m-t'><strong>Content</strong><div class='col-sm-12' style='background-color:#fdfcfc;border-radius:5px; padding:20px;border: 2px solid #f3f1f1;margin-top: 10px;'>" + message.getContent() + "</div></div>" %>
					<%	} else { %>
						<div class="col-sm-12 m-t no-padding">
							<div class="summernote"></div>
						</div>
					<%	} %>
					<div class="col-sm-12 no-padding m-t-lg">
					<strong>SMS</strong> <small><i>(Maximum of 160 characters)</i></small><br>
					<textarea class="m-t-xs" name="txaContentSMS" id="txaContentSMS" maxlength="160" style="width:100%;height:70px;border-color:#dedede;"><%=message.getContentSMS() %></textarea>
					</div>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12 no-padding", false, "Web Headline", "Web Headline", "ContentWebHeadline", message.getContentWebHeadline(), 100, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12 no-padding", false, "FaceKeeper", "FaceKeeper", "ContentFaceKeeper", message.getContentFaceKeeper(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-xl", "btn btn-primary", "align='center'") %>
				</div>
       		</div>
        </div>
    </div>
</div>   

<script>
  $(document).ready(function(){
 	// Initialized SummerNote Editor
    $('.summernote').summernote();
   	// Sets focus on the editor
    $('.note-editable.panel-body').focus();
   	// Sets default height to the editor
	$('.note-editable.panel-body').css("height", "350px");
	$('.note-editable.panel-body').css("min-height", "350px");
    // Gets the content of the editor
	$('body').on('DOMSubtreeModified', '.note-editable.panel-body', function() {
		$('#txtContent').val($('.note-editable.panel-body').html());
	});
       
     <%
     	if(!message.getContent().isEmpty() && sessionInfo.isCurrentLinkDataEntry()){
     %>
     	$('.note-editable.panel-body').html('<%=message.getContent()%>');
     <%
     	}
     %>
       
});
</script>
<%=MessageUtil.getPanelCheckboxJavaScriptForComboBoxSelection(messageTypeList, message.getMessageTypeCodes()) %>
<script>
// For User Recipient Table
$(".table-responsive").css("margin","0 -20px");
$(".table-responsive").css("height","600px");
$(".table-responsive").css("overflow-y","scroll");

// Hides the Pagination Controls
$("#divpgpdSESSION_USER_PAGINATION").hide();
$("#divpipdSESSION_USER_PAGINATION").hide();

// Hides the Group/Individual Option on load
$("#divIndividualContent").hide();
$("#divGroupContent").hide();

$("#rb<%= MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[MessageUtil.isFilterByGroup(message.getMessageTypeCodes())?0:1]%>").prop("checked", true).trigger("click");
$("#rb<%= MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[MessageUtil.isFilterByGroup(message.getMessageTypeCodes())?0:1]%>").click();

// Check if the Filter is by Group or Individual
$("input[name='rbFirstLevelFilterCriteria']").click(function() {
	if($("#rb<%= MessageDTO.FIRST_LEVEL_FILTER_CRITERIA_LIST[0]%>").is(':checked')) {
		$("#divGroupContent").show();
		$("#divIndividualContent").hide();
	}else{
		$("#divGroupContent").hide();
		$("#divIndividualContent").show();
	}	
});
</script>





