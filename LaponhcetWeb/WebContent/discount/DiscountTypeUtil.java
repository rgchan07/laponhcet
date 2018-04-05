package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicYearDAO;
import com.laponhcet.dao.DiscountTypeDAO;
import com.laponhcet.dao.DiscountTypePercentageDAO;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dao.SemesterDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DiscountTypeUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	public static List<DTOBase> getDiscountTypePercentageListByFeeListParent(List<DTOBase> feeListParent) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase feeObj: feeListParent) {
			DiscountTypePercentageDTO discountTypePercentage = new DiscountTypePercentageDTO();
			discountTypePercentage.setFee((FeeDTO) feeObj);
			resultList.add(discountTypePercentage);
		}
		return resultList;
	}

	 public static List<DTOBase> getDiscountTypePercentageListByDiscountTypeCode(String discountTypeCode)
	  {
	    List<DTOBase> discountTypePercentageList = new DiscountTypePercentageDAO().getDiscountTypePercentageList();
	    List<DTOBase> feeList = new FeeDAO().getFeeList();
	    List<DTOBase> resultList = new ArrayList<DTOBase>();
	    for (DTOBase obj : discountTypePercentageList) {
	      DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) obj;
	      if (discountTypePercentage.getDiscountTypeCode().equalsIgnoreCase(discountTypeCode)) {
	        discountTypePercentage.setFee((FeeDTO)DTOUtil.getObjByCode(feeList, discountTypePercentage.getFee().getCode()));
	       
	        resultList.add(discountTypePercentage);
	      }
	    }
	    return resultList;
	  }
	 
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			DiscountTypeDTO discountType = (DiscountTypeDTO) dto;
			discountType.setPaginationRecord(new String[]{discountType.getName(),  discountType.isPercentage()?"YES":"NO", pagination.getLinkButtonStr(sessionInfo, discountType.getId()).replace("~", ",")});	
		}
	}
	
}
