package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class VenueDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_VENUE = "SESSION_VENUE";
	public static final String SESSION_VENUE_LIST = "SESSION_VENUE_LIST";
	public static final String SESSION_VENUE_PAGINATION = "SESSION_VENUE_PAGINATION";
	
	public static String[] SEARCHCRITERIA = new String[] {"Code","Name"};
	
	private String name;
	private String location;
	private String academicProgramCodes;
	private int concurrentSession;
	private int maxPax; //maximum number of persons can accommodate
	private int columnCount;
	private String rowColumn;

	public VenueDTO() {
		super();
		name = "";
		location = "";
		academicProgramCodes = "";
		concurrentSession = 1;
		maxPax = 999;
		columnCount = 0;
		rowColumn = "";
	}
	
	public VenueDTO getVenue() {
		VenueDTO venue = new VenueDTO();
		venue.setId(super.getId());
		venue.setCode(super.getCode());
		venue.setName(this.name);
		venue.setLocation(this.location);
		venue.setAcademicProgramCodes(this.academicProgramCodes);
		venue.setConcurrentSession(this.concurrentSession);
		venue.setMaxPax(this.maxPax);
		venue.setColumnCount(this.columnCount);
		venue.setRowColumn(this.rowColumn);
		venue.setDisplayText(getName());
		return venue;
	}
	
	public String getName() {
		if(isTBA()) {
			return "*TBA";
		}
		else {
			return name;
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public int getConcurrentSession() {
		return concurrentSession;
	}

	public void setConcurrentSession(int concurrentSession) {
		this.concurrentSession = concurrentSession;
	}

	public String getAcademicProgramCodes() {
		return academicProgramCodes;
	}

	public void setAcademicProgramCodes(String academicProgramCodes) {
		this.academicProgramCodes = academicProgramCodes;
	}

	public int getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public String getRowColumn() {
		return rowColumn;
	}

	public void setRowColumn(String rowColumn) {
		this.rowColumn = rowColumn;
	}
}
