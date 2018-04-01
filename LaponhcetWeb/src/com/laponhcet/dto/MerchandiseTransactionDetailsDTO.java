package com.laponhcet.dto;

import com.laponhcet.dao.UnitDAO;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;


public class MerchandiseTransactionDetailsDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	private String merchandiseTransactionCode;
	private MerchandiseDTO merchandise;
	private double qty;
	private String remarks;
	
	public MerchandiseTransactionDetailsDTO() {
		super();
		merchandiseTransactionCode = "";
		merchandise = new MerchandiseDTO();
		qty = 1d;
		remarks = "";
	}

	public MerchandiseTransactionDetailsDTO getMerchandiseTransactionDetails() {
		MerchandiseTransactionDetailsDTO merchandiseTransactionDetails = new MerchandiseTransactionDetailsDTO();
		merchandiseTransactionDetails.setId(super.getId());
		merchandiseTransactionDetails.setCode(super.getCode());
		merchandiseTransactionDetails.setMerchandiseTransactionCode(merchandiseTransactionCode);
		merchandiseTransactionDetails.setMerchandise(merchandise);
		merchandiseTransactionDetails.setQty(qty);
		merchandiseTransactionDetails.setRemarks(remarks);
		return merchandiseTransactionDetails;
	}
	
	public String[] getTableData() {
		//make last column an code for table data that needs to be updated or deleted
		return new String[] {merchandise.getName(), StringUtil.getFormattedNum(getQty(), StringUtil.NUMERIC_STANDARD_FORMAT), getRemarks(), String.valueOf(getId())};
	}
	
	public String[] getTableDataSale() {
		//make last column an code for table data that needs to be updated or deleted
		UnitDTO unit =  new UnitDAO().getUnitByCode(merchandise.getUnit().getCode());
		return new String[] {"<p style='font-size: 20px; height: 20px'>" + StringUtil.getFormattedNum(getQty(), StringUtil.NUMERIC_STANDARD_FORMAT) +" "+ unit.getName() + "</p>", "<p style='font-size: 20px; height: 20px'>" + merchandise.getName() + " @ " + merchandise.getUnitPrice() + "</p>", "<p align='right' style='font-size: 20px; height: 20px;'>" + StringUtil.getFormattedNum(getSubtotal(), StringUtil.NUMERIC_STANDARD_FORMAT) + "</p>", String.valueOf(getId())};
	}
	
	public String[] getDetails() {
		MerchandiseSaleDTO merchandiseSale= new MerchandiseSaleDTO();
		for(DTOBase obj:merchandiseSale.getMerchandiseTransaction().getMerchandiseTransactionDetailsList()){
			MerchandiseTransactionDetailsDTO mtd = (MerchandiseTransactionDetailsDTO) obj;
			UnitDTO unit = new UnitDAO().getUnitByCode(mtd.getMerchandise().getCode());
			System.out.println(mtd.getQty() + " " + unit.getName() + " --- " + mtd.getMerchandise().getName() + " @ " + mtd.getMerchandise().getUnitPrice() + " --- " + mtd.getSubtotal());
		}
		return new String[]{};
	}
	
	public double getSubtotal(){
		return qty * merchandise.getUnitPrice();
	}
	
	public String getMerchandiseTransactionCode() {
		return merchandiseTransactionCode;
	}
	
	public void setMerchandiseTransactionCode(String merchandiseTransactionCode) {
		this.merchandiseTransactionCode = merchandiseTransactionCode;
	}
	
	public MerchandiseDTO getMerchandise() {
		return merchandise;
	}
	
	public void setMerchandise(MerchandiseDTO merchandise) {
		this.merchandise = merchandise;
	}
	
	public double getQty() {
		return qty;
	}
	
	public void setQty(double qty) {
		this.qty = qty;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
