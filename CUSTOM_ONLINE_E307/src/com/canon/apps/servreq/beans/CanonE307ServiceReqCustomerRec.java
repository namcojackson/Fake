package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqCustomerRec implements SQLData {

	private String custName;
	private String acctNum;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	
	public CanonE307ServiceReqCustomerRec(){
		
	}
	
	public CanonE307ServiceReqCustomerRec(String custName, String acctNum) {
		this.custName = custName;
		this.acctNum = acctNum;
	}
	
	@Override
	public String toString() {
		return "CanonE307ServiceReqCustomerRec [custName=" + custName + ", acctNum="
				+ acctNum + "]";
	}
	
	public String getSQLTypeName() throws SQLException {
		
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CUST_NAME_LOV_REC";
	}
	
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		
		custName = stream.readString();
		acctNum = stream.readString();
		
	}
	
	public void writeSQL(SQLOutput stream) throws SQLException {
	    stream.writeString(custName);
	    stream.writeString(acctNum);
		
	}
	
	
	
}
