package com.laponhcet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dto.FeeProgramSpecificDTO;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class FeeProgramSpecificDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryFeeProgramSpecificAdd = "FEE_PROGRAM_SPECIFIC_ADD";
	private String qryFeeProgramSpecificUpdate = "FEE_PROGRAM_SPECIFIC_UPDATE";
	private String qryFeeProgramSpecificDelete = "FEE_PROGRAM_SPECIFIC_DELETE";
	private String qryFeeProgramSpecificLast = "FEE_PROGRAM_SPECIFIC_LAST";
	private String qryFeeProgramSpecificByFeeCodeSemesterCodeAcademicProgramCodeYearLevel = "FEE_PROGRAM_SPECIFIC_BY_FEECODESEMESTERCODEACADEMICPROGRAMCODEYEARLEVEL";
	private String qryFeeProgramSpecificByFeeCodeAcademicYearCodeAcademicProgramCodeYearLevel = "FEE_PROGRAM_SPECIFIC_BY_FEECODEACADEMICYEARCODEACADEMICPROGRAMCODEYEARLEVEL";
	private String qryFeeProgramSpecificList = "FEE_PROGRAM_SPECIFIC_LIST";
	private String qryFeeProgramSpecificByAcademicProgramCode = "FEE_PROGRAM_SPECIFIC_LIST_BY_ACADEMICPROGRAMCODE";
	private String qryFeeProgramSpecificListByFeeCode = "FEE_PROGRAM_SPECIFIC_LIST_BY_FEECODE";
	private String qryFeeProgramSpecificListBySemesterCode = "FEE_PROGRAM_SPECIFIC_LIST_BY_SEMESTERCODE";
	private String qryFeeProgramSpecificListBySemesterCodeAcademicProgramCodeYearLevel = "FEE_PROGRAM_SPECIFIC_LIST_BY_SEMESTERCODEACADEMICPROGRAMCODEYEARLEVEL";
	private String qryFeeProgramSpecificListByAcademicYearCode = "FEE_PROGRAM_SPECIFIC_LIST_BY_ACADEMICYEARCODE";
	private String qryFeeProgramSpecificListByAcademicYearCodeAcademicProgramCodeYearLevel = "FEE_PROGRAM_SPECIFIC_LIST_BY_ACADEMICYEARCODEACADEMICPROGRAMCODEYEARLEVEL";
	
	@Override
	public void executeAdd(DTOBase obj) {

	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, FeeProgramSpecificDTO feeProgramSpecific) {
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeProgramSpecificAdd));
			prepStmnt.setString(1, feeProgramSpecific.getCode());
			//ELEM = 001
			//JHS = 002
			if(feeProgramSpecific.getAcademicProgram().getAcademicProgramSubgroup().getCode().equalsIgnoreCase("001")||feeProgramSpecific.getAcademicProgram().getAcademicProgramSubgroup().getCode().equalsIgnoreCase("002")) {
				prepStmnt.setString(2, feeProgramSpecific.getSemester().getAcademicYear().getCode());
				prepStmnt.setString(3, "");
			}
			else {
				prepStmnt.setString(2, "");
				prepStmnt.setString(3, feeProgramSpecific.getSemester().getCode());
			}
			prepStmnt.setString(4, feeProgramSpecific.getAcademicProgram().getCode());
			prepStmnt.setInt(5, feeProgramSpecific.getYearLevel());
			prepStmnt.setString(6, feeProgramSpecific.getFee().getCode());
			prepStmnt.setDouble(7, feeProgramSpecific.getEnrollmentStatusContinuing());
			prepStmnt.setDouble(8, feeProgramSpecific.getEnrollmentStatusNew());
			prepStmnt.setDouble(9, feeProgramSpecific.getEnrollmentStatusReturnee());
			prepStmnt.setDouble(10, feeProgramSpecific.getEnrollmentStatusShiftee());
			prepStmnt.setDouble(11, feeProgramSpecific.getEnrollmentStatusTransferee());
			prepStmnt.setDouble(12, feeProgramSpecific.getEnrollmentStatusCrossEnrollee());
			prepStmnt.setString(13, feeProgramSpecific.getAddedBy());
			prepStmnt.setTimestamp(14, feeProgramSpecific.getAddedTimestamp());
			prepStmnt.setString(15, feeProgramSpecific.getUpdatedBy());
			prepStmnt.setTimestamp(16, feeProgramSpecific.getUpdatedTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	
	private FeeProgramSpecificDTO getFeeLast() {
		return (FeeProgramSpecificDTO) getDTO(qryFeeProgramSpecificLast);
	}
	
	@Override
	public void executeDelete(DTOBase obj) {
		
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		FeeProgramSpecificDTO feeProgramSpecific = (FeeProgramSpecificDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeProgramSpecificDelete));
			prepStmnt.setInt(1, feeProgramSpecific.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, FeeProgramSpecificDTO feeProgramSpecific) {
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFeeProgramSpecificUpdate));
			prepStmnt.setDouble(1, feeProgramSpecific.getEnrollmentStatusContinuing());
			prepStmnt.setDouble(2, feeProgramSpecific.getEnrollmentStatusNew());
			prepStmnt.setDouble(3, feeProgramSpecific.getEnrollmentStatusReturnee());
			prepStmnt.setDouble(4, feeProgramSpecific.getEnrollmentStatusShiftee());
			prepStmnt.setDouble(5, feeProgramSpecific.getEnrollmentStatusTransferee());
			prepStmnt.setDouble(6, feeProgramSpecific.getEnrollmentStatusCrossEnrollee());
			prepStmnt.setString(7, feeProgramSpecific.getUpdatedBy());
			prepStmnt.setTimestamp(8, feeProgramSpecific.getUpdatedTimestamp());
			prepStmnt.setInt(9, feeProgramSpecific.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	public FeeProgramSpecificDTO getFeeProgramSpecificByFeeCodeSemesterCodeAcademicProgramCodeYearLevel(String feeCode, String semesterCode, String academicProgramCode, int yearLevel) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(feeCode);
		paramList.add(semesterCode);
		paramList.add(academicProgramCode);
		paramList.add(yearLevel);
		return (FeeProgramSpecificDTO) getDTO(qryFeeProgramSpecificByFeeCodeSemesterCodeAcademicProgramCodeYearLevel, paramList);
	}
	
	public FeeProgramSpecificDTO getFeeProgramSpecificByFeeCodeAcademicYearCodeAcademicProgramCodeYearLevel(String feeCode, String academicYearCode, String academicProgramCode, int yearLevel) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(feeCode);
		paramList.add(academicYearCode);
		paramList.add(academicProgramCode);
		paramList.add(yearLevel);
		return (FeeProgramSpecificDTO) getDTO(qryFeeProgramSpecificByFeeCodeAcademicYearCodeAcademicProgramCodeYearLevel, paramList);
	}
	
	public List<DTOBase> getFeeProgramSpecificList() {
		return getDTOList(qryFeeProgramSpecificList);
	}
	
	public List<DTOBase> getFeeProgramSpecificListByFeeCode(String feeCode) {
		return getDTOList(qryFeeProgramSpecificListByFeeCode, feeCode);
	}
	
	public List<DTOBase> getFeeProgramSpecificListBySemesterCode(String semesterCode) {
		return getDTOList(qryFeeProgramSpecificListBySemesterCode, semesterCode);
	}
	
	public FeeProgramSpecificDTO getFeeProgramSpecificByAcademicProgramCode(String academicProgramCode) {
		return (FeeProgramSpecificDTO) getDTOList(qryFeeProgramSpecificByAcademicProgramCode, academicProgramCode);
	}
	
	public List<DTOBase> getFeeProgramSpecificListBySemesterCodeAcademicProgramCodeYearLevel(String semesterCode, String academicProgramCode, int yearLevel) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(semesterCode);
		paramList.add(academicProgramCode);
		paramList.add(yearLevel);
		return getDTOList(qryFeeProgramSpecificListBySemesterCodeAcademicProgramCodeYearLevel, paramList);
	}
	
	public List<DTOBase> getFeeProgramSpecificListByAcademicYearCode(String academicYearCode) {
		return getDTOList(qryFeeProgramSpecificListByAcademicYearCode, academicYearCode);
	}
	
	public List<DTOBase> getFeeProgramSpecificListByAcademicYearCodeAcademicProgramCodeYearLevel(String academicYearCode, String academicProgramCode, int yearLevel) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(academicYearCode);
		paramList.add(academicProgramCode);
		paramList.add(yearLevel);
		return getDTOList(qryFeeProgramSpecificListByAcademicYearCodeAcademicProgramCodeYearLevel, paramList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		FeeProgramSpecificDTO feeProgramSpecific = new FeeProgramSpecificDTO();
		feeProgramSpecific.setId((Integer) getDBVal(resultSet, "id"));
		feeProgramSpecific.setCode((String) getDBVal(resultSet, "code"));
		feeProgramSpecific.getSemester().getAcademicYear().setCode((String) getDBVal(resultSet, "academic_year_code"));
		feeProgramSpecific.getSemester().setCode((String) getDBVal(resultSet, "semester_code"));
		feeProgramSpecific.getAcademicProgram().setCode((String) getDBVal(resultSet, "program_code"));
		feeProgramSpecific.setYearLevel((Integer) getDBVal(resultSet, "year_level"));
		feeProgramSpecific.getFee().setCode((String) getDBVal(resultSet, "fee_code"));
		feeProgramSpecific.setEnrollmentStatusContinuing((Double) getDBVal(resultSet, "enrollment_status_continuing"));
		feeProgramSpecific.setEnrollmentStatusNew((Double) getDBVal(resultSet, "enrollment_status_new"));
		feeProgramSpecific.setEnrollmentStatusReturnee((Double) getDBVal(resultSet, "enrollment_status_returnee"));
		feeProgramSpecific.setEnrollmentStatusShiftee((Double) getDBVal(resultSet, "enrollment_status_shiftee"));
		feeProgramSpecific.setEnrollmentStatusTransferee((Double) getDBVal(resultSet, "enrollment_status_transferee"));
		feeProgramSpecific.setEnrollmentStatusCrossEnrollee((Double) getDBVal(resultSet, "enrollment_status_cross_enrollee"));
		feeProgramSpecific.setDisplayText(feeProgramSpecific.getDisplayText());
		return feeProgramSpecific;
	}

	@Override
	public void executeAddList(List<DTOBase> objList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeDeleteList(List<DTOBase> objList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeUpdateList(List<DTOBase> feeProgramSpecificList) {
		List<FeeProgramSpecificDTO> feeProgramSpecificListForAdd = new ArrayList<FeeProgramSpecificDTO>();
		List<FeeProgramSpecificDTO> feeProgramSpecificListForUpdate = new ArrayList<FeeProgramSpecificDTO>();
		List<FeeProgramSpecificDTO> feeProgramSpecificListForDelete = new ArrayList<FeeProgramSpecificDTO>();
		
		Timestamp timeStamp = DateTimeUtil.getCurrentTimestamp();
		
		for(DTOBase feeProgramSpecificDTO: feeProgramSpecificList) {
			FeeProgramSpecificDTO feeProgramSpecific = (FeeProgramSpecificDTO) feeProgramSpecificDTO;
			//System.out.println(feeProgramSpecific.getProgram().getCode() + " " + feeProgramSpecific.getFeeSubgroup().getCode() + " " + String.valueOf( feeProgramSpecific.getId() == 0));
			Double amount = 0d;
			amount = feeProgramSpecific.getEnrollmentStatusContinuing() + feeProgramSpecific.getEnrollmentStatusCrossEnrollee() + feeProgramSpecific.getEnrollmentStatusNew() + feeProgramSpecific.getEnrollmentStatusReturnee() + feeProgramSpecific.getEnrollmentStatusShiftee() + feeProgramSpecific.getEnrollmentStatusTransferee();
			if(feeProgramSpecific.getId() == 0) {
				if(amount<1) {
					feeProgramSpecificListForAdd.add(feeProgramSpecific);
				}
			}
			else {
				if(amount<1) {
					feeProgramSpecificListForUpdate.add(feeProgramSpecific);
				}
				else {
					feeProgramSpecificListForDelete.add(feeProgramSpecific);
				}
			}
		}
		
		Connection conn = daoConnectorUtil.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		
		if(feeProgramSpecificListForAdd.size()>=1) {
			int ctr = 0;
			FeeProgramSpecificDTO feeProgramSpecificLast = getFeeLast();
			if(feeProgramSpecificLast != null) {
				ctr = Integer.parseInt(feeProgramSpecificLast.getCode());
			}
			
			for(FeeProgramSpecificDTO feeProgramSpecific: feeProgramSpecificListForAdd) {
				feeProgramSpecific.setCode(StringUtil.getPadded(String.valueOf(++ctr), 4, "0", true));
				feeProgramSpecific.setUpdatedTimestamp(timeStamp);
				feeProgramSpecific.setBaseDataOnInsert();
				add(conn, prepStmntList, feeProgramSpecific);
			}
		}
		
		if(feeProgramSpecificListForUpdate.size()>=1) {
			for(FeeProgramSpecificDTO feeProgramSpecific: feeProgramSpecificListForUpdate) {
				feeProgramSpecific.setUpdatedTimestamp(timeStamp);
				feeProgramSpecific.setBaseDataOnUpdate();
				update(conn, prepStmntList, feeProgramSpecific);
			}
		}
		
		if(feeProgramSpecificListForDelete.size()>=1) {
			for(FeeProgramSpecificDTO feeProgramSpecific: feeProgramSpecificListForDelete) {
				delete(conn, prepStmntList, feeProgramSpecific);
			}
		}
		result.put(ActionResponse.SESSION_ACTION_RESPONSE, executeIUD(conn, prepStmntList));
	}
	
	public static void main(String[] a) {
		for(DTOBase obj: new FeeProgramSpecificDAO().getFeeProgramSpecificListBySemesterCodeAcademicProgramCodeYearLevel("012", "SUP", 1)) {
			FeeProgramSpecificDTO feeProgramSpecific = (FeeProgramSpecificDTO)obj;
			System.out.println(feeProgramSpecific.getCode());
		}
	}
}
