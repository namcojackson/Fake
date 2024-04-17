package com.canon.apps.servreq.beans;

public class CanonE307ServMsgsBean {

	private String region;
	private String branch;
	private String postal;
	private String partyName;
	private String partyNumber;
	private String partySiteNumber;
	private String accountNumber;
	private String serialNumber;
	private String model;
	
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String isFullHour;
	private String servMsg;
	private String userId;
	
	private String fieldName;
	private String fieldValue;
	
	private int lineNumber;
	
	private int servMsgId;
	
	private String svcTeam;
	
	public CanonE307ServMsgsBean() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307ServMsgsBean(String region, String branch, String postal,
			String partyName, String partyNumber, String partySiteNumber,
			String accountNumber, String serialNumber, String model,
			String startDate, String endDate, String startTime, String endTime,
			String isFullHour, String servMsg, String userId, String fieldName,
			String fieldValue, int lineNumber, int servMsgId, String svcTeam) {
		super();
		this.region = region;
		this.branch = branch;
		this.postal = postal;
		this.partyName = partyName;
		this.partyNumber = partyNumber;
		this.partySiteNumber = partySiteNumber;
		this.accountNumber = accountNumber;
		this.serialNumber = serialNumber;
		this.model = model;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isFullHour = isFullHour;
		this.servMsg = servMsg;
		this.userId = userId;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.lineNumber = lineNumber;
		this.servMsgId = servMsgId;
		this.svcTeam = svcTeam;
	}



	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getPartySiteNumber() {
		return partySiteNumber;
	}

	public void setPartySiteNumber(String partySiteNumber) {
		this.partySiteNumber = partySiteNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsFullHour() {
		return isFullHour;
	}

	public void setIsFullHour(String isFullHour) {
		this.isFullHour = isFullHour;
	}

	public String getServMsg() {
		return servMsg;
	}

	public void setServMsg(String servMsg) {
		this.servMsg = servMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getServMsgId() {
		return servMsgId;
	}

	public void setServMsgId(int servMsgId) {
		this.servMsgId = servMsgId;
	}
	
	public String getSvcTeam() {
		return svcTeam;
	}

	public void setSvcTeam(String svcTeam) {
		this.svcTeam = svcTeam;
	}


	@Override
	public String toString() {
		return "CanonE307ServMsgsBean [region=" + region + ", branch=" + branch
				+ ", postal=" + postal + ", partyName=" + partyName
				+ ", partyNumber=" + partyNumber + ", partySiteNumber="
				+ partySiteNumber + ", accountNumber=" + accountNumber
				+ ", serialNumber=" + serialNumber + ", model=" + model
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", isFullHour=" + isFullHour + ", servMsg=" + servMsg
				+ ", userId=" + userId + ", fieldName=" + fieldName
				+ ", fieldValue=" + fieldValue + ", lineNumber=" + lineNumber
				+ ", servMsgId=" + servMsgId + ", svcTeam=" + svcTeam + "]";
	}



}
