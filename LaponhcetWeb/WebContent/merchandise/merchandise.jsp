<%@page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	MerchandiseDTO merchandise = (MerchandiseDTO)session.getAttribute(MerchandiseDTO.SESSION_MERCHANDISE);
	List<DTOBase> unitList = (ArrayList)session.getAttribute(UnitDTO.SESSION_UNIT_LIST);
	List<DTOBase> supplierList = (ArrayList)session.getAttribute(SupplierDTO.SESSION_SUPPLIER_LIST);
	
	LinkButtonWebControl lbAddUnit = new LinkButtonWebControl(sessionInfo, "btn", "fa fa-plus", sessionInfo.getLinkByCode("UU0002"));
	LinkButtonWebControl lbAddSupplier = new LinkButtonWebControl(sessionInfo, "btn", "fa fa-plus", sessionInfo.getLinkByCode("UM0002"));
%>

<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Code", "Code", "Code", merchandise.getCode(), 30, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Name", "Name", "Name", merchandise.getName(), 90, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Unit", "Unit", unitList, merchandise.getUnit(), "", "", "") %>
<div class="col-sm-1" style="margin-top: 40px;"><%=lbAddUnit.getLinkButtonWebControl()%> </div>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Location", "Location", "Location", merchandise.getLocation(), 90, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Beginning Balance", "Beginning Balance", "BeginningBalance", StringUtil.getFormattedNum(merchandise.getQtyBeginning(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_DOUBLE, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Quantity on Stock", "Quantity on Stock", "QuantityOnStock", StringUtil.getFormattedNum(merchandise.getQtyOnStock(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_DOUBLE, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Unit Price", "Unit Price", "UnitPrice", StringUtil.getFormattedNum(merchandise.getUnitPrice(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_DOUBLE, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Costing Method", "Costing Method", "CostingMethod", merchandise.getCostingMethod(), 45, WebControlBase.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Nrv", "Nrv", "Nrv", StringUtil.getFormattedNum(merchandise.getNrv(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_DOUBLE, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Qty Reorder", "Qty Reorder", "QtyReorder", StringUtil.getFormattedNum(merchandise.getQtyReorder(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 45, WebControlBase.DATA_TYPE_DOUBLE, "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-7", true, "Supplier", "Supplier", supplierList, merchandise.getSupplier(), "", "", "") %>
<div class="col-sm-1" style="margin-top: 40px;"><%=lbAddSupplier.getLinkButtonWebControl()%> </div>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>
