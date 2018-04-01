package com.laponhcet.dto;
import com.mytechnopal.base.DTOBase;

public class EnrollmentDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_ENROLLMENT = "SESSION_ENROLLMENT";
	public static final String SESSION_ENROLLMENT_LIST = "SESSION_ENROLLMENT_LIST";
	public static final String SESSION_ENROLLMENT_PAGINATION = "SESSION_ENROLLMENT_PAGINATION";
	
	public static String ENROLLMENT_STATUS_CONTINUING = "CONTINUING";
	public static String ENROLLMENT_STATUS_CONTINUING_CODE = "C";
	
	public static String ENROLLMENT_STATUS_CROSS_ENROLLEE = "CROSS ENROLLEE";
	public static String ENROLLMENT_STATUS_CROSS_ENROLLEE_CODE = "X";
	
	public static String ENROLLMENT_STATUS_NEW = "NEW";
	public static String ENROLLMENT_STATUS_NEW_CODE = "N";
	
	public static String ENROLLMENT_STATUS_RETURNEE = "RETURNEE";
	public static String ENROLLMENT_STATUS_RETURNEE_CODE = "R";
	
	public static String ENROLLMENT_STATUS_SHIFTEE = "SHIFTEE";
	public static String ENROLLMENT_STATUS_SHIFTEE_CODE = "S";
	
	public static String ENROLLMENT_STATUS_TRANSFEREE = "TRANSFEREE";
	public static String ENROLLMENT_STATUS_TRANSFEREE_CODE = "T";
	
	public static String[] ENROLLMENT_STATUS_ARRLIST = new String[] {ENROLLMENT_STATUS_CONTINUING, ENROLLMENT_STATUS_CROSS_ENROLLEE, ENROLLMENT_STATUS_NEW, ENROLLMENT_STATUS_RETURNEE, ENROLLMENT_STATUS_SHIFTEE, ENROLLMENT_STATUS_TRANSFEREE};
	public static String[] ENROLLMENT_STATUS_CODE_ARRLIST = new String[] {ENROLLMENT_STATUS_CONTINUING_CODE, ENROLLMENT_STATUS_CROSS_ENROLLEE_CODE, ENROLLMENT_STATUS_NEW_CODE, ENROLLMENT_STATUS_RETURNEE_CODE, ENROLLMENT_STATUS_SHIFTEE_CODE, ENROLLMENT_STATUS_TRANSFEREE_CODE};
		
	private StudentDTO student;
	private SemesterDTO semester;
	private int yearLevel;
	private AcademicSectionDTO academicSection;
	private String status;
	private String updateKey;
	
	public EnrollmentDTO() {
		super();
		student = new StudentDTO();
		semester = new SemesterDTO();
		yearLevel = 0;
		academicSection = new AcademicSectionDTO();
		status = ENROLLMENT_STATUS_CONTINUING;
		updateKey = "";
	}
	
	public EnrollmentDTO getEnrollment() {
		EnrollmentDTO enrollment = new EnrollmentDTO();
		enrollment.setId(super.getId());
		enrollment.setCode(super.getCode());
		enrollment.setStudent(this.student);
		enrollment.setSemester(this.semester);
		enrollment.setYearLevel(this.yearLevel);
		enrollment.setAcademicSection(this.academicSection);
		enrollment.setStatus(this.status);
		enrollment.setUpdateKey(this.updateKey);
		return enrollment;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public SemesterDTO getSemester() {
		return semester;
	}

	public void setSemester(SemesterDTO semester) {
		this.semester = semester;
	}

	public int getYearLevel() {
		return yearLevel;
	}

	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}

	public AcademicSectionDTO getAcademicSection() {
		return academicSection;
	}

	public void setAcademicSection(AcademicSectionDTO academicSection) {
		this.academicSection = academicSection;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
}