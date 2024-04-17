package oracle.apps.emsde618.sync;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;

public class CanonEMSDE618ServImpactRec implements java.sql.SQLData, java.io.Serializable  {
  
	private String incidentNumber;
	private String serialNumber;
	private String incidentType;
	private String status;
	private String severity;
	private Timestamp incidentCreateDate;
	private String createdBy;
	private String assigned;
	private String closedBy;
	private Timestamp incidentClosedDate;
	private String problemSummary;
	private String customerContact;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String taskNumber;
	private String taskPriority;
	private Timestamp taskCreateDate;
	private Timestamp taskEndDate;
	private String taskStatus;
	private String taskProblemDescription;	
	private String srConfirmationNumber;	
	private Timestamp incidentResolvedDate;
	
	private String resolution;
	private String taskType;
	private String source;

	private Connection conn;
    
     public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public CanonEMSDE618ServImpactRec(){
     }
     
     public String getSQLTypeName() throws SQLException {
         return "CANON_E618_SERV_IMPACT_REC";
     }
     
     public CanonEMSDE618ServImpactRec(
    		 String incidentNumber,
    		 String serialNumber,
    		 String incidentType,
    		 String status,
    		 String severity,
    		 Timestamp incidentCreateDate,
    		 String createdBy,
    		 String assigned,
    		 String closedBy,
    		 Timestamp incidentClosedDate,
    		 String problemSummary,
    		 String customerContact,
    		 String address,
    		 String city,
    		 String state,
    		 String zip,
    		 String taskNumber,
    		 String taskPriority,
    		 Timestamp taskCreateDate,
    		 Timestamp taskEndDate,
    		 String taskStatus,
    		 String taskProblemDescription,
    		 String srConfirmationNumber,
    		 Timestamp  incidentResolvedDate,
    		 String resolution,
    		 String taskType,
    		 String source
             ){
    	 this.incidentNumber = incidentNumber;
    	 this.serialNumber = serialNumber;
    	 this.incidentType = incidentType;
    	 this.status = status;
    	 this.severity = severity;
    	 this.incidentCreateDate = incidentCreateDate;
    	 this.createdBy = createdBy;
    	 this.assigned = assigned;
    	 this.closedBy = closedBy;
    	 this.incidentClosedDate = incidentClosedDate;
    	 this.problemSummary = problemSummary;
    	 this.customerContact = customerContact;
    	 this.address = address;
    	 this.city = city;
    	 this.state = state;
    	 this.zip = zip;
    	 this.taskNumber = taskNumber;
    	 this.taskPriority = taskPriority;
    	 this.taskCreateDate = taskCreateDate;
    	 this.taskEndDate = taskEndDate;
    	 this.taskStatus = taskStatus;
    	 this.taskProblemDescription = taskProblemDescription;
    	 this.srConfirmationNumber = srConfirmationNumber;
    	 this.incidentResolvedDate = incidentResolvedDate;
    	 this.resolution = resolution;
    	 this.taskType = taskType;
    	 this.source = source;
     }

     public void readSQL(SQLInput stream, String typeName) throws SQLException {
    	 incidentNumber= stream.readString();
    	 serialNumber= stream.readString();
    	 incidentType= stream.readString();
    	 status= stream.readString();
    	 severity= stream.readString();
    	 incidentCreateDate= stream.readTimestamp();
    	 createdBy= stream.readString();
    	 assigned= stream.readString();
    	 closedBy= stream.readString();
    	 incidentClosedDate= stream.readTimestamp();
    	 problemSummary= stream.readString();
    	 customerContact= stream.readString();
    	 address= stream.readString();
    	 city= stream.readString();
    	 state= stream.readString();
    	 zip= stream.readString();
    	 taskNumber= stream.readString();
    	 taskPriority= stream.readString();
    	 taskCreateDate= stream.readTimestamp();
    	 taskEndDate= stream.readTimestamp();
    	 taskStatus= stream.readString();
    	 taskProblemDescription= stream.readString();
    	 srConfirmationNumber= stream.readString();
    	 incidentResolvedDate= stream.readTimestamp();
    	 resolution= stream.readString();
    	 taskType= stream.readString();
    	 source= stream.readString();

     }

     public void writeSQL(SQLOutput stream) throws SQLException {
    	 stream.writeString(incidentNumber);
    	 stream.writeString(serialNumber);
    	 stream.writeString(incidentType);
    	 stream.writeString(status);
    	 stream.writeString(severity);
    	 stream.writeTimestamp(incidentCreateDate);
    	 stream.writeString(createdBy);
    	 stream.writeString(assigned);
    	 stream.writeString(closedBy);
    	 stream.writeTimestamp(incidentClosedDate);
    	 stream.writeString(problemSummary);
    	 stream.writeString(customerContact);
    	 stream.writeString(address);
    	 stream.writeString(city);
    	 stream.writeString(state);
    	 stream.writeString(zip);
    	 stream.writeString(taskNumber);
    	 stream.writeString(taskPriority);
    	 stream.writeTimestamp(taskCreateDate);
    	 stream.writeTimestamp(taskEndDate);
    	 stream.writeString(taskStatus);
    	 stream.writeString(taskProblemDescription);
    	 stream.writeString(srConfirmationNumber);
    	 stream.writeTimestamp(incidentResolvedDate);
    	 stream.writeString(resolution);
    	 stream.writeString(taskType);
    	 stream.writeString(source);
     }
	
     
     
     
     public String getIncidentNumber() {
		return incidentNumber;
	}
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getIncidentType() {
		return incidentType;
	}
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Timestamp getIncidentCreateDate() {
		return incidentCreateDate;
	}
	public void setIncidentCreateDate(Timestamp incidentCreateDate) {
		this.incidentCreateDate = incidentCreateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public Timestamp getIncidentClosedDate() {
		return incidentClosedDate;
	}
	public void setIncidentClosedDate(Timestamp incidentClosedDate) {
		this.incidentClosedDate = incidentClosedDate;
	}
	public String getProblemSummary() {
		return problemSummary;
	}
	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	public Timestamp getTaskCreateDate() {
		return taskCreateDate;
	}
	public void setTaskCreateDate(Timestamp taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}
	public Timestamp getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(Timestamp taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskProblemDescription() {
		return taskProblemDescription;
	}
	public void setTaskProblemDescription(String taskProblemDescription) {
		this.taskProblemDescription = taskProblemDescription;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getSrConfirmationNumber() {
		return srConfirmationNumber;
	}

	public void setSrConfirmationNumber(String srConfirmationNumber) {
		this.srConfirmationNumber = srConfirmationNumber;
	}

    public Timestamp getIncidentResolvedDate() {
		return incidentResolvedDate;
	}

	public void setIncidentResolvedDate(Timestamp incidentResolvedDate) {
		this.incidentResolvedDate = incidentResolvedDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

     
     
 }