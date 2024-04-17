package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqCallAvdRec implements SQLData{

	private String svcCallAvoidCd;
	private String svcCallAvoidNm;
	
	public String getSvcCallAvoidCd() {
		return svcCallAvoidCd;
	}
	public void setSvcCallAvoidCd(String svcCallAvoidCd) {
		this.svcCallAvoidCd = svcCallAvoidCd;
	}
	public String getSvcCallAvoidNm() {
		return svcCallAvoidNm;
	}
	public void setSvcCallAvoidNm(String svcCallAvoidNm) {
		this.svcCallAvoidNm = svcCallAvoidNm;
	}
	
	public CanonE307ServiceReqCallAvdRec() {
	}
	public CanonE307ServiceReqCallAvdRec(String svcCallAvoidCd,
			String svcCallAvoidNm) {
		this.svcCallAvoidCd = svcCallAvoidCd;
		this.svcCallAvoidNm = svcCallAvoidNm;
	}
	@Override
	public String toString() {
		return "CanonE307ServiceReqCallAvdRec [svcCallAvoidCd="
				+ svcCallAvoidCd + ", svcCallAvoidNm=" + svcCallAvoidNm + "]";
	}
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CALL_AVOID_REC";
	}
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
          svcCallAvoidCd=stream.readString();
          svcCallAvoidNm=stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		  stream.writeString(svcCallAvoidCd);
		  stream.writeString(svcCallAvoidNm);
	}

   
	
	
	
	
}
