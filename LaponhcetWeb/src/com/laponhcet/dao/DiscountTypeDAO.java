package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.DiscountTypeDTO;
import com.laponhcet.dto.DiscountTypePercentageDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class DiscountTypeDAO extends DAOBase{
	private static final long serialVersionUID = 1L;

	 private String qryDiscountTypeAdd = "DISCOUNT_TYPE_ADD";
	  private String qryDiscountTypeUpdate = "DISCOUNT_TYPE_UPDATE";
	  private String qryDiscountTypeDelete = "DISCOUNT_TYPE_DELETE";
	  private String qryDiscountTypeLastCode = "DISCOUNT_TYPE_LAST";
	  private String qryDiscountTypeList = "DISCOUNT_TYPE_LIST";
	  private String qryDiscountTypeByName = "DISCOUNT_TYPE_BY_NAME";
	  private String qryDiscountTypeByCode = "DISCOUNT_TYPE_BY_CODE";
	  private String qryDiscountTypeListSearchByName = "DISCOUNT_TYPE_LIST_SEARCHBY_NAME";
	  
	  
	  private String getGeneratedCode() {
			 DiscountTypeDTO discountType = getDiscountTypeLast();
				String code = "001"; //default code
				if(discountType != null) {
					int nextNum = Integer.parseInt(discountType.getCode()) + 1; 
					code = StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
				}
				return code;
		}
		 
		 public DiscountTypeDTO getDiscountTypeLast() {
				return (DiscountTypeDTO) getDTO(qryDiscountTypeLastCode);
			}
	 
	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		 discountType.setCode(getGeneratedCode());
		 discountType.setBaseDataOnInsert();
		add(conn, prepStmntList, discountType);	
		
	for (DTOBase discountTypePercentageObj : discountType.getDiscountTypePercentageList()) {
	      DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountTypePercentageObj;
	     discountTypePercentage.setDiscountTypeCode(discountType.getCode());
	     discountTypePercentage.setAcademicYear(discountType.getAcademicYear());
	     discountTypePercentage.setSemester(discountType.getSemester());
	      discountTypePercentageDAO.add(conn, prepStmntList, discountTypePercentageObj);
	}
		
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		    DiscountTypeDTO discountType = (DiscountTypeDTO)obj;
		    PreparedStatement prepStmnt = null;
		    try {
		      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypeAdd));
		      prepStmnt.setString(1, discountType.getCode());
		      prepStmnt.setString(2, discountType.getAcademicYear().getCode());
		      prepStmnt.setString(3, discountType.getSemester().getCode());
		      prepStmnt.setString(4, discountType.getName());
		      prepStmnt.setBoolean(5, discountType.isPercentage()); 
		      prepStmnt.setString(6, discountType.getAddedBy());
		      prepStmnt.setTimestamp(7, discountType.getAddedTimestamp());
		      prepStmnt.setString(8, discountType.getUpdatedBy());
		      prepStmnt.setTimestamp(9, discountType.getUpdatedTimestamp());
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
		DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, discountType);	
		
		for (Object discountTypePercentageObj : discountType.getDiscountTypePercentageList()) {
		      DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountTypePercentageObj;
		     discountTypePercentage.setDiscountTypeCode(discountType.getCode());
		      discountTypePercentageDAO.delete(conn, prepStmntList, discountTypePercentageObj);
		}
			
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	 protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		    DiscountTypeDTO discountType = (DiscountTypeDTO)obj;
		    PreparedStatement prepStmnt = null;
		    try {
		      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypeDelete));
		      prepStmnt.setInt(1, discountType.getId());
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    prepStmntList.add(prepStmnt);
		  }
	 
	@Override
	public void executeDeleteList(List<DTOBase> obj) {
		// TODO Auto-generated method stub
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeUpdate(DTOBase obj) {
		DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		update(conn, prepStmntList, discountType);	
		

		for (Object discountTypePercentageObj : discountType.getDiscountTypePercentageList()) {
		      DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountTypePercentageObj;
		     discountTypePercentage.setDiscountTypeCode(discountType.getCode());
		      discountTypePercentageDAO.update(conn, prepStmntList, discountTypePercentageObj);
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}
	 protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		    DiscountTypeDTO discountType = (DiscountTypeDTO)obj;
		    PreparedStatement prepStmnt = null;
		    try {
		      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypeUpdate));
		      prepStmnt.setString(1, discountType.getCode());
		      prepStmnt.setString(2, discountType.getAcademicYear().getCode());
		      prepStmnt.setString(3, discountType.getSemester().getCode());
		      prepStmnt.setString(4, discountType.getName());
		      prepStmnt.setBoolean(5, discountType.isPercentage()); 
		      prepStmnt.setString(6, discountType.getUpdatedBy());
		      prepStmnt.setTimestamp(7, discountType.getUpdatedTimestamp());
		      prepStmnt.setInt(8, discountType.getId());
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    prepStmntList.add(prepStmnt);
		  }
		
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	public List<DTOBase> getDiscountTypeList() {
		return getDTOList(qryDiscountTypeList);
	}
	
	public DiscountTypeDTO getDiscountTypeByName(String name) {
		return (DiscountTypeDTO) getDTO(qryDiscountTypeByName, name);
	}
	
	public DiscountTypeDTO getDiscountTypeByCode(String code) {
		return (DiscountTypeDTO) getDTO(qryDiscountTypeByCode, code);
	}
	
	public List<DTOBase>  getDiscountTypeListSearchByName(String searchValue) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%" + searchValue + "%");
		return getDTOList(qryDiscountTypeListSearchByName, paramList );
	}
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		  DiscountTypeDTO discountType = new DiscountTypeDTO();
		    discountType.setId(((Integer)getDBVal(resultSet, "id")));
		    discountType.setCode((String)getDBVal(resultSet, "code"));
			discountType.getAcademicYear().setCode((String) getDBVal(resultSet, "academic_year_code"));
			discountType.getSemester().setCode((String) getDBVal(resultSet, "semester_code"));
		    discountType.setName((String)getDBVal(resultSet, "name"));
		    discountType.setPercentage(((Boolean)getDBVal(resultSet, "is_percentage")));
		    discountType.setDisplayText(discountType.getDisplayText());
		    return discountType;
		
	}
	
}

