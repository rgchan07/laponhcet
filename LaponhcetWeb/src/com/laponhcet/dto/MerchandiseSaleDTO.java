package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class MerchandiseSaleDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_MERCHANDISE_SALE = "SESSION_MERCHANDISE_SALE";
	public static final String SESSION_MERCHANDISE_SALE_LIST = "SESSION_MERCHANDISE_SALE_LIST";
	public static final String SESSION_MERCHANDISE_SALE_PAGINATION = "PAGINATION_MERCHANDISE_SALE_LIST";

	private MerchandiseTransactionDTO merchandiseTransaction;
	private CustomerDTO customer;
	private double discount;

	public MerchandiseSaleDTO() {
		super();
		merchandiseTransaction = new MerchandiseTransactionDTO();
		merchandiseTransaction.getMerchandiseTransactionType().setCode(MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_MERCHANDISE_SALE_CODE);
		customer = new CustomerDTO();
		discount = 0.0d;
	}

	public MerchandiseSaleDTO getMerchandiseSale() {
		MerchandiseSaleDTO merchandiseSale = new MerchandiseSaleDTO();
		merchandiseSale.setId(super.getId());
		merchandiseSale.setCode(super.getCode());
		merchandiseSale.setMerchandiseTransaction(merchandiseTransaction);
		merchandiseSale.setCustomer(customer);
		merchandiseSale.setDiscount(this.discount);
		return merchandiseSale;
	}

	public double getGrandTotal() {
		double grandTotal = 0d;
		for(DTOBase obj:merchandiseTransaction.getMerchandiseTransactionDetailsList()){
			MerchandiseTransactionDetailsDTO mtd = (MerchandiseTransactionDetailsDTO) obj;
			grandTotal += mtd.getSubtotal();
		}
		return grandTotal;
	}
	
	public double getTotalQuantity() {
		double totalQuantity = 0d;
		for(DTOBase obj:merchandiseTransaction.getMerchandiseTransactionDetailsList()){
			MerchandiseTransactionDetailsDTO mtd = (MerchandiseTransactionDetailsDTO) obj;
			totalQuantity += mtd.getQty();
		}
		return totalQuantity;
	}
	
	public String[] getTableData() {
		return new String[] { getCode(), merchandiseTransaction.getMerchandiseTransactionType().getName() + " <br> " + merchandiseTransaction.getMerchandiseTransactionDetailsListStr() + " " + merchandiseTransaction.getMerchandiseTransactionDetails().getMerchandise().getUnit().getName(), StringUtil.getFullName(customer.getLastName(), customer.getFirstName(), customer.getMiddleName(), "", false, true), StringUtil.getFormattedNum(getDiscount(), StringUtil.NUMERIC_STANDARD_FORMAT), getCode() };
	}
	
	public MerchandiseTransactionDTO getMerchandiseTransaction() {
		return merchandiseTransaction;
	}

	public void setMerchandiseTransaction(MerchandiseTransactionDTO merchandiseTransaction) {
		this.merchandiseTransaction = merchandiseTransaction;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
