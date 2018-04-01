<%@page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>

 <link href="/static/inspinia/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="/static/inspinia/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="/static/inspinia/css/style.css" rel="stylesheet">
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	MerchandiseSaleDTO merchandiseSale = (MerchandiseSaleDTO)session.getAttribute(MerchandiseSaleDTO.SESSION_MERCHANDISE_SALE);

	List<DTOBase> merchandiseList = (ArrayList<DTOBase>)session.getAttribute(MerchandiseDTO.SESSION_MERCHANDISE_LIST);
	List<DTOBase> customerList = (ArrayList<DTOBase>)session.getAttribute(CustomerDTO.SESSION_CUSTOMER_LIST);
	//LinkButtonWebControl lbAddTransactionDetails = new LinkButtonWebControl(sessionInfo, "btn btn-default", "fa fa-plus", sessionInfo.getLinkByCode("U165"));
	//LinkButtonWebControl lbConfirm = new LinkButtonWebControl(sessionInfo, "btn btn-default", "fa fa-check", sessionInfo.getLinkByCode("U164"));
	
	double cash = 0d;
	double change = 0d;
%>

<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Search Merchandise", "Search Merchandise", "Merchandise", merchandiseSale.getMerchandiseTransaction().getMerchandiseTransactionDetails().getMerchandise().getCode(), 20, TextBoxWebControl.DATA_TYPE_STRING, "onkeydown='add(event)' type='' style='font-size: 15px; font-weight: 500;'") %>
<div class="wrapper wrapper-content  animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                      		<div class="jqGrid_wrapper">
                                <table id="table_list_2"></table>
                                <div id="pager_list_2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Total Quantity", "0.00", "TotalQuantity", StringUtil.getNumToStr(merchandiseSale.getTotalQuantity(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "disabled style='font-size: 25px; font-weight: 500; text-align: right; border: 0px; background-color: white;'")%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Total", "0.00", "Total", StringUtil.getNumToStr(merchandiseSale.getGrandTotal(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "disabled style='font-size: 25px; font-weight: 500; text-align: right; border: 0px; background-color: white;'")%>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "QUANTITY", "Quantity", "Quantity", StringUtil.getNumToStr(merchandiseSale.getMerchandiseTransaction().getMerchandiseTransactionDetails().getQty(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA, false), 9, WebControlBase.DATA_TYPE_DOUBLE, "onkeydown='search(event)' onkeydown='add(event)' pull-right style='font-size: 15px; font-weight: 500;'") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "GRAND TOTAL", "Grand Total", "GrandTotal", StringUtil.getNumToStr(merchandiseSale.getGrandTotal(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "disabled style='height: 40px; font-size: 35px; font-weight: 900; text-align: right; border: 0px; background-color: white;'") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "DISCOUNT", "Discount", "Discount", StringUtil.getNumToStr(merchandiseSale.getDiscount(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA, false), 9, WebControlBase.DATA_TYPE_DOUBLE, "onkeydown='discount(event)' pull-right style='font-size: 18px; font-weight: 500;'") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "CASH", "Cash", "Cash", StringUtil.getNumToStr(cash, StringUtil.NUMERIC_STANDARD_FORMAT, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "onkeydown='computation(event)' style='height: 40px; font-size: 25px; font-weight: 900; text-align: right;'") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Date", "Date", "Date", DateTimeUtil.getDateTimeToStr(merchandiseSale.getMerchandiseTransaction().getDate(), "MM/dd/yyyy"), 45, TextBoxWebControl.DATA_TYPE_DATE, "disabled style='font-size:15px; font-weight:800; margin-top: 10px; color:#353333; border: 0px; background-color: white;'")%>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Customer", "Customer", customerList, merchandiseSale.getCustomer(), "", "", "style='font-size:15px; font-weight:800; color:#353333; margin-top: 10px; border: 0px;'") %>

<!-- Modal for Change-->
<div onkeydown="save(event)" onkeydown="closeModalPayment(event)" class="modal fade" id="myModalChange" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button id="closeModalonCash" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">TechnoPOS</h4>
			</div>
			<div class="modal-body">
				<p class="col-sm-6" style="font-size: 25px; font-weight: 700;">PAYMENT RECEIVE</p>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "", ".00", "PaymentReceive", StringUtil.getNumToStr(cash, StringUtil.NUMERIC_STANDARD_FORMAT, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "disabled style='height: 40px; border: none; font-size: 35px; font-weight: 900; text-align: right; background-color: white;'")%>
				<p class="col-sm-6" style="font-size: 25px; font-weight: 700;">CHANGE</p>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "", ".00", "Change", StringUtil.getNumToStr(cash, StringUtil.NUMERIC_STANDARD_FORMAT, false), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "disabled style='height: 40px; border: none; font-size: 35px; font-weight: 900; text-align: right; background-color: white;'")%>
			</div>
			<div class="modal-footer">
				<div class="col-sm-12">
					<button onclick="clickSave()" type="button" class="btn btn-default pull-right" data-dismiss="modal">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal for Not Enough Cash-->
<div onkeydown="closeModalPayment(event)" class="modal fade" id="myModalNotEnoughCash" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">TechnoPOS</h4>
			</div>
			<div class="modal-body">
				<p class="col-sm-12" style="font-size: 35px; font-weight: 700; text-align: center;">NOT ENOUGH CASH</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-right" data-dismiss="modal">Re-Enter Cash</button>
			</div>
		</div>
	</div>
</div>
<!-- Invalid Discount-->
<div onkeydown="closeDiscount(event)" class="modal fade" id="invalidDiscount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">TechnoPOS</h4>
			</div>
			<div class="modal-body">
				<p class="col-sm-12" style="font-size: 30px; font-weight: 700; text-align: center;">Discount should be lesser than Grand Total</p>
			</div>
			<div class="modal-footer">
				<button id="ReEnterDiscount" type="button" class="btn btn-default pull-right" data-dismiss="modal">Re-Enter Discount</button>
			</div>
		</div>
	</div>
</div>

<!-- Peity -->
    <script src="/static/inspinia/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- jqGrid -->
    <script src="/static/inspinia/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
    <script src="/static/inspinia/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script>
	$(document).ready(function () {


            // Examle data for jqGrid
            var mydata = [
            	<%
            	for(DTOBase obj:merchandiseSale.getMerchandiseTransaction().getMerchandiseTransactionDetailsList()){
    				MerchandiseTransactionDetailsDTO mtd = (MerchandiseTransactionDetailsDTO) obj;
    				UnitDTO unit = new UnitDAO().getUnitByCode(mtd.getMerchandise().getCode());
            	%>
                {data: "<%=mtd.getQty() + " " + unit.getName() + " --- " + mtd.getMerchandise().getName() + " @ " + mtd.getMerchandise().getUnitPrice() + " --- " + mtd.getSubtotal() + "-------" + "<a href='#' onclick='openLink(US0008)'><i class='fa fa-remove'></i></a>" %>"} ,
               
                <%
            	}
            	%>
            ];

            // Configuration for jqGrid Example 2
            $("#table_list_2").jqGrid({
                data: mydata,
                datatype: "local",
                height: 450,
                autowidth: true,
                shrinkToFit: true,
                rowNum: 20,
                rowList: [10, 20, 30],
                colNames:[''],
                colModel:[
                    {name:'data',index:'data', editable: true, width:60, sorttype:"data",search:true},
                ],
                pager: "#pager_list_2",
                viewrecords: true,
                caption: "",
                add: true,
                edit: true,
                addtext: 'Add',
                edittext: 'Edit',
                hidegrid: false
            });

            // Add selection
            $("#table_list_2").setSelection(4, true);


            // Setup buttons
            $("#table_list_2").jqGrid('navGrid', '#pager_list_2',
                    {edit: true, add: true, del: true, search: true},
                    {height: 200, reloadAfterSubmit: true}
            );

            // Add responsive to jqGrid
            $(window).bind('resize', function () {
                var width = $('.jqGrid_wrapper').width();
                $('#table_list_2').setGridWidth(width);
            });


            setTimeout(function(){
                $('.wrapper-content').removeClass('animated fadeInRight');
            },700);

        });

</script>

<script type="text/javascript">
	window.addEventListener("keydown", checkKeyPress, false);

	/* function checkKeyPress(key){
		if (key.keyCode == "65"){
			alert("The 'a' letter has been pressed!")
		}
	} */
	
	//Confirm
	function clickSave()
	{	
		openLink('US0004');
	}
	function save(event)
	{	
		var w = event.keyCode;
		if (w == 13){
			openLink('US0004');
		}
	}
	//Add to Table
	 function add(event)
	{
		var w = event.keyCode;
		if (w == 13){
			openLink('US0007');
		}
	}
	//Computation
	function computation(event)
	{
		var cash = parseInt(document.getElementById("txtCash").value);
		var grandTotal = parseInt(document.getElementById("txtGrandTotal").value);
		var w = event.keyCode;
		if (w == 13 && grandTotal > cash) {
			$("#myModalNotEnoughCash").modal();
		}
		if (w == 13 && cash >= grandTotal) {
			var change = parseInt(cash - grandTotal)
			document.getElementById("txtPaymentReceive").value= cash;
			document.getElementById("txtChange").value= change;
			$("#myModalChange").modal();
		}
	}
	//Discount
	function discount(event)
	{
		var w = event.keyCode;
		var total = parseInt(document.getElementById("txtTotal").value);
		var discount = parseInt(document.getElementById("txtDiscount").value);
		if (w == 13 && discount == 0) {
			document.getElementById("txtGrandTotal").value = total;
		}	
		if (w == 13 && discount > total) {
			$("#invalidDiscount").modal();
			document.getElementById("txtDiscount").focus();
		}	
		if (w == 13 && total > discount) {
			var grandTotal = parseInt(total - discount);
			document.getElementById("txtGrandTotal").value= grandTotal + ".00";
		}	
	}
	//for Total and GrandTotal
	function assign() {
		var total = parseInt(document.getElementById("txtTotal").value);
		document.getElementById("txtGrandTotal").value = total;
	}
	//Close Discount
	function closeDiscount(event)
	{
		var w = event.keyCode;
		if (w == 13) {
			document.getElementById("ReEnterDiscount").click();
			document.getElementById("txtDiscount").focus();	
		}
	}
	//Close Modal
	function closeModalPayment(event)
	{
		var w = event.keyCode;
		if (w == 27) {
			document.getElementById("txtMerchandise").focus();
			document.getElementById("closeModalonCash").click();
		}
	}
	//Close Modal
	function closeModalPayment(event)
	{
		var w = event.keyCode;
		if (w == 27) {
			document.getElementById("txtCash").focus();
			document.getElementById("closeModalNotEnoughCash").click();
		}
	}
	//Focus on Merchandise
	function getfocus() {
    	document.getElementById("txtMerchandise").focus();
	}
	/* //Close Modal on Search
	function closeModalSearch(event) {
		var w = event.keyCode;
		if (w == 27) {
			document.getElementById("closeModalSearch").click();
		}
	}
	//Search Modal
	function search(event) {
		var w = event.keyCode;
		if (w == 13) {
			$("#myModalSearch").modal();
		}
	} */
</script>