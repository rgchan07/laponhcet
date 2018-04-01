package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.DiscountTypeDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class DiscountTypeDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryDiscountTypeAdd = "DISCOUNT_TYPE_ADD";
	private String qryDiscountTypeUpdate = "DISCOUNT_TYPE_UPDATE";
	private String qryDiscountTypeDelete = "DISCOUNT_TYPE_DELETE";
	private String qryDiscountTypeLast = "DISCOUNT_TYPE_LAST";
	private String qryDiscountTypeList = "DISCOUNT_TYPE_LIST";
	private String qryDiscountTypeByCode = "DISCOUNT_TYPE_BY_CODE";
	
	@Override
	public void executeAdd(DTOBase obj) {
		//DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		discountType.setCode(getGeneratedCode());
		discountType.setBaseDataOnInsert();
		add(conn, prepStmntList, discountType);
		/*for(Object discountTypePercentageObj: discountType.getDiscountTypePercentageList()) {
			DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) discountTypePercentageObj;
			discountTypePercentage.setDiscountTypeCode(discountType.getCode());
			discountTypePercentageDAO.add(conn, prepStmntList, discountTypePercentageObj);
		}*/
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	private String getGeneratedCode() {
		DiscountTypeDTO discountType = getDiscountTypeLast();
		String code = "001"; //default code
		if(discountType != null) {
			int nextNum = Integer.parseInt(discountType.getCode()) + 1;
			code = StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
		}
		return code;
	}
	
	private DiscountTypeDTO getDiscountTypeLast() {
		return (DiscountTypeDTO) getDTO(qryDiscountTypeLast);
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
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
	public void executeDelete(DTOBase obj) {
		//DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		delete(conn, prepStmntList, discountType);
		/*for(Object discountTypePercentageObj: discountType.getDiscountTypePercentageList()) {
			DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) discountTypePercentageObj;
			discountTypePercentageDAO.delete(conn, prepStmntList, discountTypePercentageObj);
		}*/
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
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
	public void executeUpdate(DTOBase obj) {
		//DiscountTypePercentageDAO discountTypePercentageDAO = new DiscountTypePercentageDAO();
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		discountType.setBaseDataOnUpdate();
		update(conn, prepStmntList, discountType);
		/*for(Object discountTypePercentageObj: discountType.getDiscountTypePercentageList()) {
			DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO) discountTypePercentageObj;
			discountTypePercentageDAO.update(conn, prepStmntList, discountTypePercentageObj);
		}*/
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}

	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		DiscountTypeDTO discountType = (DiscountTypeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryDiscountTypeUpdate));
			prepStmnt.setString(1, discountType.getName());
			prepStmnt.setBoolean(2, discountType.isPercentage());
			prepStmnt.setString(3, discountType.getUpdatedBy());
			prepStmnt.setTimestamp(4, discountType.getUpdatedTimestamp());	
			prepStmnt.setInt(5, discountType.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public List<DTOBase> getDiscountTypeList() {
		return getDTOList(qryDiscountTypeList);
	}
	
	public DiscountTypeDTO getDiscountTypeByCode(String code) {
		return (DiscountTypeDTO) getDTO(qryDiscountTypeByCode, code);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		DiscountTypeDTO discountType = new DiscountTypeDTO();
		discountType.setId((Integer) getDBVal(resultSet, "id"));
		discountType.setCode((String) getDBVal(resultSet, "code"));
		discountType.getAcademicYear().setCode((String) getDBVal(resultSet, "academic_year_code"));
		discountType.getSemester().setCode((String) getDBVal(resultSet, "semester_code"));
		discountType.setName((String) getDBVal(resultSet, "name"));
		discountType.setPercentage((Boolean) getDBVal(resultSet, "is_percentage"));
		discountType.setDisplayText(discountType.getDisplayText());
		return discountType;
	}

	public static void main(String[] a) {
		DiscountTypeDAO discountTypeDAO = new DiscountTypeDAO();
		for(Object obj: discountTypeDAO.getDiscountTypeList()) {
			DiscountTypeDTO discountType = (DiscountTypeDTO)obj;
			System.out.println(discountType.getId());
			System.out.println("name: " + " " + discountType.getName());
		}
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
}
