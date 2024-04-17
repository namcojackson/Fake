package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefItemLov implements SQLData{

	private String strItmNm;
	private String strItmDesc;
	private String strUom;
	private String strStrtDt;
	private String strStrtTm;
	private String strEndDt;
	private String strEndTm;
	private String strItmCnt;
	
	public CanonE307DebriefItemLov() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307DebriefItemLov(String strItmNm, String strItmDesc,
			String strUom, String strStrtDt, String strStrtTm, String strEndDt,
			String strEndTm, String strItmCnt) {
		super();
		this.strItmNm = strItmNm;
		this.strItmDesc = strItmDesc;
		this.strUom = strUom;
		this.strStrtDt = strStrtDt;
		this.strStrtTm = strStrtTm;
		this.strEndDt = strEndDt;
		this.strEndTm = strEndTm;
		this.strItmCnt = strItmCnt;
	}



	public String getStrItmNm() {
		return strItmNm;
	}

	public void setStrItmNm(String strItmNm) {
		this.strItmNm = strItmNm;
	}

	public String getStrItmDesc() {
		return strItmDesc;
	}

	public void setStrItmDesc(String strItmDesc) {
		this.strItmDesc = strItmDesc;
	}

	public String getStrUom() {
		return strUom;
	}

	public void setStrUom(String strUom) {
		this.strUom = strUom;
	}

	public String getStrStrtDt() {
		return strStrtDt;
	}

	public void setStrStrtDt(String strStrtDt) {
		this.strStrtDt = strStrtDt;
	}

	public String getStrStrtTm() {
		return strStrtTm;
	}

	public void setStrStrtTm(String strStrtTm) {
		this.strStrtTm = strStrtTm;
	}

	public String getStrEndDt() {
		return strEndDt;
	}

	public void setStrEndDt(String strEndDt) {
		this.strEndDt = strEndDt;
	}

	public String getStrEndTm() {
		return strEndTm;
	}

	public void setStrEndTm(String strEndTm) {
		this.strEndTm = strEndTm;
	}
	
	public String getStrItmCnt() {
		return strItmCnt;
	}


	public void setStrItmCnt(String strItmCnt) {
		this.strItmCnt = strItmCnt;
	}


	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_ITEM_LOV_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String type) throws SQLException {
		strItmNm= stream.readString();
		strItmDesc = stream.readString();
		strUom = stream.readString();
		strStrtDt = stream.readString();
		strStrtTm = stream.readString();
		strEndDt = stream.readString();
		strEndTm = stream.readString();
		strItmCnt = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strItmNm);
		stream.writeString(strItmDesc);
		stream.writeString(strUom);
		stream.writeString(strStrtDt);
		stream.writeString(strStrtTm);
		stream.writeString(strEndDt);
		stream.writeString(strEndTm);
		stream.writeString(strItmCnt);
	}
	
}
