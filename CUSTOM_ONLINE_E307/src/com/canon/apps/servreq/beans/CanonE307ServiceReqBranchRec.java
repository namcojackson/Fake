package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqBranchRec implements SQLData{

	public CanonE307ServiceReqBranchRec() {
		// TODO Auto-generated constructor stub
	}
	private String strBranchCode;
	private String strBranchDesc;
	
	public CanonE307ServiceReqBranchRec(String strBranchCode,
			String strBranchDesc) {
		super();
		this.strBranchCode = strBranchCode;
		this.strBranchDesc = strBranchDesc;
	}

	public String getStrBranchCode() {
		return strBranchCode;
	}

	public void setStrBranchCode(String strBranchCode) {
		this.strBranchCode = strBranchCode;
	}

	public String getStrBranchDesc() {
		return strBranchDesc;
	}

	public void setStrBranchDesc(String strBranchDesc) {
		this.strBranchDesc = strBranchDesc;
	}

	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_BRANCH_CODE_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strBranchCode = stream.readString();
		strBranchDesc = stream.readString();
		
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strBranchCode);
		stream.writeString(strBranchDesc);
	}
	

}
