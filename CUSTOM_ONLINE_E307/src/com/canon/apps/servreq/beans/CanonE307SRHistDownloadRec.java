package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.List;

import canon.apps.common.CanonConstants;

public class CanonE307SRHistDownloadRec{
	private String strFSR;
	private String strTaskNumber;
	private String strTskCrtnDt;
	private String strSerNum;
	private String strTskTpe;
	private String strActualEndDt;
	private String strAsgnNm;
	private String strTotRd;
	private String strBWRd;
	private String strClrRd;
	private String strRspTm;
	private String strRstrTm;
	private String strInstlCompNm;
	private String strPblmTpCd;
	private String strTskSts;
	private String strMachMngr;
	private String strMaterialChrg;
	private String strLabrChrg;
	private String strTechNotes;
	private String strModNum;
	private String strContNumbr;
	private String strContSts;
	private String strAveMonthlyVol;
	private String strCntrFlg;
	private List counterLst;
	
	public CanonE307SRHistDownloadRec() {
	}
	
	public CanonE307SRHistDownloadRec(String strFSR, String strTaskNumber,
			String strTskCrtnDt, String strSerNum, String strTskTpe,
			String strActualEndDt, String strAsgnNm, String strTotRd,
			String strBWRd, String strClrRd, String strRspTm, String strRstrTm,
			String strInstlCompNm, String strPblmTpCd, String strTskSts,
			String strMachMngr, String strMaterialChrg, String strLabrChrg,
			String strTechNotes, String strModNum, String strContNumbr,
			String strContSts, String strAveMonthlyVol, String strCntrFlg,
			List counterLst) {
		super();
		this.strFSR = strFSR;
		this.strTaskNumber = strTaskNumber;
		this.strTskCrtnDt = strTskCrtnDt;
		this.strSerNum = strSerNum;
		this.strTskTpe = strTskTpe;
		this.strActualEndDt = strActualEndDt;
		this.strAsgnNm = strAsgnNm;
		this.strTotRd = strTotRd;
		this.strBWRd = strBWRd;
		this.strClrRd = strClrRd;
		this.strRspTm = strRspTm;
		this.strRstrTm = strRstrTm;
		this.strInstlCompNm = strInstlCompNm;
		this.strPblmTpCd = strPblmTpCd;
		this.strTskSts = strTskSts;
		this.strMachMngr = strMachMngr;
		this.strMaterialChrg = strMaterialChrg;
		this.strLabrChrg = strLabrChrg;
		this.strTechNotes = strTechNotes;
		this.strModNum = strModNum;
		this.strContNumbr = strContNumbr;
		this.strContSts = strContSts;
		this.strAveMonthlyVol = strAveMonthlyVol;
		this.strCntrFlg = strCntrFlg;
		this.counterLst = counterLst;
	}


/*	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_DWNLD_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFSR = stream.readString();
		strTaskNumber = stream.readString();
		strTskCrtnDt = stream.readString();
		strSerNum = stream.readString();
		strTskTpe = stream.readString();
		strActualEndDt = stream.readString();
		strAsgnNm = stream.readString();
		strTotRd = stream.readDouble();
		strBWRd = stream.readDouble();
		strClrRd = stream.readDouble();
		strRspTm = stream.readString();
		strRstrTm = stream.readString();
		strInstlCompNm = stream.readString();
		strPblmTpCd = stream.readString();
		strTskSts = stream.readString();
		strMachMngr = stream.readString();
		strMaterialChrg = stream.readString();
		strLabrChrg = stream.readString();
		strTechNotes = stream.readString();
		strModNum = stream.readString();
		strContNumbr = stream.readString();
		strContSts = stream.readString();
		strAveMonthlyVol = stream.readDouble();
		strCntrFlg = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFSR);
		stream.writeString(strTaskNumber);
		stream.writeString(strTskCrtnDt);
		stream.writeString(strSerNum);
		stream.writeString(strTskTpe);
		stream.writeString(strActualEndDt);
		stream.writeString(strAsgnNm);
		stream.writeDouble(strTotRd);
		stream.writeDouble(strBWRd);
		stream.writeDouble(strClrRd);
		stream.writeString(strRspTm);
		stream.writeString(strRstrTm);
		stream.writeString(strInstlCompNm);
		stream.writeString(strPblmTpCd);
		stream.writeString(strTskSts);
		stream.writeString(strMachMngr);
		stream.writeString(strMaterialChrg);
		stream.writeString(strLabrChrg);
		stream.writeString(strTechNotes);
		stream.writeString(strModNum);
		stream.writeString(strContNumbr);
		stream.writeString(strContSts);
		stream.writeDouble(strAveMonthlyVol);
		stream.writeString(strCntrFlg);
	} */

	public String getStrFSR() {
		return strFSR;
	}

	public void setStrFSR(String strFSR) {
		this.strFSR = strFSR;
	}

	public String getStrTaskNumber() {
		return strTaskNumber;
	}

	public void setStrTaskNumber(String strTaskNumber) {
		this.strTaskNumber = strTaskNumber;
	}

	public String getStrTskCrtnDt() {
		return strTskCrtnDt;
	}

	public void setStrTskCrtnDt(String strTskCrtnDt) {
		this.strTskCrtnDt = strTskCrtnDt;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrTskTpe() {
		return strTskTpe;
	}

	public void setStrTskTpe(String strTskTpe) {
		this.strTskTpe = strTskTpe;
	}

	public String getStrActualEndDt() {
		return strActualEndDt;
	}

	public void setStrActualEndDt(String strActualEndDt) {
		this.strActualEndDt = strActualEndDt;
	}

	public String getStrAsgnNm() {
		return strAsgnNm;
	}

	public void setStrAsgnNm(String strAsgnNm) {
		this.strAsgnNm = strAsgnNm;
	}

	public String getStrRspTm() {
		return strRspTm;
	}

	public void setStrRspTm(String strRspTm) {
		this.strRspTm = strRspTm;
	}

	public String getStrRstrTm() {
		return strRstrTm;
	}

	public void setStrRstrTm(String strRstrTm) {
		this.strRstrTm = strRstrTm;
	}

	public String getStrInstlCompNm() {
		return strInstlCompNm;
	}

	public void setStrInstlCompNm(String strInstlCompNm) {
		this.strInstlCompNm = strInstlCompNm;
	}

	public String getStrPblmTpCd() {
		return strPblmTpCd;
	}

	public void setStrPblmTpCd(String strPblmTpCd) {
		this.strPblmTpCd = strPblmTpCd;
	}

	public String getStrTskSts() {
		return strTskSts;
	}

	public void setStrTskSts(String strTskSts) {
		this.strTskSts = strTskSts;
	}

	public String getStrMachMngr() {
		return strMachMngr;
	}

	public void setStrMachMngr(String strMachMngr) {
		this.strMachMngr = strMachMngr;
	}

	public String getStrMaterialChrg() {
		return strMaterialChrg;
	}

	public void setStrMaterialChrg(String strMaterialChrg) {
		this.strMaterialChrg = strMaterialChrg;
	}

	public String getStrLabrChrg() {
		return strLabrChrg;
	}

	public void setStrLabrChrg(String strLabrChrg) {
		this.strLabrChrg = strLabrChrg;
	}

	public String getStrTechNotes() {
		return strTechNotes;
	}

	public void setStrTechNotes(String strTechNotes) {
		this.strTechNotes = strTechNotes;
	}

	public String getStrModNum() {
		return strModNum;
	}

	public void setStrModNum(String strModNum) {
		this.strModNum = strModNum;
	}

	public String getStrContNumbr() {
		return strContNumbr;
	}

	public void setStrContNumbr(String strContNumbr) {
		this.strContNumbr = strContNumbr;
	}

	public String getStrContSts() {
		return strContSts;
	}

	public void setStrContSts(String strContSts) {
		this.strContSts = strContSts;
	}

	public String getStrAveMonthlyVol() {
		return strAveMonthlyVol;
	}

	public void setStrAveMonthlyVol(String strAveMonthlyVol) {
		this.strAveMonthlyVol = strAveMonthlyVol;
	}

	public String getStrTotRd() {
		return strTotRd;
	}

	public void setStrTotRd(String strTotRd) {
		this.strTotRd = strTotRd;
	}

	public String getStrBWRd() {
		return strBWRd;
	}

	public void setStrBWRd(String strBWRd) {
		this.strBWRd = strBWRd;
	}

	public String getStrClrRd() {
		return strClrRd;
	}

	public void setStrClrRd(String strClrRd) {
		this.strClrRd = strClrRd;
	}

	public String getStrCntrFlg() {
		return strCntrFlg;
	}

	public void setStrCntrFlg(String strCntrFlg) {
		this.strCntrFlg = strCntrFlg;
	}

	public List getCounterLst() {
		return counterLst;
	}

	public void setCounterLst(List counterLst) {
		this.counterLst = counterLst;
	}

	@Override
	public String toString() {
		return "CanonE307SRHistDownloadRec [strFSR=" + strFSR
				+ ", strTaskNumber=" + strTaskNumber + ", strTskCrtnDt="
				+ strTskCrtnDt + ", strSerNum=" + strSerNum + ", strTskTpe="
				+ strTskTpe + ", strActualEndDt=" + strActualEndDt
				+ ", strAsgnNm=" + strAsgnNm + ", strTotRd=" + strTotRd
				+ ", strBWRd=" + strBWRd + ", strClrRd=" + strClrRd
				+ ", strRspTm=" + strRspTm + ", strRstrTm=" + strRstrTm
				+ ", strInstlCompNm=" + strInstlCompNm + ", strPblmTpCd="
				+ strPblmTpCd + ", strTskSts=" + strTskSts + ", strMachMngr="
				+ strMachMngr + ", strMaterialChrg=" + strMaterialChrg
				+ ", strLabrChrg=" + strLabrChrg + ", strTechNotes="
				+ strTechNotes + ", strModNum=" + strModNum + ", strContNumbr="
				+ strContNumbr + ", strContSts=" + strContSts
				+ ", strAveMonthlyVol=" + strAveMonthlyVol + ", strCntrFlg="
				+ strCntrFlg + ", counterLst=" + counterLst + "]";
	}


}
