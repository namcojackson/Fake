package com.canon.apps.servreq.beans;

import java.util.Date;
import java.sql.SQLData;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;

import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServReqDebriefLovRec implements SQLData{

	private String strVal;
	private String strValDesc;
	
	public CanonE307ServReqDebriefLovRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307ServReqDebriefLovRec(String strVal, String strValDesc) {
		super();
		this.strVal = strVal;
		this.strValDesc=strValDesc;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strVal = stream.readString();
		strValDesc= stream.readString();
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strVal);
		stream.writeString(strValDesc);
	}

	public String getStrVal() {
		return strVal;
	}

	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	public String getStrValDesc() {
		return strValDesc;
	}

	public void setStrValDesc(String strValDesc) {
		this.strValDesc = strValDesc;
	}

	@Override
	public String toString() {
		return "CanonE307ServReqDebriefLovRec [strVal=" + strVal
				+ ", strValDesc=" + strValDesc + "]";
	}


}
