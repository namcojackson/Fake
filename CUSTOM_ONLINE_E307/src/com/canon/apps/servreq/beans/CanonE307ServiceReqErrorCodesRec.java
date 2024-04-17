package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqErrorCodesRec implements SQLData {

	private String ugwErrCode;

	
	public String getUgwErrCode() {
		return ugwErrCode;
	}

	public void setUgwErrCode(String ugwErrCode) {
		this.ugwErrCode = ugwErrCode;
	}
	
	public CanonE307ServiceReqErrorCodesRec(){
		
	}

	public CanonE307ServiceReqErrorCodesRec(String ugwErrCode) {
		this.ugwErrCode = ugwErrCode;
	}

	@Override
	public String toString() {
		return "CanonE307ServiceReqErrorCodesRec [ugwErrCode=" + ugwErrCode
				+ "]";
	}

	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_UGW_ERR_CODE_REC";
	}


	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		ugwErrCode = stream.readString();
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
         stream.writeString(ugwErrCode);
	}
	
	
}
