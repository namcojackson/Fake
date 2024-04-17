package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307TaskCancelRsnRec implements SQLData{

	private String rsnTpCd;
	private String rsnTpVal;
	private String rsnTpDesc;
	
	
	public CanonE307TaskCancelRsnRec() {
	}
	
	public CanonE307TaskCancelRsnRec(String rsnTpCd, String rsnTpVal,
			String rsnTpDesc) {
		super();
		this.rsnTpCd = rsnTpCd;
		this.rsnTpVal = rsnTpVal;
		this.rsnTpDesc = rsnTpDesc;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC";
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		rsnTpCd = stream.readString();
		rsnTpVal = stream.readString();
		rsnTpDesc = stream.readString();
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(rsnTpCd);
		stream.writeString(rsnTpVal);
		stream.writeString(rsnTpDesc);
	}

	public String getRsnTpCd() {
		return rsnTpCd;
	}

	public void setRsnTpCd(String rsnTpCd) {
		this.rsnTpCd = rsnTpCd;
	}

	public String getRsnTpVal() {
		return rsnTpVal;
	}

	public void setRsnTpVal(String rsnTpVal) {
		this.rsnTpVal = rsnTpVal;
	}

	public String getRsnTpDesc() {
		return rsnTpDesc;
	}

	public void setRsnTpDesc(String rsnTpDesc) {
		this.rsnTpDesc = rsnTpDesc;
	}

	@Override
	public String toString() {
		return "CanonE307TaskCancelRsnRec [rsnTpCd=" + rsnTpCd + ", rsnTpVal="
				+ rsnTpVal + ", rsnTpDesc=" + rsnTpDesc + "]";
	}
	
}
