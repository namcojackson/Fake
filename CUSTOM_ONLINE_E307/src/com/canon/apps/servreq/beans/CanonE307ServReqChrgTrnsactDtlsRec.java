package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServReqChrgTrnsactDtlsRec implements SQLData{
	private String strTrxTpe;
	private String strChrgSrc;
	private String strCretionDt;
	private String strTrxPrcLst;
	private String strSourceNum;
	private int contractPrice;
	
	public CanonE307ServReqChrgTrnsactDtlsRec() {
		// TODO Auto-generated constructor stub
	}
	public CanonE307ServReqChrgTrnsactDtlsRec(String strTrxTpe,
			String strChrgSrc, String strCretionDt, String strTrxPrcLst,
			String strSourceNum, int contractPrice) {
		super();
		this.strTrxTpe = strTrxTpe;
		this.strChrgSrc = strChrgSrc;
		this.strCretionDt = strCretionDt;
		this.strTrxPrcLst = strTrxPrcLst;
		this.strSourceNum = strSourceNum;
		this.contractPrice = contractPrice;
	}
	public String getStrTrxTpe() {
		return strTrxTpe;
	}
	public void setStrTrxTpe(String strTrxTpe) {
		this.strTrxTpe = strTrxTpe;
	}
	public String getStrChrgSrc() {
		return strChrgSrc;
	}
	public void setStrChrgSrc(String strChrgSrc) {
		this.strChrgSrc = strChrgSrc;
	}
	public String getStrCretionDt() {
		return strCretionDt;
	}
	public void setStrCretionDt(String strCretionDt) {
		this.strCretionDt = strCretionDt;
	}
	public String getStrTrxPrcLst() {
		return strTrxPrcLst;
	}
	public void setStrTrxPrcLst(String strTrxPrcLst) {
		this.strTrxPrcLst = strTrxPrcLst;
	}
	public String getStrSourceNum() {
		return strSourceNum;
	}
	public void setStrSourceNum(String strSourceNum) {
		this.strSourceNum = strSourceNum;
	}
	public int getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(int contractPrice) {
		this.contractPrice = contractPrice;
	}
	@Override
	public String toString() {
		return "CanonE307ServReqChrgTrnsactDtlsRec [strTrxTpe=" + strTrxTpe
				+ ", strChrgSrc=" + strChrgSrc + ", strCretionDt="
				+ strCretionDt + ", strTrxPrcLst=" + strTrxPrcLst
				+ ", strSourceNum=" + strSourceNum + ", contractPrice="
				+ contractPrice + "]";
	}
	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TRX_DTL_REC";
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strTrxTpe = stream.readString();
		strChrgSrc = stream.readString();
		strCretionDt = stream.readString();
		strTrxPrcLst = stream.readString();
		strSourceNum = stream.readString();
		contractPrice = stream.readInt();
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strTrxTpe);
		stream.writeString(strChrgSrc);
		stream.writeString(strCretionDt);
		stream.writeString(strTrxPrcLst);
		stream.writeString(strSourceNum);
		stream.writeInt(contractPrice);
	}

}
