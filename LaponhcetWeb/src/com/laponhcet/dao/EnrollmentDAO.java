package com.laponhcet.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mytechnopal.base.DAOBase;
import com.mytechnopal.base.DTOBase;

public class EnrollmentDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qryEnrollmentListByAcademicYearCodeSectionCode = "ENROLLMENT_LIST_BY_ACADEMICYEARCODESECTIONCODE";
	private String qryEnrollmentListBySemesterCodeSectionCode = "ENROLLMENT_LIST_BY_SEMESTERCODESECTIONCODE";
	
	public List<DTOBase> getEnrollmentListByAcademicYearCodeSectionCode(String academicYearCode, String sectionCode) {
	    List<Object> paramList = new ArrayList<Object>();
		paramList.add(academicYearCode);
		paramList.add(sectionCode);
		return getDTOList(qryEnrollmentListByAcademicYearCodeSectionCode, paramList);
	}
	
	public List<DTOBase> getEnrollmentListBySemesterCodeSectionCode(String semesterCode, String sectionCode) {
	    List<Object> paramList = new ArrayList<Object>();
		paramList.add(semesterCode);
		paramList.add(sectionCode);
		return getDTOList(qryEnrollmentListBySemesterCodeSectionCode, paramList);
	}
	
	@Override
	public void executeAdd(DTOBase arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeUpdate(DTOBase arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected DTOBase rsToObj(ResultSet arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
