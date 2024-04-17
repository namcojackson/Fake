package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307BillEstmtTskDtlsRec implements SQLData{

	private String strTaskNum;
	private String strActualStrtDt;
	private String strActualEndDt;
	private String strTechNm;
	
	public CanonE307BillEstmtTskDtlsRec() {
		// TODO Auto-generated constructor stub
	}
	

	public CanonE307BillEstmtTskDtlsRec(String strTaskNum,
			String strActualStrtDt, String strActualEndDt, String strTechNm) {
		super();
		this.strTaskNum = strTaskNum;
		this.strActualStrtDt = strActualStrtDt;
		this.strActualEndDt = strActualEndDt;
		this.strTechNm = strTechNm;
	}


	public String getStrTaskNum() {
		return strTaskNum;
	}


	public void setStrTaskNum(String strTaskNum) {
		this.strTaskNum = strTaskNum;
	}


	public String getStrActualStrtDt() {
		return strActualStrtDt;
	}


	public void setStrActualStrtDt(String strActualStrtDt) {
		this.strActualStrtDt = strActualStrtDt;
	}


	public String getStrActualEndDt() {
		return strActualEndDt;
	}


	public void setStrActualEndDt(String strActualEndDt) {
		this.strActualEndDt = strActualEndDt;
	}


	public String getStrTechNm() {
		return strTechNm;
	}


	public void setStrTechNm(String strTechNm) {
		this.strTechNm = strTechNm;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_EST_TASK_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strTaskNum = stream.readString();
		strActualStrtDt = stream.readString();
		strActualEndDt = stream.readString();
		strTechNm = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strTaskNum);
		stream.writeString(strActualStrtDt);
		stream.writeString(strActualEndDt);
		stream.writeString(strTechNm);
	}
}
