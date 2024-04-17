package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRLovRec implements SQLData{

	private String strVal;
	private String strValDesc;
	
	public CanonE307SRLovRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307SRLovRec(String strVal, String strValDesc) {
		super();
		this.strVal = strVal;
		this.strValDesc = strValDesc;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return CanonConstants.SCHEMA_NAME+".CANON_E307_LOV_VAL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strVal = stream.readString();
		strValDesc=stream.readString();
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
	
	

}
