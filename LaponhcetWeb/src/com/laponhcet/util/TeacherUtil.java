package com.laponhcet.util;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.base.DataAndSessionBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;
import com.mytechnopal.webcontrol.CheckBoxWebControl;

public class TeacherUtil extends DataAndSessionBase {
	private static final long serialVersionUID = 1L;

	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> ObjList, String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox && sessionInfo.isCurrentLinkDataEntry()){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" + panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable("", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + panelName + "', " + ObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> childObjList, String selectedValue , String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "CheckList\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "  m-t\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" +panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable("", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName + selectedValue, childObjList, strArrCodes.split("~"), StringUtil.getStrArr(childObjList), "onchange=\"toggleCheckParent('chk" + panelName + selectedValue + "', " + childObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getTableWithLabel(SessionInfo sessionInfo, String label, String name, boolean isImportant, List<DTOBase> ObjList, String strArrCodes, String  tableClass, int columnCount, String webControlStr){
		StringBuffer str = new StringBuffer();
		str.append("<label class=\"m-t m-b-xs\">" + label + (isImportant?" <font color=\"red\">*</font>":" ") +  "</label>\n");
		str.append("<div class=\"" + webControlStr + "\">" + WebUtil.getTable(tableClass, new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, name, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + name + "', " + ObjList.size() + ")\""), columnCount) + "</div>");
		return str.toString();
	}
	
	public static List<DTOBase> getArrNameProgramGroupList(String code) {
		String[] codes = code.split("~");
		List<DTOBase> newListLink = new ArrayList<DTOBase>();
		for(int i=0; i < codes.length ; i++){
			newListLink.add(new AcademicProgramDAO().getAcademicProgramByName(codes[i]));
		}
		return newListLink;
	}
	
	public static String getNameList(List<DTOBase> list) {
		String name = "";
		for(DTOBase obj: list) {
			AcademicProgramDTO ap = (AcademicProgramDTO) obj;
			name += " | " + ap.getName();
		}
		return name.substring(3);
	}
	
	public static List<DTOBase> getTeacherListByAcademicProgram(List<DTOBase> userList, List<DTOBase> teacherListByAcademicProgramCode){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase userObj : teacherListByAcademicProgramCode){
			TeacherDTO teacher = (TeacherDTO) userObj;
			UserDTO user = (UserDTO) DTOUtil.getObjByCode(userList, teacher.getCode());
			if(user!=null && !StringUtil.isEmpty(user.getCpNumber())){
				newStudentList.add(setTeacher(teacher, user));
			}
		}
		return newStudentList;
	}
	
	public static List<DTOBase> getTeacherListByAcademicProgram(List<DTOBase> teacherList, String academicProgramCode){
		List<DTOBase> newStudentList = new ArrayList<DTOBase>();
		for(DTOBase teacherObj : teacherList){
			TeacherDTO teacher = (TeacherDTO) teacherObj;
			if(StringUtil.isStrExistInStrArr(teacher.getAcademicProgramCodes().split("~"), academicProgramCode) ){
				if(DTOUtil.getObjByCode(newStudentList, teacher.getCode())==null){
					newStudentList.add(teacher);
				}
			}
		}
		return newStudentList;
	}
	
	public static TeacherDTO setTeacher(TeacherDTO teacher, UserDTO user) {
		teacher.setRfid(user.getRfid());
		teacher.setFacebookId(user.getFacebookId());
		teacher.setUserName(user.getUserName());
		teacher.setPassword(user.getPassword());
		teacher.setUserGroup(user.getUserGroup());
		teacher.setLastName(user.getLastName());
		teacher.setFirstName(user.getFirstName());
		teacher.setMiddleName(user.getMiddleName());
		teacher.setPrefixName(user.getPrefixName());
		teacher.setSuffixName(user.getSuffixName());
		teacher.setOtherTitle(user.getOtherTitle());
		teacher.setGender(user.getGender());
		teacher.setStreetPermanent(user.getStreetPermanent());
		teacher.setBarangayPermanent(user.getBarangayPermanent());
		teacher.setCityPermanent(user.getCityPermanent());
		teacher.setStreetPresent(user.getStreetPresent());
		teacher.setBarangayPresent(user.getBarangayPresent());
		teacher.setCityPresent(user.getCityPresent());
		teacher.setBirthPlace(user.getBirthPlace());
		teacher.setBirthDate(user.getBirthDate());
		teacher.setReligion(user.getReligion());
		teacher.setMaritalStatus(user.getMaritalStatus());
		teacher.setCitizenship(user.getCitizenship());
		teacher.setPassportNumber(user.getPassportNumber());
		teacher.setOccupation(user.getOccupation());
		teacher.setCpNumber(user.getCpNumber());
		teacher.setLandlineNumber(user.getLandlineNumber());
		teacher.setEmailAddress(user.getEmailAddress());
		teacher.setFatherName(user.getFatherName());
		teacher.setFatherOccupation(user.getFatherOccupation());
		teacher.setFatherCpNumber(user.getFatherCpNumber());
		teacher.setMotherName(user.getMotherName());
		teacher.setMotherOccupation(user.getMotherOccupation());
		teacher.setMotherCpNumber(user.getMotherCpNumber());
		teacher.setGuardianName(user.getGuardianName());
		teacher.setGuardianOccupation(user.getGuardianOccupation());
		teacher.setGuardianRelation(user.getGuardianRelation());
		teacher.setContactPerson(user.getContactPerson());
		teacher.setContactRelation(user.getContactRelation());
		teacher.setContactAddress(user.getContactAddress());
		teacher.setContactCPNumber(user.getContactCPNumber());
		teacher.setContactLandlineNumber(user.getContactLandlineNumber());
		teacher.setContactEmailAddress(user.getContactEmailAddress());
		teacher.setContactFacebookId(user.getContactFacebookId());
		teacher.setSourceDeviceInfo(user.getSourceDeviceInfo());
		teacher.setActive(user.isActive());
		teacher.setHtmlSkin(user.getHtmlSkin());
		teacher.setLastLoginTimestamp(user.getLastLoginTimestamp());
		teacher.setLastLoginIPAddress(user.getLastLoginIPAddress());
		teacher.setProfilePict(user.getProfilePict());
		teacher.setDisplayText(teacher.getName(false, false, true));
		return teacher;
	}
	
	//user obj converted to teacher obj
	public static TeacherDTO getTeacher(DTOBase userObj) {
		TeacherDTO teacher= (TeacherDTO) userObj;
		UserDTO user = new UserDAO().getUserByCode(teacher.getCode());
		setTeacher(teacher, user);
		return teacher;
	}
	
	public static List<DTOBase> getTeacherListSearchByName(List<DTOBase> objList) {
		List<DTOBase> newUserList = new ArrayList<DTOBase>();
		List<DTOBase> userList = new UserDAO().getUserList();
		List<DTOBase> teacherList = new TeacherDAO().getTeacherList();
		for(DTOBase obj: objList) {
			UserDTO user = (UserDTO) DTOUtil.getObjByCode(userList, obj.getCode());
			TeacherDTO teacher = (TeacherDTO) DTOUtil.getObjByCode(teacherList, user.getCode());
			newUserList.add(setTeacher(teacher, user));
		}
		return newUserList;
	}
	public static String getProfilePicture(TeacherDTO teacher){
		String pict = !StringUtil.isEmpty(teacher.getProfilePict())?teacher.getProfilePict():" ";
		return "<img src='" + pict + "' class='thumbnail' width='70' height='70' />";
	}
		
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> academicProgramList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			TeacherDTO teacher = (TeacherDTO) dto;
			UserDTO user = new UserDAO().getUserByCode(teacher.getCode());
			teacher.setPaginationRecord(new String[]{TeacherUtil.getProfilePicture(teacher), user.getCode(), user.getLastName(), user.getFirstName(), user.getMiddleName(),  AcademicProgramUtil.getAcademicProgramCodes(academicProgramList, teacher.getAcademicProgramCodes()), pagination.getLinkButtonStr(sessionInfo, teacher.getId()).replace("~", ",")});
		}
	}
}

