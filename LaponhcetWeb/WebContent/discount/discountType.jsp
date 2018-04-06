<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %> 

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	List<DTOBase> academicYearList = (ArrayList<DTOBase>)session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
	List<DTOBase> semesterList = (ArrayList<DTOBase>)session.getAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
	DiscountTypeDTO discountType = (DiscountTypeDTO)session.getAttribute(DiscountTypeDTO.SESSION_DISCOUNT_TYPE);
%>

<%=new RadioButtonWebControl().getRadioButtonWebControl(sessionInfo, "col-sm-12", true, "Term", "SchoolTerm", new String[] {SettingsUtil.SCHOOL_TERM_ACADEMIC_YEAR, SettingsUtil.SCHOOL_TERM_SEMESTER}, discountType.getSchoolTerm(), new String[] {SettingsUtil.SCHOOL_TERM_ACADEMIC_YEAR, SettingsUtil.SCHOOL_TERM_SEMESTER}, "onclick='toggleTerm()'", false) %>
<div class="row">
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Academic Year", "AcademicYear", academicYearList, discountType.getAcademicYear(), "--Select Academic Year--", "0", "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Semester", "Semester", semesterList, discountType.getSemester(), "--Select Semester--", "0", "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Name", "Name", "Name", discountType.getName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new RadioButtonWebControl().getRadioButtonWebControl(sessionInfo, "col-sm-2", false, "Percentage", "Percentage", new String[] {"YES", "NO"}, discountType.isPercentage()?"YES":"NO", new String[] {"YES", "NO"}, "onclick='toggleDiscountTypePercentage()'", false) %>
</div><br>

<div class="<%=discountType.isPercentage()?"ibox":"ibox hidden"%>" id="divDiscountTypePercentage" style="margin-top:-10px;">
	<%
	String[] discountTypePercentageFeeArr = new String[discountType.getDiscountTypePercentageList().size()];
	for(int i=0; i<discountType.getDiscountTypePercentageList().size(); i++) {
		DiscountTypePercentageDTO discountTypePercentage = (DiscountTypePercentageDTO)discountType.getDiscountTypePercentageList().get(i);
		discountTypePercentageFeeArr[i] = new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", false, discountTypePercentage.getFee().getName()+" [%]", "", discountTypePercentage.getFee().getCode(), StringUtil.getFormattedNum(discountTypePercentage.getPercent(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 9, TextBoxWebControl.DATA_TYPE_DOUBLE, "");
	}
	%>
	<div class="ibox">
		<%=WebUtil.getTable("table", discountTypePercentageFeeArr, discountType.getDiscountTypePercentageList().size()) %>
	</div>
</div>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-default", "align='center'") %> 
<script>
	function toggleTerm() {
		if (document.getElementById("rb<%=SettingsUtil.SCHOOL_TERM_ACADEMIC_YEAR%>").checked) {
			document.getElementById("divAcademicYear").className = "col-sm-4";
			document.getElementById("divSemester").className = "col-sm-4 hidden";
		}
		else {
			document.getElementById("divAcademicYear").className = "col-sm-4 hidden";
			document.getElementById("divSemester").className = "col-sm-4";
		}
	}
	
	function toggleDiscountTypePercentage() {
		if (document.getElementById("rbYES").checked) {
			document.getElementById("divDiscountTypePercentage").className = "ibox";
		}
		else {
			document.getElementById("divDiscountTypePercentage").className = "ibox hidden";
		}
	}
	toggleTerm();
</script>