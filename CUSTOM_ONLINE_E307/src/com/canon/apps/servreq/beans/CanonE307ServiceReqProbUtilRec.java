package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqProbUtilRec implements SQLData{
	// User for  Dropdowns in Problem Section

	
	
	private String probValue;

	public String getProbValue() {
		return probValue;
	}

	public void setProbValue(String probValue) {
		this.probValue = probValue;
	}

	public CanonE307ServiceReqProbUtilRec(){
		
	}
	
	public CanonE307ServiceReqProbUtilRec(String probValue) {
		this.probValue = probValue;
	}
	
	@Override
	public String toString() {
		return "CanonE307ServiceReqProbUtilRec [probValue=" + probValue + "]";
	}

	public String getSQLTypeName() throws SQLException {
		
		return CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		probValue = stream.readString();
		
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(probValue);		
	}
	
	
}
