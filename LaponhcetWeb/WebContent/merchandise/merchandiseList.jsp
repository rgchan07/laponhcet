<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.LinkDTO" %>
<%@ page import="com.mytechnopal.dto.UserDTO" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination)session.getAttribute(MerchandiseDTO.SESSION_MERCHANDISE_PAGINATION);
%>

<div class="col-lg-12">
    <div class="ibox float-e-margins">
    	<div class="ibox-content">
         	<div class="row">
	         	<div class="col-sm-12">
	         	<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-2", false, "Show Entries", "RecordPerPage", new String[] {"10", "20", "50", "100"}, "10", new String[] {"10", "20", "50", "100"}, "", "", "onChange=\"showPaginationData('" + Pagination.PAGINATION_ACTION_lIMIT + "', this.value);\"")%>
				<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-4", false, "Search Criteria", "SearchCriteria", new String[] {"Code", "Name"}, "Name", MerchandiseDTO.SEARCHCRITERIA, "", "", "")%>
				<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-6 m-b", false, "Search Value", "", "SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPaginationData(this.value);\"") %>
	         	</div>
	         	<%=new PaginationWebControl().getPaginationWebControl(sessionInfo, "col-sm-12", pagination) %>
			</div>
        </div>
    </div>
</div>