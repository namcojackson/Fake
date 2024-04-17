package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307MtrRdTpeRec implements SQLData{
	
	String strMtrRdTpCd;
	String strMtrRdTpNm;
	String strMtrRdDesc;

	public CanonE307MtrRdTpeRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307MtrRdTpeRec(String strMtrRdTpCd, String strMtrRdTpNm,
			String strMtrRdDesc) {
		super();
		this.strMtrRdTpCd = strMtrRdTpCd;
		this.strMtrRdTpNm = strMtrRdTpNm;
		this.strMtrRdDesc = strMtrRdDesc;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strMtrRdTpCd = stream.readString();
		strMtrRdTpNm = stream.readString();
		strMtrRdDesc = stream.readString();
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strMtrRdTpCd);
		stream.writeString(strMtrRdTpNm);
		stream.writeString(strMtrRdDesc);
	}

	public String getStrMtrRdTpCd() {
		return strMtrRdTpCd;
	}

	public void setStrMtrRdTpCd(String strMtrRdTpCd) {
		this.strMtrRdTpCd = strMtrRdTpCd;
	}

	public String getStrMtrRdTpNm() {
		return strMtrRdTpNm;
	}

	public void setStrMtrRdTpNm(String strMtrRdTpNm) {
		this.strMtrRdTpNm = strMtrRdTpNm;
	}

	public String getStrMtrRdDesc() {
		return strMtrRdDesc;
	}

	public void setStrMtrRdDesc(String strMtrRdDesc) {
		this.strMtrRdDesc = strMtrRdDesc;
	}

	@Override
	public String toString() {
		return "CanonE307MtrRdTpeRec [strMtrRdTpCd=" + strMtrRdTpCd
				+ ", strMtrRdTpNm=" + strMtrRdTpNm + ", strMtrRdDesc="
				+ strMtrRdDesc + "]";
	}
	

}
