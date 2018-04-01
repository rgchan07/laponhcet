package com.laponhcet.action.fee;

import java.util.List;
import com.laponhcet.dao.FeeDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.FeeDTO;
import com.laponhcet.util.AcademicProgramUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.StringUtil;

public class FeeAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setInput() {
		FeeDTO fee = (FeeDTO) getSessionAttribute(FeeDTO.SESSION_FEE);
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> academicProgramGroupList = (List<DTOBase>) getSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);

		fee.setName(getRequestString("txtName"));
		String academicProgramCodes = "";
		for(int i=0; i<academicProgramGroupList.size(); i++) {
			AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)academicProgramGroupList.get(i);
			String selectedAcademicProgramCodes = getSelectedCheckBox(AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode()), "AcademicProgram"+academicProgramGroup.getCode());
			if(selectedAcademicProgramCodes.length() >=1 && academicProgramCodes.length() >= 1) {
				academicProgramCodes += "~";
			}
			academicProgramCodes += selectedAcademicProgramCodes;
		}
		fee.setAcademicProgramCodes(academicProgramCodes);
		fee.setMandatory(getRequestBoolean("chkMandatory"));
	}
	
	protected void validateInput() {
		if(!sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("U00053")) {
			FeeDTO fee = (FeeDTO) getSessionAttribute(FeeDTO.SESSION_FEE);
			ActionResponse actionResponse = super.actionResponse;
			if(StringUtil.isEmpty(fee.getName())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Name");
			}else if(StringUtil.isEmpty(fee.getAcademicProgramCodes())) {
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Academic Program");
			}else {
				FeeDTO feeNameExist = new FeeDAO().getFeeByName(fee.getName());
				if(sessionInfo.isPreviousLinkAdd() || sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("U00053")) {
					if(feeNameExist!=null) {
						actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
					}
				}
				else if(sessionInfo.isPreviousLinkUpdate() || sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("U00053")) {
					FeeDTO feeOrig = (FeeDTO) getSessionAttribute(FeeDTO.SESSION_FEE + "_ORIG");
					if(feeNameExist!=null) {
						if(!feeOrig.getName().equalsIgnoreCase(fee.getName())) {
							actionResponse.constructMessage(ActionResponse.TYPE_EXIST, "Name");
						}
					}
				}
			}
		}
	}
}
