package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqProbRec    implements SQLData{

	private String type;
	private String description;
	private String problemCode;
	private String other;
	private String machineStatus;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}

	@Override
	public String toString() {
		return "SrProblem [type=" + type + ", description=" + description
				+ ", problemCode=" + problemCode + ", other=" + other + ", machineStatus="
				+ machineStatus + "]";
	}

	public CanonE307ServiceReqProbRec(){
		
	}
	
	public CanonE307ServiceReqProbRec(String type, String description,
			String problemCode, String other, String machineStatus) {
		this.type = type;
		this.description = description;
		this.problemCode = problemCode;
		this.other = other;
		this.machineStatus = machineStatus;
	}

	
	public void readSQL(SQLInput stream, String type) throws SQLException {
		
		 type         =stream.readString();
		 description  =stream.readString();
		 problemCode         =stream.readString();
		 other        =stream.readString();
		 machineStatus    =stream.readString();
	}
	
	public void writeSQL(SQLOutput stream) throws SQLException {
	
		 stream.writeString(type);
		 stream.writeString(description);
		 stream.writeString(problemCode);
		 stream.writeString(other);
		 stream.writeString(machineStatus);
		 
		 
	}
	
	public String getSQLTypeName() {
        return CanonConstants.SCHEMA_NAME+".CANON_E307_PROB_CODE_TBL";
    }	
	
}
