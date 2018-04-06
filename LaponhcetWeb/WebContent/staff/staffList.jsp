<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination)session.getAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
	StaffDTO staffSelected = (StaffDTO) session.getAttribute(StaffDTO.SESSION_STAFF);
%>

<div class="col-lg-12">
    <div class="ibox float-e-margins">
    	<div class="ibox-content">
         	<div class="row">
	         	<div class="col-sm-12">
	         	<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-2", false, "Show Entries", "RecordPerPage", new String[] {"10", "20", "50", "100"}, "10", new String[] {"10", "20", "50", "100"}, "", "", "onChange=\"showPaginationData('" + Pagination.PAGINATION_ACTION_lIMIT + "', this.value);\"")%>
				<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-4", false, "Search Criteria", "SearchCriteria", StaffDTO.STAFF_PAGINATION_SEARCH_CRITERIA_LIST, StaffDTO.STAFF_PAGINATION_SEARCH_CRITERIA_LIST[0], StaffDTO.STAFF_PAGINATION_SEARCH_CRITERIA_LIST, "", "", "")%>
	         	<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-6 m-b", false, "Search Value", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value);\"") %>
	         	</div>
	         	<%=new PaginationWebControl().getPaginationWebControlList(sessionInfo, "col-sm-12", pagination, staffSelected.getId()) %>
			</div>
        </div>
    </div>
</div>