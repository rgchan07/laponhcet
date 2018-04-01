package com.laponhcet.dto;

import java.util.List;

import com.laponhcet.util.MerchandiseUtil;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class MerchandiseDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_MERCHANDISE = "SESSION_MERCHANDISE";
	public static final String SESSION_MERCHANDISE_LIST = "SESSION_MERCHANDISE_LIST";
	public static final String SESSION_MERCHANDISE_PAGINATION = "PAGINATION_MERCHANDISE_LIST";

	public static String[] SEARCHCRITERIA = new String[] {"Code","Name"};
	public static String[] LOCATION_LIST = {"Cor. Burgos Sts. - 5th road Villamonte, Bacolod City", "CL Montelibano ext. Greensville 1,Brgy. Estefania, Bacolod City"};
	public static String[] COSTING_METHOD_LIST = {"Method1", "Method2"};
	
	private String code;
	private String name;
	private String location;
	private double qtyBeginning;
	private double qtyOnStock;
	private UnitDTO unit;
	private double unitPrice;
	private String costingMethod;
	private double nrv;
	private double qtyReorder;
	private SupplierDTO supplier;
	
	public MerchandiseDTO(){
		super();
		code = "";
		name = "";
		location = "";
		qtyBeginning = 0.0d;
		qtyOnStock = 0.0d;
		unit = new UnitDTO();
		unitPrice = 0.0d;
		costingMethod = "";
		nrv = 0d;
		qtyReorder = 0d;
		supplier = new SupplierDTO();
	}
	
	public MerchandiseDTO getMerchandise(){
		MerchandiseDTO merchandise = new MerchandiseDTO();
		merchandise.setId(super.getId());
		merchandise.setCode(this.code);
		merchandise.setName(this.name);
		merchandise.setLocation(this.location);
		merchandise.setQtyBeginning(this.qtyBeginning);
		merchandise.setQtyOnStock(this.qtyOnStock);
		merchandise.setUnit(this.unit);
		merchandise.setUnitPrice(this.unitPrice);
		merchandise.setCostingMethod(this.costingMethod);
		merchandise.setNrv(this.nrv);
		merchandise.setQtyReorder(this.qtyReorder);
		merchandise.setSupplier(this.supplier);
		merchandise.setDisplayText(merchandise.getName());
		return merchandise;
	}

	public String[] getTableData() {
		return new String[] {getCode(), getName(), StringUtil.getShortDesc(getLocation(), 15), StringUtil.getNumToStr(getQtyBeginning(), "00.00", false), StringUtil.getNumToStr(getQtyOnStock(), "00.00", false), getUnit().getName(), StringUtil.getNumToStr(getUnitPrice(), "00.00", false), getCostingMethod(), StringUtil.getNumToStr(getNrv(), "0.00", false), StringUtil.getNumToStr(getQtyReorder(), "0.00", false), getSupplier().getName(true, false, true), getCode()};
	}
	
	public String[] getTableData(List<DTOBase> merchandiseTransactionList, List<DTOBase> merchandiseTransactionTypeReceivedList, List<DTOBase> merchandiseTransactionTypeRemovedList) {
		//make last column an code for table data that needs to be updated or deleted
		String qtyStr =  "	<table width='100%'>";
		qtyStr +=  "			<tr>";
		//beginning balance
		qtyStr +=  "				<td align='right' valign='top'>";
		qtyStr += 						StringUtil.getNumToStr(getQtyBeginning(), "00.00", false);
		qtyStr +=  "				</td>";
		//received 
		double totalReceived = 0d;
		qtyStr +=  "				<td>";
		qtyStr +=  "					<table width='100%'>";
		for(int i=0; i<merchandiseTransactionTypeReceivedList.size(); i++) {
			MerchandiseTransactionTypeDTO mtt = (MerchandiseTransactionTypeDTO) merchandiseTransactionTypeReceivedList.get(i);
			double qty = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeCode(merchandiseTransactionList, mtt.getCode());
			qtyStr +=  "					<tr>";
			qtyStr +=  "						<td align='right'>";
			qtyStr += 								mtt.getNameShort();
			qtyStr +=  "						</td>";
			qtyStr +=  "						<td align='right'>";
			qtyStr += 								StringUtil.getFormattedNum(qty, StringUtil.NUMERIC_STANDARD_FORMAT);
			qtyStr +=  "						</td>";
			qtyStr +=  "					</tr>";
			totalReceived += qty;
		}
		//total received
		qtyStr +=  "						<tr>";
		qtyStr +=  "							<td style='border-top:1px solid black'>";
		qtyStr +=  "							</td>";
		qtyStr +=  "							<td align='right' style='border-top:1px solid black'>";
		qtyStr += 									StringUtil.getFormattedNum(totalReceived, StringUtil.NUMERIC_STANDARD_FORMAT);
		qtyStr +=  "							</td>";
		qtyStr +=  "						</tr>";
		qtyStr +=  "					</table>";
		qtyStr +=  "				</td>";
		
		//removed 
		double totalRemoved = 0d;
		qtyStr +=  "				<td>";
		qtyStr +=  "					<table width='100%'>";
		for(int i=0; i<merchandiseTransactionTypeRemovedList.size(); i++) {
			MerchandiseTransactionTypeDTO mtt = (MerchandiseTransactionTypeDTO) merchandiseTransactionTypeRemovedList.get(i);
			double qty = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeCode(merchandiseTransactionList, mtt.getCode());
			qtyStr +=  "					<tr>";
			qtyStr +=  "						<td align='right'>";
			qtyStr += 								mtt.getNameShort();
			qtyStr +=  "						</td>";
			qtyStr +=  "						<td align='right'>";
			qtyStr += 								StringUtil.getFormattedNum(qty, StringUtil.NUMERIC_STANDARD_FORMAT);
			qtyStr +=  "						</td>";
			qtyStr +=  "					</tr>";
			totalRemoved += qty;
		}
		//total removed
		qtyStr +=  "						<tr>";
		qtyStr +=  "							<td style='border-top:1px solid black'>";		
		qtyStr +=  "							</td>";
		qtyStr +=  "							<td align='right' style='border-top:1px solid black'>";
		qtyStr += 									StringUtil.getFormattedNum(totalRemoved, StringUtil.NUMERIC_STANDARD_FORMAT);
		qtyStr +=  "							</td>";
		qtyStr +=  "						</tr>";
		qtyStr +=  "					</table>";
		qtyStr +=  "				</td>";
		//ending balance
		qtyStr +=  "				<td align='right' valign='top'>";
		qtyStr += 						StringUtil.getFormattedNum((totalReceived + getQtyBeginning()) - totalRemoved, StringUtil.NUMERIC_STANDARD_FORMAT);
		qtyStr +=  "				</td>";
		qtyStr +=  "			</tr>";
		qtyStr +=  "		</table>";
		return new String[] {getCode(), getName(), StringUtil.getShortDesc(getLocation(), 15), qtyStr, getUnit().getName(), StringUtil.getNumToStr(getUnitPrice(), "00.00", false), getSupplier().getDisplayText(), getCode()};
	}
	
	public double getQtyEnding(List<DTOBase> merchandiseTransactionList, List<DTOBase> merchandiseTransactionTypeList) {
		//Formula EB = (BB + TRec) - TRem
		double totalReceived = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeType(merchandiseTransactionList, merchandiseTransactionTypeList, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_RECEIVED);
		double totalRemoved = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeType(merchandiseTransactionList, merchandiseTransactionTypeList, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_REMOVED);
		return ((qtyBeginning + totalReceived) - totalRemoved);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getQtyBeginning() {
		return qtyBeginning;
	}

	public void setQtyBeginning(double qtyBeginning) {
		this.qtyBeginning = qtyBeginning;
	}
	
	public double getQtyOnStock() {
		return qtyOnStock;
	}

	public void setQtyOnStock(double qtyOnStock) {
		this.qtyOnStock = qtyOnStock;
	}

	public UnitDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDTO unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCostingMethod() {
		return costingMethod;
	}

	public void setCostingMethod(String costingMethod) {
		this.costingMethod = costingMethod;
	}

	public double getNrv() {
		return nrv;
	}

	public void setNrv(double nrv) {
		this.nrv = nrv;
	}

	public double getQtyReorder() {
		return qtyReorder;
	}

	public void setQtyReorder(double qtyReorder) {
		this.qtyReorder = qtyReorder;
	}

	public SupplierDTO getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}
}
