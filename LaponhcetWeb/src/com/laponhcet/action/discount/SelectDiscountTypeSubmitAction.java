package com.laponhcet.action.discount;

public class SelectDiscountTypeSubmitAction extends StudentSpecificDiscountAction {
	private static final long serialVersionUID = 1L;

	 protected void init() { 
		  isValidateInput = false; 
	  
	  }  
	protected void setSessionVars() {
		 sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
}
