package com.laponhcet.util;

import java.io.Serializable;
import java.util.List;

import com.laponhcet.dto.MerchandiseDTO;
import com.laponhcet.dto.SupplierDTO;
import com.laponhcet.dto.UnitDTO;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.DataAndSessionBase;
import com.mytechnopal.util.DTOUtil;


public class MerchandiseUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static List<DTOBase> getMerchandiseList(List<DTOBase> merchandiseList, List<DTOBase> unitList, List<DTOBase> supplierList) {
		for(DTOBase merchandiseObj:  merchandiseList) {
			MerchandiseDTO merchandise = (MerchandiseDTO) merchandiseObj;
			merchandise.setUnit((UnitDTO) DTOUtil.getObjByCode(unitList, merchandise.getUnit().getCode()));
			merchandise.setSupplier((SupplierDTO) DTOUtil.getObjByCode(supplierList, merchandise.getSupplier().getCode()));
		}
		return merchandiseList;
	}
	
	public static double getTotalQtyByMerchandiseTransactionTypeCode(List<DTOBase> merchandiseTransactionList, String merchandiseTransactionTypeCode) {
		double totalQty = 0d;
		for(DTOBase mtObj: merchandiseTransactionList) {
			MerchandiseTransactionDTO merchandiseTransaction = (MerchandiseTransactionDTO) mtObj;
			if(merchandiseTransaction.getMerchandiseTransactionType().getCode().equalsIgnoreCase(merchandiseTransactionTypeCode)) {
				totalQty += merchandiseTransaction.getMerchandiseTransactionDetails().getQty();
			}
		}
		return totalQty;
	}
	
	public static double getTotalQtyByMerchandiseTransactionTypeType(List<DTOBase> merchandiseTransactionList, List<DTOBase> merchandiseTransactionTypeList, String merchandiseTransactionTypeType) {
		double totalQty = 0d;
		for(DTOBase mtObj: merchandiseTransactionList) {
			MerchandiseTransactionDTO merchandiseTransaction = (MerchandiseTransactionDTO) mtObj;
			merchandiseTransaction.setMerchandiseTransactionType((MerchandiseTransactionTypeDTO) DTOUtil.getObjByCode(merchandiseTransactionTypeList, merchandiseTransaction.getMerchandiseTransactionType().getCode()));
			if(merchandiseTransaction.getMerchandiseTransactionType().getType().equalsIgnoreCase(merchandiseTransactionTypeType)) {
				totalQty += merchandiseTransaction.getMerchandiseTransactionDetails().getQty();
			}
		}
		return totalQty;
	}
	
	public static String[][] getTableBody(List<DTOBase> list, List<DTOBase> merchandiseTransactionTypelist) {
		List<DTOBase> merchandiseTransactionTypeReceivedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_RECEIVED);
		List<DTOBase> merchandiseTransactionTypeRemovedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_REMOVED);
		
		String[][] result = new String[list.size()][];
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size(); j++) {
				MerchandiseDTO merchandise = (MerchandiseDTO) list.get(j);
				List<DTOBase> merchandiseTransactionList = MerchandiseTransactionUtil.getMerchandiseTransactionListByMerchandiseCode(merchandise.getCode());
				result[j] = merchandise.getTableData(merchandiseTransactionList, merchandiseTransactionTypeReceivedList, merchandiseTransactionTypeRemovedList);
			}
		}
		return result;
	}
	
	public static String[][] getTableBody(List<DTOBase> list) {
		//List<DTOBase> merchandiseTransactionTypeReceivedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_RECEIVED);
		//List<DTOBase> merchandiseTransactionTypeRemovedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_REMOVED);
		
		String[][] result = new String[list.size()][];
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size(); j++) {
				MerchandiseDTO merchandise = (MerchandiseDTO) list.get(j);
				//List<DTOBase> merchandiseTransactionList = MerchandiseTransactionUtil.getMerchandiseTransactionListByMerchandiseCode(merchandise.getCode());
				result[j] = merchandise.getTableData();
			}
		}
		return result;
	}
	
	public static String[] getTableHeader() {
		String qtyHeaderStr = "	<table width='100%'>";
		qtyHeaderStr += "				<tr>";
		qtyHeaderStr += "					<td colspan='4' align='center'>";
		qtyHeaderStr += "						Quantity";
		qtyHeaderStr += "					</td>";
		qtyHeaderStr += "				</tr>";
		qtyHeaderStr += "				<tr>";
		qtyHeaderStr += "					<td align='center'>";
		qtyHeaderStr += "						B-BAL";
		qtyHeaderStr += "					</td>";
		qtyHeaderStr += "					<td align='center'>";
		qtyHeaderStr += "						REC";
		qtyHeaderStr += "					</td>";
		qtyHeaderStr += "					<td align='center'>";
		qtyHeaderStr += "						REM";
		qtyHeaderStr += "					</td>";
		qtyHeaderStr += "					<td align='center'>";
		qtyHeaderStr += "						E-BAL";
		qtyHeaderStr += "					</td>";
		qtyHeaderStr += "				</tr>";
		qtyHeaderStr += "			</table>";
		return new String[] {"Code", "Name", "Location", qtyHeaderStr, "Unit", "Unit Price", ""};
	}
	
	/*public static void generateCSVFile(String path, PaginationData pagination, List<DTOBase> merchandiseList, List<DTOBase> merchandiseTransactionTypelist) {
		pagination.setCsvFilename("MerchandiseList" + StringUtil.getUniqueId(2, 2) + ".csv");
		List<DTOBase> merchandiseTransactionTypeReceivedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_RECEIVED);
		List<DTOBase> merchandiseTransactionTypeRemovedList = MerchandiseTransactionTypeUtil.getMerchandiseTransactionTypeListByType(merchandiseTransactionTypelist, MerchandiseTransactionTypeDTO.MERCHANDISE_TRANSACTION_TYPE_REMOVED);
		
	    try {
	    	FileWriter writer = new FileWriter(path + "/tmp/" + pagination.getCsvFilename());
			writer.write("#");
			writer.write(",");
			writer.write("Code");
			writer.write(",");
			writer.write("Name");
			writer.write(",");
			writer.write("Location");
			writer.write(",");
			writer.write("B-BAL");
			writer.write(",");
			writer.write("REC-M");
			writer.write(",");
			writer.write("REC-P");
			writer.write(",");
			writer.write("REC-TI");
			writer.write(",");
			writer.write("REC-TOTAL");
			writer.write(",");
			writer.write("REM-C");
			writer.write(",");
			writer.write("REM-S");
			writer.write(",");
			writer.write("REM-TO");
			writer.write(",");
			writer.write("REM-TOTAL");
			writer.write(",");
			writer.write("E-BAL");
			writer.write("\n");
			for(int i=0; i<merchandiseList.size(); i++) {
				MerchandiseDTO merchandise = (MerchandiseDTO)merchandiseList.get(i);
				writer.write(String.valueOf(i+1));
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(merchandise.getCode(), "\""));
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(merchandise.getName(), "\""));
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(merchandise.getLocation(), "\""));
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(StringUtil.getNumToStr(merchandise.getQtyBeginning(), "00.00", false), "\""));
				double totalReceived = 0d;
				List<DTOBase> merchandiseTransactionList = MerchandiseTransactionUtil.getMerchandiseTransactionListByMerchandiseCode(merchandise.getCode());
				for(int j=0; j<merchandiseTransactionTypeReceivedList.size(); j++) {
					MerchandiseTransactionTypeDTO mtt = (MerchandiseTransactionTypeDTO) merchandiseTransactionTypeReceivedList.get(j);
					writer.write(",");
					double qty = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeCode(merchandiseTransactionList, mtt.getCode());
					writer.write(StringUtil.getSurroundedStr(StringUtil.getFormattedNum(qty, StringUtil.NUMERIC_STANDARD_FORMAT), "\""));
					totalReceived += qty;
				}
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(StringUtil.getFormattedNum(totalReceived, StringUtil.NUMERIC_STANDARD_FORMAT), "\""));
				double totalRemoved = 0d;
				for(int n=0; n<merchandiseTransactionTypeRemovedList.size(); n++) {
					MerchandiseTransactionTypeDTO mtt = (MerchandiseTransactionTypeDTO) merchandiseTransactionTypeRemovedList.get(n);
					writer.write(",");
					double qty = MerchandiseUtil.getTotalQtyByMerchandiseTransactionTypeCode(merchandiseTransactionList, mtt.getCode());
					writer.write(StringUtil.getSurroundedStr(StringUtil.getFormattedNum(qty, StringUtil.NUMERIC_STANDARD_FORMAT), "\""));
					totalRemoved += qty;
				}
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(StringUtil.getFormattedNum(totalRemoved, StringUtil.NUMERIC_STANDARD_FORMAT), "\""));
				writer.write(",");
				writer.write(StringUtil.getSurroundedStr(StringUtil.getFormattedNum((totalReceived + merchandise.getQtyBeginning()) - totalRemoved, StringUtil.NUMERIC_STANDARD_FORMAT), "\""));
				writer.write("\n");
			}	
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static String getRecordButtonStr(SessionInfo sessionInfo, MerchandiseDTO merchandise){
		StringBuffer str = new StringBuffer();
		str.append("<button class='fa fa-pencil fa-sm btn-rounded btn-outline btn btn-success' onclick=\"recordAction('" + merchandise.getId() + "','" + sessionInfo.getUpdateLink().getCode() +  "')\"></button>");
		str.append("<button class='fa fa-times btn-rounded btn-outline btn btn-danger m-l-xs' onclick=\"recordAction('" + merchandise.getId() + "','" + sessionInfo.getDeleteSubmitLink().getCode()  +  "')\"></button>");
		/*if(!merchandise.getStatus().equalsIgnoreCase("APPROVED")){
			str.append("<button class='fa fa-check btn-rounded btn-outline btn btn-info m-l-xs' onclick=\"recordAction('" + merchandise.getId() + "','U00142')\"></button>");
		}*/
		return str.toString();
	}
}
