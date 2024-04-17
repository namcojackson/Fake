package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRTaskHistNewRec implements SQLData{

	private String strFSR;
	private String strTskCrtnDt;
	private String strTskNum;
	private String strTskTpe;
	private String strTskSts;
	private String strSrProbCd;
	private String strSrPblmNt;
	private String strMblNt;
	private String strPrmryMtr;
	private String strRespTm;
	private String strLbrStrt;
	private String strLbrEnd;
	private String strTskPblmCd;
	private String strCorctnCd;
	private String strLoctnCd;
	private String strRsnCd;
	private String strMachnSts;
	private String strTechnician;
	private String strPostalCd;
	private String strCntryCd;
	
	public CanonE307SRTaskHistNewRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307SRTaskHistNewRec(String strFSR, String strTskCrtnDt,
			String strTskNum, String strTskTpe, String strTskSts,
			String strSrProbCd, String strSrPblmNt, String strMblNt,
			String strPrmryMtr, String strRespTm, String strLbrStrt,
			String strLbrEnd, String strTskPblmCd, String strCorctnCd,
			String strLoctnCd, String strRsnCd, String strMachnSts,
			String strTechnician, String strPostalCd, String strCntryCd) {
		super();
		this.strFSR = strFSR;
		this.strTskCrtnDt = strTskCrtnDt;
		this.strTskNum = strTskNum;
		this.strTskTpe = strTskTpe;
		this.strTskSts = strTskSts;
		this.strSrProbCd = strSrProbCd;
		this.strSrPblmNt = strSrPblmNt;
		this.strMblNt = strMblNt;
		this.strPrmryMtr = strPrmryMtr;
		this.strRespTm = strRespTm;
		this.strLbrStrt = strLbrStrt;
		this.strLbrEnd = strLbrEnd;
		this.strTskPblmCd = strTskPblmCd;
		this.strCorctnCd = strCorctnCd;
		this.strLoctnCd = strLoctnCd;
		this.strRsnCd = strRsnCd;
		this.strMachnSts = strMachnSts;
		this.strTechnician = strTechnician;
		this.strPostalCd = strPostalCd;
		this.strCntryCd = strCntryCd;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_HIST_NEW_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFSR = stream.readString();
		strTskCrtnDt = stream.readString();
		strTskNum = stream.readString();
		strTskTpe = stream.readString();
		strTskSts = stream.readString();
		strSrProbCd = stream.readString();
		strSrPblmNt = stream.readString();
		strMblNt = stream.readString();
		strPrmryMtr = stream.readString();
		strRespTm = stream.readString();
		strLbrStrt = stream.readString();
		strLbrEnd = stream.readString();
		strTskPblmCd = stream.readString();
		strCorctnCd = stream.readString();
		strLoctnCd = stream.readString();
		strRsnCd = stream.readString();
		strMachnSts = stream.readString();
		strTechnician = stream.readString();
		strPostalCd = stream.readString();
		strCntryCd = stream.readString();
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFSR);
		stream.writeString(strTskCrtnDt);
		stream.writeString(strTskNum);
		stream.writeString(strTskTpe);
		stream.writeString(strTskSts);
		stream.writeString(strSrProbCd);
		stream.writeString(strSrPblmNt);
		stream.writeString(strMblNt);
		stream.writeString(strPrmryMtr);
		stream.writeString(strRespTm);
		stream.writeString(strLbrStrt);
		stream.writeString(strLbrEnd);
		stream.writeString(strTskPblmCd);
		stream.writeString(strCorctnCd);
		stream.writeString(strLoctnCd);
		stream.writeString(strRsnCd);
		stream.writeString(strMachnSts);
		stream.writeString(strTechnician);
		stream.writeString(strPostalCd);
		stream.writeString(strCntryCd);
		
	}

	public String getStrFSR() {
		return strFSR;
	}

	public void setStrFSR(String strFSR) {
		this.strFSR = strFSR;
	}

	public String getStrTskCrtnDt() {
		return strTskCrtnDt;
	}

	public void setStrTskCrtnDt(String strTskCrtnDt) {
		this.strTskCrtnDt = strTskCrtnDt;
	}

	public String getStrTskNum() {
		return strTskNum;
	}

	public void setStrTskNum(String strTskNum) {
		this.strTskNum = strTskNum;
	}

	public String getStrTskTpe() {
		return strTskTpe;
	}

	public void setStrTskTpe(String strTskTpe) {
		this.strTskTpe = strTskTpe;
	}

	public String getStrTskSts() {
		return strTskSts;
	}

	public void setStrTskSts(String strTskSts) {
		this.strTskSts = strTskSts;
	}

	public String getStrSrProbCd() {
		return strSrProbCd;
	}

	public void setStrSrProbCd(String strSrProbCd) {
		this.strSrProbCd = strSrProbCd;
	}

	public String getStrSrPblmNt() {
		return strSrPblmNt;
	}

	public void setStrSrPblmNt(String strSrPblmNt) {
		this.strSrPblmNt = strSrPblmNt;
	}

	public String getStrMblNt() {
		return strMblNt;
	}

	public void setStrMblNt(String strMblNt) {
		this.strMblNt = strMblNt;
	}

	public String getStrPrmryMtr() {
		return strPrmryMtr;
	}

	public void setStrPrmryMtr(String strPrmryMtr) {
		this.strPrmryMtr = strPrmryMtr;
	}

	public String getStrRespTm() {
		return strRespTm;
	}

	public void setStrRespTm(String strRespTm) {
		this.strRespTm = strRespTm;
	}

	public String getStrLbrStrt() {
		return strLbrStrt;
	}

	public void setStrLbrStrt(String strLbrStrt) {
		this.strLbrStrt = strLbrStrt;
	}

	public String getStrLbrEnd() {
		return strLbrEnd;
	}

	public void setStrLbrEnd(String strLbrEnd) {
		this.strLbrEnd = strLbrEnd;
	}

	public String getStrTskPblmCd() {
		return strTskPblmCd;
	}

	public void setStrTskPblmCd(String strTskPblmCd) {
		this.strTskPblmCd = strTskPblmCd;
	}

	public String getStrCorctnCd() {
		return strCorctnCd;
	}

	public void setStrCorctnCd(String strCorctnCd) {
		this.strCorctnCd = strCorctnCd;
	}

	public String getStrLoctnCd() {
		return strLoctnCd;
	}

	public void setStrLoctnCd(String strLoctnCd) {
		this.strLoctnCd = strLoctnCd;
	}

	public String getStrRsnCd() {
		return strRsnCd;
	}

	public void setStrRsnCd(String strRsnCd) {
		this.strRsnCd = strRsnCd;
	}

	public String getStrMachnSts() {
		return strMachnSts;
	}

	public void setStrMachnSts(String strMachnSts) {
		this.strMachnSts = strMachnSts;
	}

	public String getStrTechnician() {
		return strTechnician;
	}

	public void setStrTechnician(String strTechnician) {
		this.strTechnician = strTechnician;
	}

	public String getStrPostalCd() {
		return strPostalCd;
	}

	public void setStrPostalCd(String strPostalCd) {
		this.strPostalCd = strPostalCd;
	}

	public String getStrCntryCd() {
		return strCntryCd;
	}

	public void setStrCntryCd(String strCntryCd) {
		this.strCntryCd = strCntryCd;
	}

	@Override
	public String toString() {
		return "CanonE307SRTaskHistNewRec [strFSR=" + strFSR
				+ ", strTskCrtnDt=" + strTskCrtnDt + ", strTskNum=" + strTskNum
				+ ", strTskTpe=" + strTskTpe + ", strTskSts=" + strTskSts
				+ ", strSrProbCd=" + strSrProbCd + ", strSrPblmNt="
				+ strSrPblmNt + ", strMblNt=" + strMblNt + ", strPrmryMtr="
				+ strPrmryMtr + ", strRespTm=" + strRespTm + ", strLbrStrt="
				+ strLbrStrt + ", strLbrEnd=" + strLbrEnd + ", strTskPblmCd="
				+ strTskPblmCd + ", strCorctnCd=" + strCorctnCd
				+ ", strLoctnCd=" + strLoctnCd + ", strRsnCd=" + strRsnCd
				+ ", strMachnSts=" + strMachnSts + ", strTechnician="
				+ strTechnician + ", strPostalCd=" + strPostalCd
				+ ", strCntryCd=" + strCntryCd + "]";
	}

	
}
