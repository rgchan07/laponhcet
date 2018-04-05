package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class DiscountTypePercentageDAO extends DAOBase{
	private static final long serialVersionUID = 1L;

	 private String qryDiscountTypePercentageAdd = "DISCOUNT_TYPE_PERCENTAGE_ADD";
	  private String qryDiscountTypePercentageUpdate = "DISCOUNT_TYPE_PERCENTAGE_UPDATE";
	  private String qryDiscountTypePercentageDelete = "DISCOUNT_TYPE_PERCENTAGE_DELETE";
	  private String qryDiscountTypePercentageList = "DISCOUNT_TYPE_PERCENTAGE_LIST";
	  
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		add(conn, prepStmntList, discountTypePercentage);	
		
		for (Object feeObj : new FeeDAO().getFeeList()) {
	      FeeDTO fee = (FeeDTO)feeObj;
	      DiscountTypePercentageDTO percentage = new DiscountTypePercentageDTO();
	      percentage.setFee(fee);
		}
			
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj){
		
	    DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)obj;
	    PreparedStatement prepStmnt = null;
	    try {
	      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypePercentageAdd));
	      prepStmnt.setString(1, discountTypePercentage.getDiscountTypeCode());
	      prepStmnt.setString(2, discountTypePercentage.getFee().getCode());
	      prepStmnt.setDouble(3, discountTypePercentage.getPercent());
	      prepStmnt.setString(4, discountTypePercentage.getAcademicYear().getCode());
	      prepStmnt.setString(5, discountTypePercentage.getSemester().getCode());
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    prepStmntList.add(prepStmnt);
	  }

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeDelete(DTOBase obj) {
		DiscountTypePercentageDTO discountType = (DiscountTypePercentageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, discountType);	
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)obj;
	    PreparedStatement prepStmnt = null;
	    try {
	      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypePercentageDelete));
	      prepStmnt.setInt(1, discountTypePercentage.getId());
	    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    prepStmntList.add(prepStmnt);
		  }
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeUpdate(DTOBase obj) {
		DiscountTypePercentageDTO discountType = (DiscountTypePercentageDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		update(conn, prepStmntList, discountType);	
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}

	 protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj)
	  {
	    DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)obj;
	    PreparedStatement prepStmnt = null;
	    try {
	      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypePercentageUpdate));
	      prepStmnt.setDouble(1, discountTypePercentage.getPercent());
	      prepStmnt.setInt(2, discountTypePercentage.getId());
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    prepStmntList.add(prepStmnt);
	  }

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	public List<DTOBase> getDiscountTypePercentageList() {
		return getDTOList(qryDiscountTypePercentageList);
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet)
	  {
	    DiscountTypePercentageDTO discountTypePercentage = new DiscountTypePercentageDTO();
	    discountTypePercentage.setId(((Integer)getDBVal(resultSet, "id")));
	    discountTypePercentage.setDiscountTypeCode((String)getDBVal(resultSet, "discount_type_code"));
	    discountTypePercentage.getFee().setCode((String)getDBVal(resultSet, "fee_code"));
	    discountTypePercentage.setPercent(((Double)getDBVal(resultSet, "percent")));
	    discountTypePercentage.getAcademicYear().setCode((String)getDBVal(resultSet, "academic_year_code"));
	    discountTypePercentage.getSemester().setCode((String)getDBVal(resultSet, "semester_code"));
	    return discountTypePercentage;
	  }
	
	
}
