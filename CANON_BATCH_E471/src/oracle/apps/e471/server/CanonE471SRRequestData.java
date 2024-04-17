package oracle.apps.e471.server;

import java.sql.Timestamp;

public class CanonE471SRRequestData{
	private String company = null;
	private String requestedBy = null;
	private String address = null;
	private String address2 = null;
	private String city = null;
	private String state = null;
	private String zipCode = null;
	private String emailAddress = null;
	private String phoneNumber = null;
	private String faxNumber = null;
	private String serialNumber = null;
	private String meterRead = null;
	private String problem = null;
	private Timestamp creationDate = null;
	private String processedFlag = null;
	private String errorMessage = null;
	private String source = null;
	private String sourceEmail = null;
	private String serviceType = null;
	private String serviceTypeCd = null;
	private String specialInstructions = null;
	private String caseNumber = null;
	private String svcMachMasterPk = null;
	private String incidentId = null;
	private String contactName = null;
	private String contactPhone = null;
	private String contactEmail = null;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMeterRead() {
		return meterRead;
	}
	public void setMeterRead(String meterRead) {
		this.meterRead = meterRead;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public String getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSource() {
		if(source.equals("EMAIL") || source.equals("EMAIL_FROM_CC")){
			return "30";
		} else {
			return "31"; //CSA
		}
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceEmail() {
		return sourceEmail;
	}
	public void setSourceEmail(String sourceEmail) {
		this.sourceEmail = sourceEmail;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String case_number) {
		this.caseNumber = case_number;
	}
	public String getSvcMachMasterPk() {
		return svcMachMasterPk;
	}
	public void setSvcMachMasterPk(String svcMachMasterPk) {
		this.svcMachMasterPk = svcMachMasterPk;
	}
	public String getServiceTypeCd() {
		return serviceTypeCd;
	}
	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
	}
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
}
