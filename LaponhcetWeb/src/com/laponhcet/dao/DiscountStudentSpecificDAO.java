package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.laponhcet.dto.DiscountStudentSpecificDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class DiscountStudentSpecificDAO extends DAOBase{
private static final long serialVersionUID = 1L;


private String qryDiscountStudentSpecificAdd = "DISCOUNT_STUDENT_SPECIFIC_ADD";
private String qryDiscountStudentSpecificUpdate = "DISCOUNT_STUDENT_SPECIFIC_UPDATE";
private String qryDiscountStudentSpecificDelete = "DISCOUNT_STUDENT_SPECIFIC_DELETE";
private String qryDiscountStudentSpecificListByStudentCode = "DISCOUNT_STUDENT_SPECIFIC_LIST_BY_STUDENTCODE";
private String qryDiscountStudentSpecificBySemesterCodeStudentCode = "DISCOUNT_STUDENT_SPECIFIC_BY_SEMESTERCODESTUDENTCODE";
private String qryDiscountStudentSpecificByAcademicYearCodeStudentCode = "DISCOUNT__STUDENT_SPECIFIC_BY_ACADEMICYEARCODESTUDENTCODE";
private String qryDiscountStudentSpecificList = "DISCOUNT_STUDENT_SPECIFIC_LIST";
private String qryDiscountStudentSpecificListByDiscountTypeCode = "DISCOUNT_STUDENT_SPECIFIC_LIST_BY_DISCOUNT_TYPE_CODE";


	@SuppressWarnings("unchecked")
	@Override
	public void executeAdd(DTOBase obj) {
		DiscountStudentSpecificDTO discountStudentSpecific = (DiscountStudentSpecificDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		discountStudentSpecific.setCode(StringUtil.getUniqueId());
		discountStudentSpecific.setBaseDataOnInsert();
		add(conn, prepStmntList, discountStudentSpecific);	
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) obj;
	    PreparedStatement prepStmnt = null;
	    try {
	      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountStudentSpecificAdd));
	      prepStmnt.setString(1, discount.getCode());
	      prepStmnt.setString(2, discount.getSemester().getAcademicYear().getCode());
	      prepStmnt.setString(3, discount.getSemester().getCode());
	      prepStmnt.setString(4, discount.getStudent().getCode());
	      prepStmnt.setString(5, discount.getDiscountType().getCode());
	      prepStmnt.setDouble(6, discount.getAmount());
	      prepStmnt.setString(7, discount.getRemark());
	      prepStmnt.setString(8, discount.getAddedBy());
	      prepStmnt.setTimestamp(9, discount.getAddedTimestamp());
	      prepStmnt.setString(10, discount.getUpdatedBy());
	      prepStmnt.setTimestamp(11, discount.getUpdatedTimestamp());
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
		DiscountStudentSpecificDTO discountStudentSpecific = (DiscountStudentSpecificDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, discountStudentSpecific);	
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	 protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		 DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) obj;
		    PreparedStatement prepStmnt = null;
		    try {
		      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountStudentSpecificDelete));
		      prepStmnt.setInt(1, discount.getId());
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
		DiscountStudentSpecificDTO discountStudentSpecific = (DiscountStudentSpecificDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		update(conn, prepStmntList, discountStudentSpecific);	
		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
		
	}

	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountStudentSpecificDTO discount = (DiscountStudentSpecificDTO) obj;
	    PreparedStatement prepStmnt = null;
	    try {
	      prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountStudentSpecificUpdate));
	      prepStmnt.setString(1, discount.getCode());
	      prepStmnt.setString(2, discount.getSemester().getAcademicYear().getCode());
	      prepStmnt.setString(3, discount.getSemester().getCode());
	      prepStmnt.setString(4, discount.getStudent().getCode());
	      prepStmnt.setString(5, discount.getDiscountType().getCode());
	      prepStmnt.setDouble(6, discount.getAmount());
	      prepStmnt.setString(7, discount.getRemark());
	      prepStmnt.setString(8, discount.getUpdatedBy());
	      prepStmnt.setTimestamp(9, discount.getUpdatedTimestamp());
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    prepStmntList.add(prepStmnt);
	  }

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<DTOBase> getDiscountListByStudentCode(String studentCode) {
	    return getDTOList(qryDiscountStudentSpecificListByStudentCode, studentCode);
	  }
	  
	  public DiscountStudentSpecificDTO getDiscountBySemesterCodeStudentCode(String semesterCode, String studentCode) {
		  List<Object> paramList = new ArrayList<Object>();
	    paramList.add(semesterCode);
	    paramList.add(studentCode);
	    return (DiscountStudentSpecificDTO)getDTO(qryDiscountStudentSpecificBySemesterCodeStudentCode, paramList);
	  }
	  
	  public DiscountStudentSpecificDTO getDiscountByAcademicYearCodeStudentCode(String academicYearCode, String studentCode) {
		  List<Object> paramList = new ArrayList<Object>();
	    paramList.add(academicYearCode);
	    paramList.add(studentCode);
	    return (DiscountStudentSpecificDTO)getDTO(qryDiscountStudentSpecificByAcademicYearCodeStudentCode, paramList);
	  }
	  
	  public List<DTOBase> getDiscountList() {
	    return getDTOList(qryDiscountStudentSpecificList);
	  }
	  
	  public List<DTOBase> getDiscountListByDiscountTypeCode(String discountTypeCode) {
	    return getDTOList(qryDiscountStudentSpecificListByDiscountTypeCode, discountTypeCode);
	  }

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		DiscountStudentSpecificDTO discount = new DiscountStudentSpecificDTO();
	    discount.setId(((Integer)getDBVal(resultSet, "id")).intValue());
	    discount.setCode((String)getDBVal(resultSet, "code"));
	    discount.getSemester().getAcademicYear().setCode((String)getDBVal(resultSet, "academic_year_code"));
	    discount.getSemester().setCode((String)getDBVal(resultSet, "semester_code"));
	    discount.getStudent().setCode((String)getDBVal(resultSet, "student_code"));
	    discount.getDiscountType().setCode((String)getDBVal(resultSet, "discount_type_code"));
	    discount.setAmount(((Double)getDBVal(resultSet, "amount")));
	    discount.setRemark((String)getDBVal(resultSet, "remark"));
	    return discount;
	}

}
