package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.FeeDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class FeeDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryFeeAdd = "FEE_ADD";
	private String qryFeeUpdate = "FEE_UPDATE";
	private String qryFeeDelete = "FEE_DELETE";
	private String qryFeeLast = "FEE_LAST";
	private String qryFeeByCode = "FEE_BY_CODE";
	private String qryFeeByName = "FEE_BY_NAME";
	private String qryFeeList = "FEE_LIST";
	private String qryFeeListSearchByName = "FEE_LIST_SEARCHBY_NAME";
	
	@Override
	public void executeAdd(DTOBase obj) {
		FeeDTO fee = (FeeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		fee.setCode(getGeneratedCode());
		fee.setBaseDataOnInsert();
		add(conn, prepStmntList, fee);		
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void add(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		FeeDTO fee = (FeeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeAdd));
			prepStmnt.setString(1, fee.getCode());
			prepStmnt.setString(2, fee.getCodeParent());
			prepStmnt.setString(3, fee.getName());
			prepStmnt.setString(4, fee.getAcademicProgramCodes());
			prepStmnt.setBoolean(5, fee.isMandatory());
			prepStmnt.setString(6, fee.getAddedBy());
			prepStmnt.setTimestamp(7, fee.getAddedTimestamp());
			prepStmnt.setString(8, fee.getUpdatedBy());
			prepStmnt.setTimestamp(9, fee.getUpdatedTimestamp());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public String getGeneratedCode() {
		FeeDTO fee = getFeeLast();
		String code = "001"; //default fee code
		if(fee != null) {
			int nextNum = Integer.parseInt(fee.getCode()) + 1; 
			code =  StringUtil.getPadded(String.valueOf(nextNum), 3, "0", true);
		}
		return code;
	}
	
	private FeeDTO getFeeLast() {
		return (FeeDTO) getDTO(qryFeeLast);
	}
	
	public List<DTOBase> getFeeList() {
		return getDTOList(qryFeeList);
	}
	
	public FeeDTO getFeeByCode(String code) {
		return (FeeDTO) getDTO(qryFeeByCode, code);
	}
	
	public FeeDTO getFeeByName(String name) {
		return (FeeDTO) getDTO(qryFeeByName, name);
	}

	public List<DTOBase> getFeeListSearchByName(String searchValue) {
		return getDTOList(qryFeeListSearchByName, "%" + searchValue + "%");
	}
	
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void executeDelete(DTOBase obj) {
		FeeDTO fee = (FeeDTO) obj;
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		delete(conn, prepStmntList, fee);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		FeeDTO fee = (FeeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeDelete));	
			prepStmnt.setInt(1, fee.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		FeeDTO fee = (FeeDTO) obj;
		fee.setBaseDataOnUpdate();
		update(conn, prepStmntList, fee);
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, DTOBase obj) {
		FeeDTO fee = (FeeDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeUpdate));
			prepStmnt.setString(1, fee.getCodeParent());
			prepStmnt.setString(2, fee.getName());
			prepStmnt.setString(3, fee.getAcademicProgramCodes());
			prepStmnt.setBoolean(4, fee.isMandatory());
			prepStmnt.setString(5, fee.getUpdatedBy());
			prepStmnt.setTimestamp(6, fee.getUpdatedTimestamp());	
			prepStmnt.setInt(7, fee.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		FeeDTO fee = new FeeDTO();
		fee.setId((Integer) getDBVal(resultSet, "id"));
		fee.setCode((String) getDBVal(resultSet, "code"));
		fee.setCodeParent((String) getDBVal(resultSet, "code_parent"));
		fee.setName((String) getDBVal(resultSet, "name"));
		fee.setAcademicProgramCodes((String) getDBVal(resultSet, "academic_program_codes"));
		fee.setMandatory((Boolean) getDBVal(resultSet, "is_Mandatory"));
		fee.setDisplayText(fee.getName());
		return fee;
	}
}
