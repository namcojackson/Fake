package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqInfoRec implements SQLData{

	public CanonE307ServiceReqInfoRec() {
		// TODO Auto-generated constructor stub
	}
	private String strFSRNum;
	private String strITTNum;
	private String strSvcBillTpCd;
	private String strSvcBillTpNm;
	private String strFSRCratDt;
	private String strRspndByDt;
	private String strLastUptDt;
	private String strLastUpdtBy;
	private String strCreatedBy;
	private String strMachMngrId;
	private String strMachMngrNm;
	private String strPblmTpCd;
	private String strPblmTpNm;
	private String strMachStatus;
	private String strCallSrcTpCd;
	private String strCallSrcTpNm;
//	private String strTimeZone;
	private String strLineBizTpCd;
	private String strBrDescTxt;
	private String strCustPONum;
//	private String strPoUploadFlag;
//	private String strPdfName;
//	private String strVerPOUplFlag;
	private String strCurAddrLine;
	private String strCurCity;
	private String strCurStCd;
	private String strCurPostCd;
	private String strCurCtryCd;
	private String strLocDispString;
	private String strBillAddrLine;
	private String strBillCity;
	private String strBillStCd;
	private String strBillPostCd;
	private String strBillCtryCd;
	private String strBillToDispString;
	private String strPmtTerm;
	private String strSvcWindowFrom;
	private String strSvcWindowTo;
	private String strETA;
	private String strFutureServDate;
	private String strFutureServTime;
	private String strSrSts;
	private String strCustCaseNo;
	private String strPoVerFlag;
	private String strSrTpCd;
	private String strSrTpNm;
	private String strCurCustCd;
	private String strCurCustNm;
	private String strBllCustCd;
	private String strBllCustNm;
	private String strCustPoDt;
	private String strFsrUpdFlag;
	private String strBilbleFlg;
	private String strPOReqFlg;
	private String strPOFileName;
	private String strPOAttPk;
	private String strSlsDt;
	private String strCCFlag;
	private String strProfileId;
	private String strHolderName;
	private String strCardType;
	private String strExprDt;
	private String strAuthAmt;
	private String strContActvFlg;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strVendName;
	private String strVendContact;
	private String strVendPhone;
	private String strVendEmailAddr;
	private String strCovidVacMsg;

	
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_INFO_REC";
	}
	

	public CanonE307ServiceReqInfoRec(String strFSRNum, String strITTNum,
			String strSvcBillTpCd, String strSvcBillTpNm, String strFSRCratDt,
			String strRspndByDt, String strLastUptDt, String strLastUpdtBy,
			String strCreatedBy, String strMachMngrId, String strMachMngrNm,
			String strPblmTpCd, String strPblmTpNm, String strMachStatus,
			String strCallSrcTpCd, String strCallSrcTpNm,
			String strLineBizTpCd, String strBrDescTxt, String strCustPONum,
			String strCurAddrLine, String strCurCity, String strCurStCd,
			String strCurPostCd, String strCurCtryCd, String strLocDispString,
			String strBillAddrLine, String strBillCity, String strBillStCd,
			String strBillPostCd, String strBillCtryCd,
			String strBillToDispString, String strPmtTerm,
			String strSvcWindowFrom, String strSvcWindowTo, String strETA,
			String strFutureServDate, String strFutureServTime,
			String strSrSts, String strCustCaseNo, String strPoVerFlag,
			String strSrTpCd, String strSrTpNm, String strCurCustCd,
			String strCurCustNm, String strBllCustCd, String strBllCustNm,
			String strCustPoDt, String strFsrUpdFlag, String strBilbleFlg,
			String strPOReqFlg, String strPOFileName, String strPOAttPk,
			String strSlsDt, String strCCFlag, String strProfileId,
			String strHolderName, String strCardType, String strExprDt,
			String strAuthAmt, String strContActvFlg, String strAttribute1,
			String strAttribute2, String strAttribute3, String strVendName,
			String strVendContact, String strVendPhone,
			String strVendEmailAddr, String strCovidVacMsg) {
		super();
		this.strFSRNum = strFSRNum;
		this.strITTNum = strITTNum;
		this.strSvcBillTpCd = strSvcBillTpCd;
		this.strSvcBillTpNm = strSvcBillTpNm;
		this.strFSRCratDt = strFSRCratDt;
		this.strRspndByDt = strRspndByDt;
		this.strLastUptDt = strLastUptDt;
		this.strLastUpdtBy = strLastUpdtBy;
		this.strCreatedBy = strCreatedBy;
		this.strMachMngrId = strMachMngrId;
		this.strMachMngrNm = strMachMngrNm;
		this.strPblmTpCd = strPblmTpCd;
		this.strPblmTpNm = strPblmTpNm;
		this.strMachStatus = strMachStatus;
		this.strCallSrcTpCd = strCallSrcTpCd;
		this.strCallSrcTpNm = strCallSrcTpNm;
		this.strLineBizTpCd = strLineBizTpCd;
		this.strBrDescTxt = strBrDescTxt;
		this.strCustPONum = strCustPONum;
		this.strCurAddrLine = strCurAddrLine;
		this.strCurCity = strCurCity;
		this.strCurStCd = strCurStCd;
		this.strCurPostCd = strCurPostCd;
		this.strCurCtryCd = strCurCtryCd;
		this.strLocDispString = strLocDispString;
		this.strBillAddrLine = strBillAddrLine;
		this.strBillCity = strBillCity;
		this.strBillStCd = strBillStCd;
		this.strBillPostCd = strBillPostCd;
		this.strBillCtryCd = strBillCtryCd;
		this.strBillToDispString = strBillToDispString;
		this.strPmtTerm = strPmtTerm;
		this.strSvcWindowFrom = strSvcWindowFrom;
		this.strSvcWindowTo = strSvcWindowTo;
		this.strETA = strETA;
		this.strFutureServDate = strFutureServDate;
		this.strFutureServTime = strFutureServTime;
		this.strSrSts = strSrSts;
		this.strCustCaseNo = strCustCaseNo;
		this.strPoVerFlag = strPoVerFlag;
		this.strSrTpCd = strSrTpCd;
		this.strSrTpNm = strSrTpNm;
		this.strCurCustCd = strCurCustCd;
		this.strCurCustNm = strCurCustNm;
		this.strBllCustCd = strBllCustCd;
		this.strBllCustNm = strBllCustNm;
		this.strCustPoDt = strCustPoDt;
		this.strFsrUpdFlag = strFsrUpdFlag;
		this.strBilbleFlg = strBilbleFlg;
		this.strPOReqFlg = strPOReqFlg;
		this.strPOFileName = strPOFileName;
		this.strPOAttPk = strPOAttPk;
		this.strSlsDt = strSlsDt;
		this.strCCFlag = strCCFlag;
		this.strProfileId = strProfileId;
		this.strHolderName = strHolderName;
		this.strCardType = strCardType;
		this.strExprDt = strExprDt;
		this.strAuthAmt = strAuthAmt;
		this.strContActvFlg = strContActvFlg;
		this.strAttribute1 = strAttribute1;
		this.strAttribute2 = strAttribute2;
		this.strAttribute3 = strAttribute3;
		this.strVendName = strVendName;
		this.strVendContact = strVendContact;
		this.strVendPhone = strVendPhone;
		this.strVendEmailAddr = strVendEmailAddr;
		this.strCovidVacMsg = strCovidVacMsg;
	}






	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFSRNum = stream.readString();
		strITTNum= stream.readString();
		strSvcBillTpCd= stream.readString();
		strSvcBillTpNm=stream.readString();
		strFSRCratDt = stream.readString();
		strRspndByDt = stream.readString();
		strLastUptDt = stream.readString();
		strLastUpdtBy = stream.readString();
		strCreatedBy = stream.readString();
		strMachMngrId = stream.readString();
		strMachMngrNm = stream.readString();
		strPblmTpCd = stream.readString();
		strPblmTpNm=stream.readString();
		strMachStatus = stream.readString();
		strCallSrcTpCd = stream.readString();
		strCallSrcTpNm = stream.readString();
	//	strTimeZone = stream.readString();
		strLineBizTpCd = stream.readString();
		strBrDescTxt = stream.readString();
		strCustPONum = stream.readString();
	//	strPoUploadFlag = stream.readString();
	//	strVerPOUplFlag = stream.readString();
		strCurAddrLine = stream.readString();
		strCurCity=stream.readString();
		strCurStCd=stream.readString();
		strCurPostCd=stream.readString();
		strCurCtryCd=stream.readString();
		strLocDispString = stream.readString();
		strBillAddrLine = stream.readString();
		strBillCity = stream.readString();
		strBillStCd = stream.readString();
		strBillPostCd = stream.readString();
		strBillCtryCd = stream.readString();
		strBillToDispString = stream.readString();
		strPmtTerm= stream.readString();
		strSvcWindowFrom= stream.readString();
		strSvcWindowTo= stream.readString();
		strETA= stream.readString();
		strFutureServDate = stream.readString();
		strFutureServTime=stream.readString();
		strSrSts = stream.readString();
		strCustCaseNo = stream.readString();
		strPoVerFlag = stream.readString();	
		strSrTpCd = stream.readString();
		strSrTpNm = stream.readString();
		strCurCustCd = stream.readString();
		strCurCustNm = stream.readString();
		strBllCustCd = stream.readString();
		strBllCustNm = stream.readString();
		strCustPoDt = stream.readString();
		strFsrUpdFlag = stream.readString();
		strBilbleFlg = stream.readString();
		strPOReqFlg = stream.readString();
		strPOFileName = stream.readString();
		strPOAttPk = stream.readString();
		strSlsDt = stream.readString();
		strCCFlag = stream.readString();
		strProfileId = stream.readString();
		strHolderName = stream.readString();
		strCardType = stream.readString();
		strExprDt = stream.readString();
		strAuthAmt = stream.readString();
		strContActvFlg = stream.readString();
		strAttribute1 = stream.readString();
		strAttribute2 =  stream.readString();
		strAttribute3 = stream.readString();
		strVendName = stream.readString();
		strVendContact = stream.readString();
		strVendPhone = stream.readString();
		strVendEmailAddr = stream.readString();
		strCovidVacMsg = stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFSRNum);
		stream.writeString(strITTNum);
		stream.writeString(strSvcBillTpCd);
		stream.writeString(strSvcBillTpNm);
		stream.writeString(strFSRCratDt);
		stream.writeString(strRspndByDt);
		stream.writeString(strLastUptDt);
		stream.writeString(strLastUpdtBy);
		stream.writeString(strCreatedBy);
		stream.writeString(strMachMngrId);
		stream.writeString(strMachMngrNm);
		stream.writeString(strPblmTpCd);
		stream.writeString(strPblmTpNm);
		stream.writeString(strMachStatus);
		stream.writeString(strCallSrcTpCd);
		stream.writeString(strCallSrcTpNm);
	//	stream.writeString(strTimeZone);
		stream.writeString(strLineBizTpCd);
		stream.writeString(strBrDescTxt);
		stream.writeString(strCustPONum);
	//	stream.writeString(strPoUploadFlag);
	//	stream.writeString(strVerPOUplFlag);
		stream.writeString(strCurAddrLine);
		stream.writeString(strCurCity);
		stream.writeString(strCurStCd);
		stream.writeString(strCurPostCd);
		stream.writeString(strCurCtryCd);
		stream.writeString(strLocDispString);
		stream.writeString(strBillAddrLine);
		stream.writeString(strBillCity);
		stream.writeString(strBillStCd);
		stream.writeString(strBillPostCd);
		stream.writeString(strBillCtryCd);
		stream.writeString(strBillToDispString);
		stream.writeString(strPmtTerm);
		stream.writeString(strSvcWindowFrom);
		stream.writeString(strSvcWindowTo);		
		stream.writeString(strETA);
		stream.writeString(strFutureServDate);
		stream.writeString(strFutureServTime);
		stream.writeString(strSrSts);
		stream.writeString(strCustCaseNo);
		stream.writeString(strPoVerFlag);	
		stream.writeString(strSrTpCd);
		stream.writeString(strSrTpNm);
		stream.writeString(strCurCustCd);
		stream.writeString(strCurCustNm);
		stream.writeString(strBllCustCd);
		stream.writeString(strBllCustNm);
		stream.writeString(strCustPoDt);
		stream.writeString(strFsrUpdFlag);
		stream.writeString(strBilbleFlg);
		stream.writeString(strPOReqFlg);
		stream.writeString(strPOFileName);
		stream.writeString(strPOAttPk);
		stream.writeString(strSlsDt);
		stream.writeString(strCCFlag);
		stream.writeString(strProfileId);
		stream.writeString(strHolderName);
		stream.writeString(strCardType);
		stream.writeString(strExprDt);
		stream.writeString(strAuthAmt);
		stream.writeString(strContActvFlg);
		stream.writeString(strAttribute1);
		stream.writeString(strAttribute2);
		stream.writeString(strAttribute3);
		stream.writeString(strVendName);
		stream.writeString(strVendContact);
		stream.writeString(strVendPhone);
		stream.writeString(strVendEmailAddr);
		stream.writeString(strCovidVacMsg);
	}
	
	public String getStrFSRNum() {
		return strFSRNum;
	}
	public void setStrFSRNum(String strFSRNum) {
		this.strFSRNum = strFSRNum;
	}
	public String getStrITTNum() {
		return strITTNum;
	}
	public void setStrITTNum(String strITTNum) {
		this.strITTNum = strITTNum;
	}
	public String getStrSvcBillTpCd() {
		return strSvcBillTpCd;
	}
	public void setStrSvcBillTpCd(String strSvcBillTpCd) {
		this.strSvcBillTpCd = strSvcBillTpCd;
	}
	public String getStrSvcBillTpNm() {
		return strSvcBillTpNm;
	}
	public void setStrSvcBillTpNm(String strSvcBillTpNm) {
		this.strSvcBillTpNm = strSvcBillTpNm;
	}
	public String getStrFSRCratDt() {
		return strFSRCratDt;
	}
	public void setStrFSRCratDt(String strFSRCratDt) {
		this.strFSRCratDt = strFSRCratDt;
	}
	public String getStrRspndByDt() {
		return strRspndByDt;
	}
	public void setStrRspndByDt(String strRspndByDt) {
		this.strRspndByDt = strRspndByDt;
	}
	public String getStrLastUptDt() {
		return strLastUptDt;
	}
	public void setStrLastUptDt(String strLastUptDt) {
		this.strLastUptDt = strLastUptDt;
	}
	public String getStrLastUpdtBy() {
		return strLastUpdtBy;
	}
	public void setStrLastUpdtBy(String strLastUpdtBy) {
		this.strLastUpdtBy = strLastUpdtBy;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public String getStrMachMngrId() {
		return strMachMngrId;
	}
	public void setStrMachMngrId(String strMachMngrId) {
		this.strMachMngrId = strMachMngrId;
	}
	public String getStrMachMngrNm() {
		return strMachMngrNm;
	}
	public void setStrMachMngrNm(String strMachMngrNm) {
		this.strMachMngrNm = strMachMngrNm;
	}
	public String getStrPblmTpCd() {
		return strPblmTpCd;
	}
	public void setStrPblmTpCd(String strPblmTpCd) {
		this.strPblmTpCd = strPblmTpCd;
	}
	public String getStrPblmTpNm() {
		return strPblmTpNm;
	}
	public void setStrPblmTpNm(String strPblmTpNm) {
		this.strPblmTpNm = strPblmTpNm;
	}
	public String getStrMachStatus() {
		return strMachStatus;
	}
	public void setStrMachStatus(String strMachStatus) {
		this.strMachStatus = strMachStatus;
	}
	public String getStrCallSrcTpNm() {
		return strCallSrcTpNm;
	}
	public void setStrCallSrcTpNm(String strCallSrcTpNm) {
		this.strCallSrcTpNm = strCallSrcTpNm;
	}
	public String getStrLineBizTpCd() {
		return strLineBizTpCd;
	}
	public void setStrLineBizTpCd(String strLineBizTpCd) {
		this.strLineBizTpCd = strLineBizTpCd;
	}
	public String getStrBrDescTxt() {
		return strBrDescTxt;
	}
	public void setStrBrDescTxt(String strBrDescTxt) {
		this.strBrDescTxt = strBrDescTxt;
	}
	public String getStrCustPONum() {
		return strCustPONum;
	}
	public void setStrCustPONum(String strCustPONum) {
		this.strCustPONum = strCustPONum;
	}
	public String getStrCurAddrLine() {
		return strCurAddrLine;
	}
	public void setStrCurAddrLine(String strCurAddrLine) {
		this.strCurAddrLine = strCurAddrLine;
	}
	public String getStrCurCity() {
		return strCurCity;
	}
	public void setStrCurCity(String strCurCity) {
		this.strCurCity = strCurCity;
	}
	public String getStrCurStCd() {
		return strCurStCd;
	}
	public void setStrCurStCd(String strCurStCd) {
		this.strCurStCd = strCurStCd;
	}
	public String getStrCurPostCd() {
		return strCurPostCd;
	}
	public void setStrCurPostCd(String strCurPostCd) {
		this.strCurPostCd = strCurPostCd;
	}
	public String getStrCurCtryCd() {
		return strCurCtryCd;
	}
	public void setStrCurCtryCd(String strCurCtryCd) {
		this.strCurCtryCd = strCurCtryCd;
	}
	public String getStrLocDispString() {
		return strLocDispString;
	}
	public void setStrLocDispString(String strLocDispString) {
		this.strLocDispString = strLocDispString;
	}
	public String getStrBillAddrLine() {
		return strBillAddrLine;
	}
	public void setStrBillAddrLine(String strBillAddrLine) {
		this.strBillAddrLine = strBillAddrLine;
	}
	public String getStrBillCity() {
		return strBillCity;
	}
	public void setStrBillCity(String strBillCity) {
		this.strBillCity = strBillCity;
	}
	public String getStrBillStCd() {
		return strBillStCd;
	}
	public void setStrBillStCd(String strBillStCd) {
		this.strBillStCd = strBillStCd;
	}
	public String getStrBillPostCd() {
		return strBillPostCd;
	}
	public void setStrBillPostCd(String strBillPostCd) {
		this.strBillPostCd = strBillPostCd;
	}
	public String getStrBillCtryCd() {
		return strBillCtryCd;
	}
	public void setStrBillCtryCd(String strBillCtryCd) {
		this.strBillCtryCd = strBillCtryCd;
	}
	public String getStrBillToDispString() {
		return strBillToDispString;
	}
	public void setStrBillToDispString(String strBillToDispString) {
		this.strBillToDispString = strBillToDispString;
	}
	public String getStrPmtTerm() {
		return strPmtTerm;
	}
	public void setStrPmtTerm(String strPmtTerm) {
		this.strPmtTerm = strPmtTerm;
	}
	public String getStrSvcWindowFrom() {
		return strSvcWindowFrom;
	}
	public void setStrSvcWindowFrom(String strSvcWindowFrom) {
		this.strSvcWindowFrom = strSvcWindowFrom;
	}
	public String getStrSvcWindowTo() {
		return strSvcWindowTo;
	}
	public void setStrSvcWindowTo(String strSvcWindowTo) {
		this.strSvcWindowTo = strSvcWindowTo;
	}
	public String getStrETA() {
		return strETA;
	}
	public void setStrETA(String strETA) {
		this.strETA = strETA;
	}
	public String getStrFutureServDate() {
		return strFutureServDate;
	}
	public void setStrFutureServDate(String strFutureServDate) {
		this.strFutureServDate = strFutureServDate;
	}
	public String getStrFutureServTime() {
		return strFutureServTime;
	}
	public void setStrFutureServTime(String strFutureServTime) {
		this.strFutureServTime = strFutureServTime;
	}
	public String getStrSrSts() {
		return strSrSts;
	}
	public void setStrSrSts(String strSrSts) {
		this.strSrSts = strSrSts;
	}
	public String getStrCustCaseNo() {
		return strCustCaseNo;
	}
	public void setStrCustCaseNo(String strCustCaseNo) {
		this.strCustCaseNo = strCustCaseNo;
	}
	public String getStrPoVerFlag() {
		return strPoVerFlag;
	}
	public void setStrPoVerFlag(String strPoVerFlag) {
		this.strPoVerFlag = strPoVerFlag;
	}
	public String getStrSrTpCd() {
		return strSrTpCd;
	}
	public void setStrSrTpCd(String strSrTpCd) {
		this.strSrTpCd = strSrTpCd;
	}
	public String getStrSrTpNm() {
		return strSrTpNm;
	}
	public void setStrSrTpNm(String strSrTpNm) {
		this.strSrTpNm = strSrTpNm;
	}
	public String getStrCurCustCd() {
		return strCurCustCd;
	}
	public void setStrCurCustCd(String strCurCustCd) {
		this.strCurCustCd = strCurCustCd;
	}
	public String getStrCurCustNm() {
		return strCurCustNm;
	}
	public void setStrCurCustNm(String strCurCustNm) {
		this.strCurCustNm = strCurCustNm;
	}
	public String getStrBllCustCd() {
		return strBllCustCd;
	}
	public void setStrBllCustCd(String strBllCustCd) {
		this.strBllCustCd = strBllCustCd;
	}
	public String getStrBllCustNm() {
		return strBllCustNm;
	}
	public void setStrBllCustNm(String strBllCustNm) {
		this.strBllCustNm = strBllCustNm;
	}
	public String getStrCustPoDt() {
		return strCustPoDt;
	}
	public void setStrCustPoDt(String strCustPoDt) {
		this.strCustPoDt = strCustPoDt;
	}
	public String getStrFsrUpdFlag() {
		return strFsrUpdFlag;
	}
	public void setStrFsrUpdFlag(String strFsrUpdFlag) {
		this.strFsrUpdFlag = strFsrUpdFlag;
	}
	public String getStrBilbleFlg() {
		return strBilbleFlg;
	}
	public void setStrBilbleFlg(String strBilbleFlg) {
		this.strBilbleFlg = strBilbleFlg;
	}
	public String getStrPOReqFlg() {
		return strPOReqFlg;
	}
	public void setStrPOReqFlg(String strPOReqFlg) {
		this.strPOReqFlg = strPOReqFlg;
	}
	public String getStrPOFileName() {
		return strPOFileName;
	}
	public void setStrPOFileName(String strPOFileName) {
		this.strPOFileName = strPOFileName;
	}
	public String getStrPOAttPk() {
		return strPOAttPk;
	}
	public void setStrPOAttPk(String strPOAttPk) {
		this.strPOAttPk = strPOAttPk;
	}
	public String getStrSlsDt() {
		return strSlsDt;
	}
	public void setStrSlsDt(String strSlsDt) {
		this.strSlsDt = strSlsDt;
	}
	public String getStrCCFlag() {
		return strCCFlag;
	}
	public void setStrCCFlag(String strCCFlag) {
		this.strCCFlag = strCCFlag;
	}
	public String getStrProfileId() {
		return strProfileId;
	}
	public void setStrProfileId(String strProfileId) {
		this.strProfileId = strProfileId;
	}
	public String getStrHolderName() {
		return strHolderName;
	}
	public void setStrHolderName(String strHolderName) {
		this.strHolderName = strHolderName;
	}
	public String getStrCardType() {
		return strCardType;
	}
	public void setStrCardType(String strCardType) {
		this.strCardType = strCardType;
	}
	public String getStrExprDt() {
		return strExprDt;
	}
	public void setStrExprDt(String strExprDt) {
		this.strExprDt = strExprDt;
	}
	public String getStrCallSrcTpCd() {
		return strCallSrcTpCd;
	}
	public void setStrCallSrcTpCd(String strCallSrcTpCd) {
		this.strCallSrcTpCd = strCallSrcTpCd;
	}
	public String getStrContActvFlg() {
		return strContActvFlg;
	}
	public void setStrContActvFlg(String strContActvFlg) {
		this.strContActvFlg = strContActvFlg;
	}
	public String getStrAuthAmt() {
		return strAuthAmt;
	}
	public void setStrAuthAmt(String strAuthAmt) {
		this.strAuthAmt = strAuthAmt;
	}
	public String getStrAttribute1() {
		return strAttribute1;
	}
	public void setStrAttribute1(String strAttribute1) {
		this.strAttribute1 = strAttribute1;
	}
	public String getStrAttribute2() {
		return strAttribute2;
	}
	public void setStrAttribute2(String strAttribute2) {
		this.strAttribute2 = strAttribute2;
	}
	public String getStrAttribute3() {
		return strAttribute3;
	}
	public void setStrAttribute3(String strAttribute3) {
		this.strAttribute3 = strAttribute3;
	}
	
	public String getStrVendName() {
		return strVendName;
	}


	public void setStrVendName(String strVendName) {
		this.strVendName = strVendName;
	}


	public String getStrVendContact() {
		return strVendContact;
	}


	public void setStrVendContact(String strVendContact) {
		this.strVendContact = strVendContact;
	}


	public String getStrVendPhone() {
		return strVendPhone;
	}


	public void setStrVendPhone(String strVendPhone) {
		this.strVendPhone = strVendPhone;
	}


	public String getStrVendEmailAddr() {
		return strVendEmailAddr;
	}


	public void setStrVendEmailAddr(String strVendEmailAddr) {
		this.strVendEmailAddr = strVendEmailAddr;
	}


	public String getStrCovidVacMsg() {
		return strCovidVacMsg;
	}


	public void setStrCovidVacMsg(String strCovidVacMsg) {
		this.strCovidVacMsg = strCovidVacMsg;
	}


	@Override
	public String toString() {
		return "CanonE307ServiceReqInfoRec [strFSRNum=" + strFSRNum
				+ ", strITTNum=" + strITTNum + ", strSvcBillTpCd="
				+ strSvcBillTpCd + ", strSvcBillTpNm=" + strSvcBillTpNm
				+ ", strFSRCratDt=" + strFSRCratDt + ", strRspndByDt="
				+ strRspndByDt + ", strLastUptDt=" + strLastUptDt
				+ ", strLastUpdtBy=" + strLastUpdtBy + ", strCreatedBy="
				+ strCreatedBy + ", strMachMngrId=" + strMachMngrId
				+ ", strMachMngrNm=" + strMachMngrNm + ", strPblmTpCd="
				+ strPblmTpCd + ", strPblmTpNm=" + strPblmTpNm
				+ ", strMachStatus=" + strMachStatus + ", strCallSrcTpCd="
				+ strCallSrcTpCd + ", strCallSrcTpNm=" + strCallSrcTpNm
				+ ", strLineBizTpCd=" + strLineBizTpCd + ", strBrDescTxt="
				+ strBrDescTxt + ", strCustPONum=" + strCustPONum
				+ ", strCurAddrLine=" + strCurAddrLine + ", strCurCity="
				+ strCurCity + ", strCurStCd=" + strCurStCd + ", strCurPostCd="
				+ strCurPostCd + ", strCurCtryCd=" + strCurCtryCd
				+ ", strLocDispString=" + strLocDispString
				+ ", strBillAddrLine=" + strBillAddrLine + ", strBillCity="
				+ strBillCity + ", strBillStCd=" + strBillStCd
				+ ", strBillPostCd=" + strBillPostCd + ", strBillCtryCd="
				+ strBillCtryCd + ", strBillToDispString="
				+ strBillToDispString + ", strPmtTerm=" + strPmtTerm
				+ ", strSvcWindowFrom=" + strSvcWindowFrom
				+ ", strSvcWindowTo=" + strSvcWindowTo + ", strETA=" + strETA
				+ ", strFutureServDate=" + strFutureServDate
				+ ", strFutureServTime=" + strFutureServTime + ", strSrSts="
				+ strSrSts + ", strCustCaseNo=" + strCustCaseNo
				+ ", strPoVerFlag=" + strPoVerFlag + ", strSrTpCd=" + strSrTpCd
				+ ", strSrTpNm=" + strSrTpNm + ", strCurCustCd=" + strCurCustCd
				+ ", strCurCustNm=" + strCurCustNm + ", strBllCustCd="
				+ strBllCustCd + ", strBllCustNm=" + strBllCustNm
				+ ", strCustPoDt=" + strCustPoDt + ", strFsrUpdFlag="
				+ strFsrUpdFlag + ", strBilbleFlg=" + strBilbleFlg
				+ ", strPOReqFlg=" + strPOReqFlg + ", strPOFileName="
				+ strPOFileName + ", strPOAttPk=" + strPOAttPk + ", strSlsDt="
				+ strSlsDt + ", strCCFlag=" + strCCFlag + ", strProfileId="
				+ strProfileId + ", strHolderName=" + strHolderName
				+ ", strCardType=" + strCardType + ", strExprDt=" + strExprDt
				+ ", strAuthAmt=" + strAuthAmt + ", strContActvFlg="
				+ strContActvFlg + ", strAttribute1=" + strAttribute1
				+ ", strAttribute2=" + strAttribute2 + ", strAttribute3="
				+ strAttribute3 + ", strVendName=" + strVendName
				+ ", strVendContact=" + strVendContact + ", strVendPhone="
				+ strVendPhone + ", strVendEmailAddr=" + strVendEmailAddr
				+ ", strCovidVacMsg=" + strCovidVacMsg + "]";
	}


}
