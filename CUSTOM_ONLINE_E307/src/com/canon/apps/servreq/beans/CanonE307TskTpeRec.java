package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307TskTpeRec implements SQLData{
	
	private String tskTpCd;
	private String tskTpNm;
	private String tskPrtyCd;
	
	public CanonE307TskTpeRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307TskTpeRec(String tskTpCd, String tskTpNm, String tskPrtyCd) {
		super();
		this.tskTpCd = tskTpCd;
		this.tskTpNm = tskTpNm;
		this.tskPrtyCd = tskPrtyCd;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String arg1) throws SQLException {
		tskTpCd = stream.readString();
		tskTpNm = stream.readString();
		tskPrtyCd = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(tskTpCd);
		stream.writeString(tskTpNm);
		stream.writeString(tskPrtyCd);
	}

	public String getTskTpCd() {
		return tskTpCd;
	}

	public void setTskTpCd(String tskTpCd) {
		this.tskTpCd = tskTpCd;
	}

	public String getTskTpNm() {
		return tskTpNm;
	}

	public void setTskTpNm(String tskTpNm) {
		this.tskTpNm = tskTpNm;
	}

	public String getTskPrtyCd() {
		return tskPrtyCd;
	}

	public void setTskPrtyCd(String tskPrtyCd) {
		this.tskPrtyCd = tskPrtyCd;
	}

}
