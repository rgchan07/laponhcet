package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class FeeUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> feeList, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			FeeDTO fee = (FeeDTO) dto;
			FeeDTO parent = (FeeDTO) DTOUtil.getObjByCode(feeList, fee.getCodeParent());
			fee.setPaginationRecord(new String[] {parent==null?"None":parent.getName(), fee.getName(),  AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, fee.getAcademicProgramCodes()), fee.isMandatory()?"Yes":"No", pagination.getLinkButtonStr(sessionInfo, fee.getId()).replace("~", ",")});		
		}
	}
	
	public static List<DTOBase> getFeeListSearchByParent(List<DTOBase> feeParentList, List<DTOBase> feeList) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase parentObj: feeParentList) {
			FeeDTO parentFee = (FeeDTO) parentObj;
			List<DTOBase> childrenFeeList = new ArrayList<DTOBase>();
			for(DTOBase childObj: feeList) {
				FeeDTO childFee = (FeeDTO) childObj;
				if(childFee.getCodeParent().equalsIgnoreCase(parentFee.getCode())) {
					childrenFeeList.add(childFee);
				}
			}
			resultList = DTOUtil.addObjList(childrenFeeList, resultList);
		}
		return resultList;
	}
	
	public static List<DTOBase> getFeeListChildrenByParent(List<DTOBase> feeList, FeeDTO feeParent) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase feeObj: feeList) {
			FeeDTO fee = (FeeDTO) feeObj;
			if(fee.getCodeParent().equalsIgnoreCase(feeParent.getCode())) {
				resultList.add(fee);
			}
		}
		return resultList;		
	}
	
	public static List<DTOBase> getFeeListChildren(List<DTOBase> feeList) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase feeObj: feeList) {
			FeeDTO fee = (FeeDTO) feeObj;
			if(!StringUtil.isEmpty(fee.getCodeParent())) {
				resultList.add(fee);
			}
		}
		return resultList;		
	}
	
	public static List<DTOBase> getFeeListParent(List<DTOBase> feeList) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase feeObj: feeList) {
			FeeDTO fee = (FeeDTO) feeObj;
			if(StringUtil.isEmpty(fee.getCodeParent())) {
				resultList.add(fee);
			}
		}
		return resultList;		
	}
}
