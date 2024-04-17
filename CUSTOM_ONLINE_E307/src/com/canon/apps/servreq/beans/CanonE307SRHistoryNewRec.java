package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRHistoryNewRec implements SQLData{
	
	private String strFSR;
	private String strCreationDt;	
	private String strFsrType;		
	private String strFSRSts;
	private String strSvcPblmTpCd;
	private String strPblmNt;
	private String strMblNt;
	private String strLastMtrRd;	
	private String strRespTm;
	private String strRstrTm;
	private String strTskPblmCd;
	private String strCorrectionCd;	
	private String strLocationCd;
	private String strRsnCd;
	private String strMachSts;
	private String strCreatedBy;
	private String strMachMstrPk;
	private String strPostalCd;
	private String strCntryCd;
	private String strEOLFlg;
	private String strAttibute1;
	private String strAttibute2;
	private String strAttibute3;
	private String strAttibute4;
	private String strAttibute5;
	


	public CanonE307SRHistoryNewRec(String strFSR, String strCreationDt,
			String strFsrType, String strFSRSts, String strSvcPblmTpCd,
			String strPblmNt, String strMblNt, String strLastMtrRd,
			String strRespTm, String strRstrTm, String strTskPblmCd,
			String strCorrectionCd, String strLocationCd, String strRsnCd,
			String strMachSts, String strCreatedBy, String strMachMstrPk,
			String strPostalCd, String strCntryCd, String strEOLFlg,
			String strAttibute1, String strAttibute2, String strAttibute3,
			String strAttibute4, String strAttibute5) {
		super();
		this.strFSR = strFSR;
		this.strCreationDt = strCreationDt;
		this.strFsrType = strFsrType;
		this.strFSRSts = strFSRSts;
		this.strSvcPblmTpCd = strSvcPblmTpCd;
		this.strPblmNt = strPblmNt;
		this.strMblNt = strMblNt;
		this.strLastMtrRd = strLastMtrRd;
		this.strRespTm = strRespTm;
		this.strRstrTm = strRstrTm;
		this.strTskPblmCd = strTskPblmCd;
		this.strCorrectionCd = strCorrectionCd;
		this.strLocationCd = strLocationCd;
		this.strRsnCd = strRsnCd;
		this.strMachSts = strMachSts;
		this.strCreatedBy = strCreatedBy;
		this.strMachMstrPk = strMachMstrPk;
		this.strPostalCd = strPostalCd;
		this.strCntryCd = strCntryCd;
		this.strEOLFlg = strEOLFlg;
		this.strAttibute1 = strAttibute1;
		this.strAttibute2 = strAttibute2;
		this.strAttibute3 = strAttibute3;
		this.strAttibute4 = strAttibute4;
		this.strAttibute5 = strAttibute5;
	}



	public CanonE307SRHistoryNewRec() {
		// TODO Auto-generated constructor stub
	}



	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_NEW_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFSR = stream.readString();
		strCreationDt = stream.readString();
		strFsrType = stream.readString();
		strFSRSts = stream.readString();
		strSvcPblmTpCd = stream.readString();
		strPblmNt = stream.readString();
		strMblNt = stream.readString();
		strLastMtrRd = stream.readString();
		strRespTm = stream.readString();		
		strRstrTm = stream.readString();
		strTskPblmCd = stream.readString();
		strCorrectionCd = stream.readString();
		strLocationCd = stream.readString();
		strRsnCd = stream.readString();
		strMachSts = stream.readString();
		strCreatedBy = stream.readString();
		strMachMstrPk = stream.readString();
		strPostalCd = stream.readString();	
		strCntryCd = stream.readString();
		strEOLFlg = stream.readString();
		strAttibute1 = stream.readString();
		strAttibute2 = stream.readString();
		strAttibute3 = stream.readString();
		strAttibute4 = stream.readString();
		strAttibute5 = stream.readString();
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFSR);
		stream.writeString(strCreationDt);
		stream.writeString(strFsrType);
		stream.writeString(strFSRSts);
		stream.writeString(strSvcPblmTpCd);
		stream.writeString(strPblmNt);
		stream.writeString(strMblNt);
		stream.writeString(strLastMtrRd);
		stream.writeString(strRespTm);		
		stream.writeString(strRstrTm);
		stream.writeString(strTskPblmCd);
		stream.writeString(strCorrectionCd);
		stream.writeString(strLocationCd);
		stream.writeString(strRsnCd);
		stream.writeString(strMachSts);
		stream.writeString(strCreatedBy);
		stream.writeString(strMachMstrPk);
		stream.writeString(strPostalCd);		
		stream.writeString(strCntryCd);
		stream.writeString(strEOLFlg);
		stream.writeString(strAttibute1);
		stream.writeString(strAttibute2);
		stream.writeString(strAttibute3);
		stream.writeString(strAttibute4);
		stream.writeString(strAttibute5);
	}
	

	public String getStrFSR() {
		return strFSR;
	}



	public void setStrFSR(String strFSR) {
		this.strFSR = strFSR;
	}



	public String getStrCreationDt() {
		return strCreationDt;
	}



	public void setStrCreationDt(String strCreationDt) {
		this.strCreationDt = strCreationDt;
	}



	public String getStrFsrType() {
		return strFsrType;
	}



	public void setStrFsrType(String strFsrType) {
		this.strFsrType = strFsrType;
	}



	public String getStrFSRSts() {
		return strFSRSts;
	}



	public void setStrFSRSts(String strFSRSts) {
		this.strFSRSts = strFSRSts;
	}



	public String getStrSvcPblmTpCd() {
		return strSvcPblmTpCd;
	}



	public void setStrSvcPblmTpCd(String strSvcPblmTpCd) {
		this.strSvcPblmTpCd = strSvcPblmTpCd;
	}



	public String getStrPblmNt() {
		return strPblmNt;
	}



	public void setStrPblmNt(String strPblmNt) {
		this.strPblmNt = strPblmNt;
	}



	public String getStrMblNt() {
		return strMblNt;
	}



	public void setStrMblNt(String strMblNt) {
		this.strMblNt = strMblNt;
	}



	public String getStrLastMtrRd() {
		return strLastMtrRd;
	}



	public void setStrLastMtrRd(String strLastMtrRd) {
		this.strLastMtrRd = strLastMtrRd;
	}



	public String getStrRespTm() {
		return strRespTm;
	}



	public void setStrRespTm(String strRespTm) {
		this.strRespTm = strRespTm;
	}



	public String getStrRstrTm() {
		return strRstrTm;
	}



	public void setStrRstrTm(String strRstrTm) {
		this.strRstrTm = strRstrTm;
	}



	public String getStrTskPblmCd() {
		return strTskPblmCd;
	}



	public void setStrTskPblmCd(String strTskPblmCd) {
		this.strTskPblmCd = strTskPblmCd;
	}



	public String getStrCorrectionCd() {
		return strCorrectionCd;
	}



	public void setStrCorrectionCd(String strCorrectionCd) {
		this.strCorrectionCd = strCorrectionCd;
	}



	public String getStrLocationCd() {
		return strLocationCd;
	}



	public void setStrLocationCd(String strLocationCd) {
		this.strLocationCd = strLocationCd;
	}



	public String getStrRsnCd() {
		return strRsnCd;
	}



	public void setStrRsnCd(String strRsnCd) {
		this.strRsnCd = strRsnCd;
	}



	public String getStrMachSts() {
		return strMachSts;
	}



	public void setStrMachSts(String strMachSts) {
		this.strMachSts = strMachSts;
	}



	public String getStrCreatedBy() {
		return strCreatedBy;
	}



	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}



	public String getStrMachMstrPk() {
		return strMachMstrPk;
	}



	public void setStrMachMstrPk(String strMachMstrPk) {
		this.strMachMstrPk = strMachMstrPk;
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



	public String getStrEOLFlg() {
		return strEOLFlg;
	}



	public void setStrEOLFlg(String strEOLFlg) {
		this.strEOLFlg = strEOLFlg;
	}



	public String getStrAttibute1() {
		return strAttibute1;
	}



	public void setStrAttibute1(String strAttibute1) {
		this.strAttibute1 = strAttibute1;
	}



	public String getStrAttibute2() {
		return strAttibute2;
	}



	public void setStrAttibute2(String strAttibute2) {
		this.strAttibute2 = strAttibute2;
	}



	public String getStrAttibute3() {
		return strAttibute3;
	}



	public void setStrAttibute3(String strAttibute3) {
		this.strAttibute3 = strAttibute3;
	}



	public String getStrAttibute4() {
		return strAttibute4;
	}



	public void setStrAttibute4(String strAttibute4) {
		this.strAttibute4 = strAttibute4;
	}



	public String getStrAttibute5() {
		return strAttibute5;
	}



	public void setStrAttibute5(String strAttibute5) {
		this.strAttibute5 = strAttibute5;
	}



	@Override
	public String toString() {
		return "CanonE307SRHistoryNewRec [strFSR=" + strFSR
				+ ", strCreationDt=" + strCreationDt + ", strFsrType="
				+ strFsrType + ", strFSRSts=" + strFSRSts + ", strSvcPblmTpCd="
				+ strSvcPblmTpCd + ", strPblmNt=" + strPblmNt + ", strMblNt="
				+ strMblNt + ", strLastMtrRd=" + strLastMtrRd + ", strRespTm="
				+ strRespTm + ", strRstrTm=" + strRstrTm + ", strTskPblmCd="
				+ strTskPblmCd + ", strCorrectionCd=" + strCorrectionCd
				+ ", strLocationCd=" + strLocationCd + ", strRsnCd=" + strRsnCd
				+ ", strMachSts=" + strMachSts + ", strCreatedBy="
				+ strCreatedBy + ", strMachMstrPk=" + strMachMstrPk
				+ ", strPostalCd=" + strPostalCd + ", strCntryCd=" + strCntryCd
				+ ", strEOLFlg=" + strEOLFlg + ", strAttibute1=" + strAttibute1
				+ ", strAttibute2=" + strAttibute2 + ", strAttibute3="
				+ strAttibute3 + ", strAttibute4=" + strAttibute4
				+ ", strAttibute5=" + strAttibute5 + "]";
	}





	
	
}
