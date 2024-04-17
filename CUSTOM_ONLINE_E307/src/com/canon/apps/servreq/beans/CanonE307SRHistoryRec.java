package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRHistoryRec implements SQLData{
	
	private String strFSR;
	private String strFSRStsCd;
	private String strFSRSts;
	private String strFsrType;	
	private String strMachMstrPk;
	private String strSerNum;
	private String strMachCtrlNum;
	private String strSvcSlnNm;
	private String strMdlNm;
	private String strTechCd;
	private String strCreationDt;
	private String strFsrCpltDt;
	private String strBillToCustCd;
	private String strSellToCustCd;	
	private String strShipToCustCd;
	private String strOwnerAcctNum;
	private String strCurLocAcctNum;
	private String strCustName;
	private String strPmtTrmDiscCd;
	private String strInstStsUpdFlg;
	private String strSvcCallSrcTpCd;
	private String strSvcPblmTpCd;
	private String strPblmTpNm;
	private String strSvcCallAvdcd;
	private String strRqstOwrTocCd;
	private String strOwnerNm;
	private String strIncidentDt;
	private String strPoVerFlg;
	private String strCustCseNum;
	private String strIttNum;
	private String strBilCustUpdFlg;
	private String strShpCustUpdFlg;
	private String strBilUpdCustCd;
	private String strShpUpdCustCd;
	private String strBilCustActCd;
	private String strShpCustActCd;
	private String strFsrTpCd;
	private String strFsrCloDt;
	private String strLastMtrRd;
	private String strBranch;
	private String strDispatcher;
	private String strPostalCd;
	private String strCntryCd;


	public CanonE307SRHistoryRec() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307SRHistoryRec(String strFSR, String strFSRStsCd,
			String strFSRSts, String strFsrType, String strMachMstrPk,
			String strSerNum, String strMachCtrlNum, String strSvcSlnNm,
			String strMdlNm, String strTechCd, String strCreationDt,
			String strFsrCpltDt, String strBillToCustCd,
			String strSellToCustCd, String strShipToCustCd,
			String strOwnerAcctNum, String strCurLocAcctNum,
			String strCustName, String strPmtTrmDiscCd,
			String strInstStsUpdFlg, String strSvcCallSrcTpCd,
			String strSvcPblmTpCd, String strPblmTpNm, String strSvcCallAvdcd,
			String strRqstOwrTocCd, String strOwnerNm, String strIncidentDt,
			String strPoVerFlg, String strCustCseNum, String strIttNum,
			String strBilCustUpdFlg, String strShpCustUpdFlg,
			String strBilUpdCustCd, String strShpUpdCustCd,
			String strBilCustActCd, String strShpCustActCd, String strFsrTpCd,
			String strFsrCloDt, String strLastMtrRd, String strBranch,
			String strDispatcher, String strPostalCd, String strCntryCd) {
		super();
		this.strFSR = strFSR;
		this.strFSRStsCd = strFSRStsCd;
		this.strFSRSts = strFSRSts;
		this.strFsrType = strFsrType;
		this.strMachMstrPk = strMachMstrPk;
		this.strSerNum = strSerNum;
		this.strMachCtrlNum = strMachCtrlNum;
		this.strSvcSlnNm = strSvcSlnNm;
		this.strMdlNm = strMdlNm;
		this.strTechCd = strTechCd;
		this.strCreationDt = strCreationDt;
		this.strFsrCpltDt = strFsrCpltDt;
		this.strBillToCustCd = strBillToCustCd;
		this.strSellToCustCd = strSellToCustCd;
		this.strShipToCustCd = strShipToCustCd;
		this.strOwnerAcctNum = strOwnerAcctNum;
		this.strCurLocAcctNum = strCurLocAcctNum;
		this.strCustName = strCustName;
		this.strPmtTrmDiscCd = strPmtTrmDiscCd;
		this.strInstStsUpdFlg = strInstStsUpdFlg;
		this.strSvcCallSrcTpCd = strSvcCallSrcTpCd;
		this.strSvcPblmTpCd = strSvcPblmTpCd;
		this.strPblmTpNm = strPblmTpNm;
		this.strSvcCallAvdcd = strSvcCallAvdcd;
		this.strRqstOwrTocCd = strRqstOwrTocCd;
		this.strOwnerNm = strOwnerNm;
		this.strIncidentDt = strIncidentDt;
		this.strPoVerFlg = strPoVerFlg;
		this.strCustCseNum = strCustCseNum;
		this.strIttNum = strIttNum;
		this.strBilCustUpdFlg = strBilCustUpdFlg;
		this.strShpCustUpdFlg = strShpCustUpdFlg;
		this.strBilUpdCustCd = strBilUpdCustCd;
		this.strShpUpdCustCd = strShpUpdCustCd;
		this.strBilCustActCd = strBilCustActCd;
		this.strShpCustActCd = strShpCustActCd;
		this.strFsrTpCd = strFsrTpCd;
		this.strFsrCloDt = strFsrCloDt;
		this.strLastMtrRd = strLastMtrRd;
		this.strBranch = strBranch;
		this.strDispatcher = strDispatcher;
		this.strPostalCd = strPostalCd;
		this.strCntryCd = strCntryCd;
	}


	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HIST_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFSR = stream.readString();
		strFSRStsCd = stream.readString();
		strFSRSts=stream.readNString();
		strFsrType = stream.readString();
		strMachMstrPk = stream.readString();
		strSerNum = stream.readString();
		strMachCtrlNum = stream.readString();
		strSvcSlnNm = stream.readString();
		strMdlNm = stream.readString();
		strTechCd = stream.readString();
		strCreationDt = stream.readString();
		strFsrCpltDt = stream.readString();
		strBillToCustCd = stream.readString();
		strSellToCustCd = stream.readString();
		strShipToCustCd = stream.readString();
		strOwnerAcctNum = stream.readString();
		strCurLocAcctNum = stream.readString();
		strCustName = stream.readString();
		strPmtTrmDiscCd = stream.readString();
		strInstStsUpdFlg = stream.readString();
		strSvcCallSrcTpCd = stream.readString();
		strSvcPblmTpCd = stream.readString();
		strPblmTpNm = stream.readString();
		strSvcCallAvdcd = stream.readString();
		strRqstOwrTocCd = stream.readString();
		strOwnerNm = stream.readString();
		strIncidentDt = stream.readString();
		strPoVerFlg = stream.readString();
		strCustCseNum = stream.readString();
		strIttNum = stream.readString();
		strBilCustUpdFlg = stream.readString();
		strShpCustUpdFlg = stream.readString();
		strBilUpdCustCd = stream.readString();
		strShpUpdCustCd = stream.readString();
		strBilCustActCd = stream.readString();
		strShpCustActCd = stream.readString();
		strFsrTpCd = stream.readString();
		strFsrCloDt = stream.readString();
		strLastMtrRd = stream.readString();
		strBranch = stream.readString();
		strDispatcher = stream.readString();
		strPostalCd = stream.readString();
		strCntryCd = stream.readString();
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFSR);
		stream.writeString(strFSRStsCd);
		stream.writeString(strFSRSts);
		stream.writeString(strFsrType);
		stream.writeString(strMachMstrPk);
		stream.writeString(strSerNum);
		stream.writeString(strMachCtrlNum);
		stream.writeString(strSvcSlnNm);
		stream.writeString(strMdlNm);
		stream.writeString(strTechCd);
		stream.writeString(strCreationDt);
		stream.writeString(strFsrCpltDt);
		stream.writeString(strBillToCustCd);
		stream.writeString(strSellToCustCd);
		stream.writeString(strShipToCustCd);
		stream.writeString(strOwnerAcctNum);
		stream.writeString(strCurLocAcctNum);
		stream.writeString(strCustName);
		stream.writeString(strPmtTrmDiscCd);
		stream.writeString(strInstStsUpdFlg);
		stream.writeString(strSvcCallSrcTpCd);
		stream.writeString(strSvcPblmTpCd);
		stream.writeString(strPblmTpNm);
		stream.writeString(strSvcCallAvdcd);
		stream.writeString(strRqstOwrTocCd);
		stream.writeString(strOwnerNm);
		stream.writeString(strIncidentDt);
		stream.writeString(strPoVerFlg);
		stream.writeString(strCustCseNum);
		stream.writeString(strIttNum);
		stream.writeString(strBilCustUpdFlg);
		stream.writeString(strShpCustUpdFlg);
		stream.writeString(strBilUpdCustCd);
		stream.writeString(strShpUpdCustCd);
		stream.writeString(strBilCustActCd);
		stream.writeString(strShpCustActCd);
		stream.writeString(strFsrTpCd);
		stream.writeString(strFsrCloDt);
		stream.writeString(strLastMtrRd);
		stream.writeString(strBranch);
		stream.writeString(strDispatcher);
		stream.writeString(strPostalCd);
		stream.writeString(strCntryCd);
	}

	public String getStrFSR() {
		return strFSR;
	}

	public void setStrFSR(String strFSR) {
		this.strFSR = strFSR;
	}

	public String getStrFSRStsCd() {
		return strFSRStsCd;
	}

	public void setStrFSRStsCd(String strFSRStsCd) {
		this.strFSRStsCd = strFSRStsCd;
	}

	public String getStrFsrType() {
		return strFsrType;
	}

	public void setStrFsrType(String strFsrType) {
		this.strFsrType = strFsrType;
	}

	public String getStrMachMstrPk() {
		return strMachMstrPk;
	}

	public void setStrMachMstrPk(String strMachMstrPk) {
		this.strMachMstrPk = strMachMstrPk;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrMachCtrlNum() {
		return strMachCtrlNum;
	}

	public void setStrMachCtrlNum(String strMachCtrlNum) {
		this.strMachCtrlNum = strMachCtrlNum;
	}

	public String getStrSvcSlnNm() {
		return strSvcSlnNm;
	}

	public void setStrSvcSlnNm(String strSvcSlnNm) {
		this.strSvcSlnNm = strSvcSlnNm;
	}

	public String getStrMdlNm() {
		return strMdlNm;
	}

	public void setStrMdlNm(String strMdlNm) {
		this.strMdlNm = strMdlNm;
	}

	public String getStrTechCd() {
		return strTechCd;
	}

	public void setStrTechCd(String strTechCd) {
		this.strTechCd = strTechCd;
	}

	public String getStrCreationDt() {
		return strCreationDt;
	}

	public void setStrCreationDt(String strCreationDt) {
		this.strCreationDt = strCreationDt;
	}

	public String getStrFsrCpltDt() {
		return strFsrCpltDt;
	}

	public void setStrFsrCpltDt(String strFsrCpltDt) {
		this.strFsrCpltDt = strFsrCpltDt;
	}

	public String getStrBillToCustCd() {
		return strBillToCustCd;
	}

	public void setStrBillToCustCd(String strBillToCustCd) {
		this.strBillToCustCd = strBillToCustCd;
	}

	public String getStrSellToCustCd() {
		return strSellToCustCd;
	}

	public void setStrSellToCustCd(String strSellToCustCd) {
		this.strSellToCustCd = strSellToCustCd;
	}

	public String getStrShipToCustCd() {
		return strShipToCustCd;
	}

	public void setStrShipToCustCd(String strShipToCustCd) {
		this.strShipToCustCd = strShipToCustCd;
	}

	public String getStrOwnerAcctNum() {
		return strOwnerAcctNum;
	}

	public void setStrOwnerAcctNum(String strOwnerAcctNum) {
		this.strOwnerAcctNum = strOwnerAcctNum;
	}

	public String getStrCurLocAcctNum() {
		return strCurLocAcctNum;
	}

	public void setStrCurLocAcctNum(String strCurLocAcctNum) {
		this.strCurLocAcctNum = strCurLocAcctNum;
	}

	public String getStrCustName() {
		return strCustName;
	}

	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
	}

	public String getStrPmtTrmDiscCd() {
		return strPmtTrmDiscCd;
	}

	public void setStrPmtTrmDiscCd(String strPmtTrmDiscCd) {
		this.strPmtTrmDiscCd = strPmtTrmDiscCd;
	}

	public String getStrInstStsUpdFlg() {
		return strInstStsUpdFlg;
	}

	public void setStrInstStsUpdFlg(String strInstStsUpdFlg) {
		this.strInstStsUpdFlg = strInstStsUpdFlg;
	}

	public String getStrSvcCallSrcTpCd() {
		return strSvcCallSrcTpCd;
	}

	public void setStrSvcCallSrcTpCd(String strSvcCallSrcTpCd) {
		this.strSvcCallSrcTpCd = strSvcCallSrcTpCd;
	}

	public String getStrSvcPblmTpCd() {
		return strSvcPblmTpCd;
	}

	public void setStrSvcPblmTpCd(String strSvcPblmTpCd) {
		this.strSvcPblmTpCd = strSvcPblmTpCd;
	}

	public String getStrSvcCallAvdcd() {
		return strSvcCallAvdcd;
	}

	public void setStrSvcCallAvdcd(String strSvcCallAvdcd) {
		this.strSvcCallAvdcd = strSvcCallAvdcd;
	}

	public String getStrRqstOwrTocCd() {
		return strRqstOwrTocCd;
	}

	public void setStrRqstOwrTocCd(String strRqstOwrTocCd) {
		this.strRqstOwrTocCd = strRqstOwrTocCd;
	}

	public String getStrIncidentDt() {
		return strIncidentDt;
	}

	public void setStrIncidentDt(String strIncidentDt) {
		this.strIncidentDt = strIncidentDt;
	}

	public String getStrPoVerFlg() {
		return strPoVerFlg;
	}

	public void setStrPoVerFlg(String strPoVerFlg) {
		this.strPoVerFlg = strPoVerFlg;
	}

	public String getStrCustCseNum() {
		return strCustCseNum;
	}

	public void setStrCustCseNum(String strCustCseNum) {
		this.strCustCseNum = strCustCseNum;
	}

	public String getStrIttNum() {
		return strIttNum;
	}

	public void setStrIttNum(String strIttNum) {
		this.strIttNum = strIttNum;
	}

	public String getStrBilCustUpdFlg() {
		return strBilCustUpdFlg;
	}

	public void setStrBilCustUpdFlg(String strBilCustUpdFlg) {
		this.strBilCustUpdFlg = strBilCustUpdFlg;
	}

	public String getStrShpCustUpdFlg() {
		return strShpCustUpdFlg;
	}

	public void setStrShpCustUpdFlg(String strShpCustUpdFlg) {
		this.strShpCustUpdFlg = strShpCustUpdFlg;
	}

	public String getStrBilUpdCustCd() {
		return strBilUpdCustCd;
	}

	public void setStrBilUpdCustCd(String strBilUpdCustCd) {
		this.strBilUpdCustCd = strBilUpdCustCd;
	}

	public String getStrShpUpdCustCd() {
		return strShpUpdCustCd;
	}

	public void setStrShpUpdCustCd(String strShpUpdCustCd) {
		this.strShpUpdCustCd = strShpUpdCustCd;
	}

	public String getStrBilCustActCd() {
		return strBilCustActCd;
	}

	public void setStrBilCustActCd(String strBilCustActCd) {
		this.strBilCustActCd = strBilCustActCd;
	}

	public String getStrShpCustActCd() {
		return strShpCustActCd;
	}

	public void setStrShpCustActCd(String strShpCustActCd) {
		this.strShpCustActCd = strShpCustActCd;
	}

	public String getStrFsrTpCd() {
		return strFsrTpCd;
	}

	public void setStrFsrTpCd(String strFsrTpCd) {
		this.strFsrTpCd = strFsrTpCd;
	}

	public String getStrFsrCloDt() {
		return strFsrCloDt;
	}

	public void setStrFsrCloDt(String strFsrCloDt) {
		this.strFsrCloDt = strFsrCloDt;
	}

	public String getStrLastMtrRd() {
		return strLastMtrRd;
	}

	public void setStrLastMtrRd(String strLastMtrRd) {
		this.strLastMtrRd = strLastMtrRd;
	}

	public String getStrBranch() {
		return strBranch;
	}

	public void setStrBranch(String strBranch) {
		this.strBranch = strBranch;
	}

	public String getStrDispatcher() {
		return strDispatcher;
	}

	public void setStrDispatcher(String strDispatcher) {
		this.strDispatcher = strDispatcher;
	}

	public String getStrPblmTpNm() {
		return strPblmTpNm;
	}

	public void setStrPblmTpNm(String strPblmTpNm) {
		this.strPblmTpNm = strPblmTpNm;
	}

	public String getStrOwnerNm() {
		return strOwnerNm;
	}

	public void setStrOwnerNm(String strOwnerNm) {
		this.strOwnerNm = strOwnerNm;
	}

	public String getStrFSRSts() {
		return strFSRSts;
	}

	public void setStrFSRSts(String strFSRSts) {
		this.strFSRSts = strFSRSts;
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
	
}
