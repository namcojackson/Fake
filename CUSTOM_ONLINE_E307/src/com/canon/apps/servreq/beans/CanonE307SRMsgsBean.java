package com.canon.apps.servreq.beans;

public class CanonE307SRMsgsBean {

	
	public String region;
	public String branch;
	public String branchDesc;
	public String postal;
	public String partyId;
	public String partyName;
	public String partyNumber;
	public String partySiteNumber;
	public String accountNumber;
	
	public String serialNumber;
	public String extReference;
	public String address;
	public String model;
	
	public String startDate;
	public String endDate;
	public String startTime;
	public String endTime;
	public String servMsg;
	
	public String retStatus;
	public String retMessage;
	
	public int totalRec;
	
	public String modelDesc;
	public String isFullHour;
	
	public String servMsgId;
	
	public String createdBy;
	public String lastUpdatedBy;
	
	public String svcTeam;
	
	
	public CanonE307SRMsgsBean() {
		// TODO Auto-generated constructor stub
	}

	
	public CanonE307SRMsgsBean(String region, String branch, String branchDesc,
			String postal, String partyId, String partyName,
			String partyNumber, String partySiteNumber, String accountNumber,
			String serialNumber, String extReference, String address,
			String model, String startDate, String endDate, String startTime,
			String endTime, String servMsg, String retStatus,
			String retMessage, int totalRec, String modelDesc,
			String isFullHour, String servMsgId, String createdBy,
			String lastUpdatedBy, String svcTeam) {
		super();
		this.region = region;
		this.branch = branch;
		this.branchDesc = branchDesc;
		this.postal = postal;
		this.partyId = partyId;
		this.partyName = partyName;
		this.partyNumber = partyNumber;
		this.partySiteNumber = partySiteNumber;
		this.accountNumber = accountNumber;
		this.serialNumber = serialNumber;
		this.extReference = extReference;
		this.address = address;
		this.model = model;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.servMsg = servMsg;
		this.retStatus = retStatus;
		this.retMessage = retMessage;
		this.totalRec = totalRec;
		this.modelDesc = modelDesc;
		this.isFullHour = isFullHour;
		this.servMsgId = servMsgId;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
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

	public String getBranchDesc() {
		return branchDesc;
	}

	public void setBranchDesc(String branchDesc) {
		this.branchDesc = branchDesc;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
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

	public String getExtReference() {
		return extReference;
	}

	public void setExtReference(String extReference) {
		this.extReference = extReference;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getServMsg() {
		return servMsg;
	}

	public void setServMsg(String servMsg) {
		this.servMsg = servMsg;
	}

	public String getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}

	public String getIsFullHour() {
		return isFullHour;
	}

	public void setIsFullHour(String isFullHour) {
		this.isFullHour = isFullHour;
	}

	public String getServMsgId() {
		return servMsgId;
	}

	public void setServMsgId(String servMsgId) {
		this.servMsgId = servMsgId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public String getSvcTeam() {
		return svcTeam;
	}


	public void setSvcTeam(String svcTeam) {
		this.svcTeam = svcTeam;
	}

	

	@Override
	public String toString() {
		return "CanonE307SRMsgsBean [region=" + region + ", branch=" + branch
				+ ", branchDesc=" + branchDesc + ", postal=" + postal
				+ ", partyId=" + partyId + ", partyName=" + partyName
				+ ", partyNumber=" + partyNumber + ", partySiteNumber="
				+ partySiteNumber + ", accountNumber=" + accountNumber
				+ ", serialNumber=" + serialNumber + ", extReference="
				+ extReference + ", address=" + address + ", model=" + model
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", servMsg=" + servMsg + ", retStatus=" + retStatus
				+ ", retMessage=" + retMessage + ", totalRec=" + totalRec
				+ ", modelDesc=" + modelDesc + ", isFullHour=" + isFullHour
				+ ", servMsgId=" + servMsgId + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", svcTeam=" + svcTeam
				+ "]";
	}
	
}
