package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307TaskHistoryRec implements SQLData{
	private String strSvcTaskNum;
	private String strFsrNum;                       
	private String strTskStsCd;
	private String strTaskCratDt;
	private String strMachMstrPk;
	private String strCustMachCtrlNum;
	private String strSerNum;
	private String strMdlNm;
	private String strMdlGrpNm;
	private String strMdseCd;
	private String strShpToCustCd;
	private String strCustTelNum;
	private String strCustTelExtNum;
	private String strCustAttnTxt;
	private String strCustEmlAdrs;
	private String strCustPoNum;
	private String strCustPoDt;
	private String strDsSvcCallTpCd;
	private String strTskTpeNm;
	private String strBillTpCd;
	private String strPblmSympTpCd;
	private String strTechCd;
	private String strCustAvalFrmHrMn;
	private String strCustAvalToHrMn;
	private String strTskRcvDt;
	private String strTskSchdDt;
	private String strTskCpltDt;
	private String strTskCloDt;
	private String strTskSchdByUsrId;
	private String strTskCloByUsrId;
	private String strRespTm;
	private String strRestoreTm;
	private String strMachDwnFg;
	private String strErlStatTs;
	private String strLtStatTs;
	private String strSvcRgCd;
	private String strSvcBrCd;
	private String strSvcTeamCd;
	private String strBrMgrPsnCd;
	private String strTrtyMgrPsnCd;
	private String strTmMgrPsncd;
	private String strFsrVstNum;
	private String strFsrVstStsCd;
	private String strVstTechCd;
	private String strAssigneeNm;
	private String strTechShdFmDt;
	private String strTechShdToDt;
	private String strTskActlStart;
	private String strTskActlEnd;
	private String strAsgTpCd;
	
	public CanonE307TaskHistoryRec() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307TaskHistoryRec(String strSvcTaskNum, String strFsrNum,
			String strTskStsCd, String strTaskCratDt, String strMachMstrPk,
			String strCustMachCtrlNum, String strSerNum, String strMdlNm,
			String strMdlGrpNm, String strMdseCd, String strShpToCustCd,
			String strCustTelNum, String strCustTelExtNum,
			String strCustAttnTxt, String strCustEmlAdrs, String strCustPoNum,
			String strCustPoDt, String strDsSvcCallTpCd, String strTskTpeNm,
			String strBillTpCd, String strPblmSympTpCd, String strTechCd,
			String strCustAvalFrmHrMn, String strCustAvalToHrMn,
			String strTskRcvDt, String strTskSchdDt, String strTskCpltDt,
			String strTskCloDt, String strTskSchdByUsrId,
			String strTskCloByUsrId, String strRespTm, String strRestoreTm,
			String strMachDwnFg, String strErlStatTs, String strLtStatTs,
			String strSvcRgCd, String strSvcBrCd, String strSvcTeamCd,
			String strBrMgrPsnCd, String strTrtyMgrPsnCd, String strTmMgrPsncd,
			String strFsrVstNum, String strFsrVstStsCd, String strVstTechCd,
			String strAssigneeNm, String strTechShdFmDt, String strTechShdToDt,
			String strTskActlStart, String strTskActlEnd, String strAsgTpCd) {
		super();
		this.strSvcTaskNum = strSvcTaskNum;
		this.strFsrNum = strFsrNum;
		this.strTskStsCd = strTskStsCd;
		this.strTaskCratDt = strTaskCratDt;
		this.strMachMstrPk = strMachMstrPk;
		this.strCustMachCtrlNum = strCustMachCtrlNum;
		this.strSerNum = strSerNum;
		this.strMdlNm = strMdlNm;
		this.strMdlGrpNm = strMdlGrpNm;
		this.strMdseCd = strMdseCd;
		this.strShpToCustCd = strShpToCustCd;
		this.strCustTelNum = strCustTelNum;
		this.strCustTelExtNum = strCustTelExtNum;
		this.strCustAttnTxt = strCustAttnTxt;
		this.strCustEmlAdrs = strCustEmlAdrs;
		this.strCustPoNum = strCustPoNum;
		this.strCustPoDt = strCustPoDt;
		this.strDsSvcCallTpCd = strDsSvcCallTpCd;
		this.strTskTpeNm = strTskTpeNm;
		this.strBillTpCd = strBillTpCd;
		this.strPblmSympTpCd = strPblmSympTpCd;
		this.strTechCd = strTechCd;
		this.strCustAvalFrmHrMn = strCustAvalFrmHrMn;
		this.strCustAvalToHrMn = strCustAvalToHrMn;
		this.strTskRcvDt = strTskRcvDt;
		this.strTskSchdDt = strTskSchdDt;
		this.strTskCpltDt = strTskCpltDt;
		this.strTskCloDt = strTskCloDt;
		this.strTskSchdByUsrId = strTskSchdByUsrId;
		this.strTskCloByUsrId = strTskCloByUsrId;
		this.strRespTm = strRespTm;
		this.strRestoreTm = strRestoreTm;
		this.strMachDwnFg = strMachDwnFg;
		this.strErlStatTs = strErlStatTs;
		this.strLtStatTs = strLtStatTs;
		this.strSvcRgCd = strSvcRgCd;
		this.strSvcBrCd = strSvcBrCd;
		this.strSvcTeamCd = strSvcTeamCd;
		this.strBrMgrPsnCd = strBrMgrPsnCd;
		this.strTrtyMgrPsnCd = strTrtyMgrPsnCd;
		this.strTmMgrPsncd = strTmMgrPsncd;
		this.strFsrVstNum = strFsrVstNum;
		this.strFsrVstStsCd = strFsrVstStsCd;
		this.strVstTechCd = strVstTechCd;
		this.strAssigneeNm = strAssigneeNm;
		this.strTechShdFmDt = strTechShdFmDt;
		this.strTechShdToDt = strTechShdToDt;
		this.strTskActlStart = strTskActlStart;
		this.strTskActlEnd = strTskActlEnd;
		this.strAsgTpCd = strAsgTpCd;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TSK_HIST_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strSvcTaskNum = stream.readString();
		strFsrNum = stream.readString();
		strTskStsCd = stream.readString();
		strTaskCratDt = stream.readString();
		strMachMstrPk = stream.readString();
		strCustMachCtrlNum = stream.readString();
		strSerNum = stream.readString();
		strMdlNm = stream.readString();
		strMdlGrpNm = stream.readString();
		strMdseCd = stream.readString();
		strShpToCustCd = stream.readString();
		strCustTelNum = stream.readString();
		strCustTelExtNum = stream.readString();
		strCustAttnTxt = stream.readString();
		strCustEmlAdrs = stream.readString();
		strCustPoNum = stream.readString();
		strCustPoDt = stream.readString();
		strDsSvcCallTpCd = stream.readString();
		strTskTpeNm = stream.readString();
		strBillTpCd = stream.readString();
		strPblmSympTpCd = stream.readString();
		strTechCd = stream.readString();
		strCustAvalFrmHrMn = stream.readString();
		strCustAvalToHrMn = stream.readString();
		strTskRcvDt = stream.readString();
		strTskSchdDt = stream.readString();
		strTskCpltDt = stream.readString();
		strTskCloDt = stream.readString();
		strTskSchdByUsrId = stream.readString();
		strTskCloByUsrId = stream.readString();
		strRespTm=stream.readString();
		strRestoreTm= stream.readString();
		strMachDwnFg = stream.readString();
		strErlStatTs = stream.readString();
		strLtStatTs = stream.readString();
		strSvcRgCd = stream.readString();
		strSvcBrCd = stream.readString();
		strSvcTeamCd = stream.readString();
		strBrMgrPsnCd = stream.readString();
		strTrtyMgrPsnCd = stream.readString();
		strTmMgrPsncd = stream.readString();
		strFsrVstNum = stream.readString();
		strFsrVstStsCd = stream.readString();
		strVstTechCd = stream.readString();
		strAssigneeNm= stream.readString();
		strTechShdFmDt = stream.readString();
		strTechShdToDt = stream.readString();
		strTskActlStart = stream.readString();
		strTskActlEnd = stream.readString();
		strAsgTpCd = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strSvcTaskNum);
		stream.writeString(strFsrNum);
		stream.writeString(strTskStsCd);
		stream.writeString(strTaskCratDt);
		stream.writeString(strMachMstrPk);
		stream.writeString(strCustMachCtrlNum);
		stream.writeString(strSerNum);
		stream.writeString(strMdlNm);
		stream.writeString(strMdlGrpNm);
		stream.writeString(strMdseCd);
		stream.writeString(strShpToCustCd);
		stream.writeString(strCustTelNum);
		stream.writeString(strCustTelExtNum);
		stream.writeString(strCustAttnTxt);
		stream.writeString(strCustEmlAdrs);
		stream.writeString(strCustPoNum);
		stream.writeString(strCustPoDt);
		stream.writeString(strDsSvcCallTpCd);
		stream.writeString(strTskTpeNm);
		stream.writeString(strBillTpCd);
		stream.writeString(strPblmSympTpCd);
		stream.writeString(strTechCd);
		stream.writeString(strCustAvalFrmHrMn);
		stream.writeString(strCustAvalToHrMn);
		stream.writeString(strTskRcvDt);
		stream.writeString(strTskSchdDt);
		stream.writeString(strTskCpltDt);
		stream.writeString(strTskCloDt);
		stream.writeString(strTskSchdByUsrId);
		stream.writeString(strTskCloByUsrId);
		stream.writeString(strRespTm);
		stream.writeString(strRestoreTm);
		stream.writeString(strMachDwnFg);
		stream.writeString(strErlStatTs);
		stream.writeString(strLtStatTs);
		stream.writeString(strSvcRgCd);
		stream.writeString(strSvcBrCd);
		stream.writeString(strSvcTeamCd);
		stream.writeString(strBrMgrPsnCd);
		stream.writeString(strTrtyMgrPsnCd);
		stream.writeString(strTmMgrPsncd);
		stream.writeString(strFsrVstNum);
		stream.writeString(strFsrVstStsCd);
		stream.writeString(strVstTechCd);
		stream.writeString(strAssigneeNm);
		stream.writeString(strTechShdFmDt);
		stream.writeString(strTechShdToDt);	
		stream.writeString(strTskActlStart);
		stream.writeString(strTskActlEnd);
		stream.writeString(strAsgTpCd);
	}

	public String getStrSvcTaskNum() {
		return strSvcTaskNum;
	}

	public void setStrSvcTaskNum(String strSvcTaskNum) {
		this.strSvcTaskNum = strSvcTaskNum;
	}

	public String getStrFsrNum() {
		return strFsrNum;
	}

	public void setStrFsrNum(String strFsrNum) {
		this.strFsrNum = strFsrNum;
	}

	public String getStrTskStsCd() {
		return strTskStsCd;
	}

	public void setStrTskStsCd(String strTskStsCd) {
		this.strTskStsCd = strTskStsCd;
	}

	public String getStrTaskCratDt() {
		return strTaskCratDt;
	}

	public void setStrTaskCratDt(String strTaskCratDt) {
		this.strTaskCratDt = strTaskCratDt;
	}

	public String getStrMachMstrPk() {
		return strMachMstrPk;
	}

	public void setStrMachMstrPk(String strMachMstrPk) {
		this.strMachMstrPk = strMachMstrPk;
	}

	public String getStrCustMachCtrlNum() {
		return strCustMachCtrlNum;
	}

	public void setStrCustMachCtrlNum(String strCustMachCtrlNum) {
		this.strCustMachCtrlNum = strCustMachCtrlNum;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrMdlNm() {
		return strMdlNm;
	}

	public void setStrMdlNm(String strMdlNm) {
		this.strMdlNm = strMdlNm;
	}

	public String getStrMdlGrpNm() {
		return strMdlGrpNm;
	}

	public void setStrMdlGrpNm(String strMdlGrpNm) {
		this.strMdlGrpNm = strMdlGrpNm;
	}

	public String getStrMdseCd() {
		return strMdseCd;
	}

	public void setStrMdseCd(String strMdseCd) {
		this.strMdseCd = strMdseCd;
	}

	public String getStrShpToCustCd() {
		return strShpToCustCd;
	}

	public void setStrShpToCustCd(String strShpToCustCd) {
		this.strShpToCustCd = strShpToCustCd;
	}

	public String getStrCustTelNum() {
		return strCustTelNum;
	}

	public void setStrCustTelNum(String strCustTelNum) {
		this.strCustTelNum = strCustTelNum;
	}

	public String getStrCustTelExtNum() {
		return strCustTelExtNum;
	}

	public void setStrCustTelExtNum(String strCustTelExtNum) {
		this.strCustTelExtNum = strCustTelExtNum;
	}

	public String getStrCustAttnTxt() {
		return strCustAttnTxt;
	}

	public void setStrCustAttnTxt(String strCustAttnTxt) {
		this.strCustAttnTxt = strCustAttnTxt;
	}

	public String getStrCustEmlAdrs() {
		return strCustEmlAdrs;
	}

	public void setStrCustEmlAdrs(String strCustEmlAdrs) {
		this.strCustEmlAdrs = strCustEmlAdrs;
	}

	public String getStrCustPoNum() {
		return strCustPoNum;
	}

	public void setStrCustPoNum(String strCustPoNum) {
		this.strCustPoNum = strCustPoNum;
	}

	public String getStrCustPoDt() {
		return strCustPoDt;
	}

	public void setStrCustPoDt(String strCustPoDt) {
		this.strCustPoDt = strCustPoDt;
	}

	public String getStrDsSvcCallTpCd() {
		return strDsSvcCallTpCd;
	}

	public void setStrDsSvcCallTpCd(String strDsSvcCallTpCd) {
		this.strDsSvcCallTpCd = strDsSvcCallTpCd;
	}

	public String getStrBillTpCd() {
		return strBillTpCd;
	}

	public void setStrBillTpCd(String strBillTpCd) {
		this.strBillTpCd = strBillTpCd;
	}

	public String getStrPblmSympTpCd() {
		return strPblmSympTpCd;
	}

	public void setStrPblmSympTpCd(String strPblmSympTpCd) {
		this.strPblmSympTpCd = strPblmSympTpCd;
	}

	public String getStrTechCd() {
		return strTechCd;
	}

	public void setStrTechCd(String strTechCd) {
		this.strTechCd = strTechCd;
	}

	public String getStrCustAvalFrmHrMn() {
		return strCustAvalFrmHrMn;
	}

	public void setStrCustAvalFrmHrMn(String strCustAvalFrmHrMn) {
		this.strCustAvalFrmHrMn = strCustAvalFrmHrMn;
	}

	public String getStrCustAvalToHrMn() {
		return strCustAvalToHrMn;
	}

	public void setStrCustAvalToHrMn(String strCustAvalToHrMn) {
		this.strCustAvalToHrMn = strCustAvalToHrMn;
	}

	public String getStrTskRcvDt() {
		return strTskRcvDt;
	}

	public void setStrTskRcvDt(String strTskRcvDt) {
		this.strTskRcvDt = strTskRcvDt;
	}

	public String getStrTskSchdDt() {
		return strTskSchdDt;
	}

	public void setStrTskSchdDt(String strTskSchdDt) {
		this.strTskSchdDt = strTskSchdDt;
	}

	public String getStrTskCpltDt() {
		return strTskCpltDt;
	}

	public void setStrTskCpltDt(String strTskCpltDt) {
		this.strTskCpltDt = strTskCpltDt;
	}

	public String getStrTskCloDt() {
		return strTskCloDt;
	}

	public void setStrTskCloDt(String strTskCloDt) {
		this.strTskCloDt = strTskCloDt;
	}

	public String getStrTskSchdByUsrId() {
		return strTskSchdByUsrId;
	}

	public void setStrTskSchdByUsrId(String strTskSchdByUsrId) {
		this.strTskSchdByUsrId = strTskSchdByUsrId;
	}

	public String getStrTskCloByUsrId() {
		return strTskCloByUsrId;
	}

	public void setStrTskCloByUsrId(String strTskCloByUsrId) {
		this.strTskCloByUsrId = strTskCloByUsrId;
	}

	public String getStrMachDwnFg() {
		return strMachDwnFg;
	}

	public void setStrMachDwnFg(String strMachDwnFg) {
		this.strMachDwnFg = strMachDwnFg;
	}

	public String getStrErlStatTs() {
		return strErlStatTs;
	}

	public void setStrErlStatTs(String strErlStatTs) {
		this.strErlStatTs = strErlStatTs;
	}

	public String getStrLtStatTs() {
		return strLtStatTs;
	}

	public void setStrLtStatTs(String strLtStatTs) {
		this.strLtStatTs = strLtStatTs;
	}

	public String getStrSvcRgCd() {
		return strSvcRgCd;
	}

	public void setStrSvcRgCd(String strSvcRgCd) {
		this.strSvcRgCd = strSvcRgCd;
	}

	public String getStrSvcBrCd() {
		return strSvcBrCd;
	}

	public void setStrSvcBrCd(String strSvcBrCd) {
		this.strSvcBrCd = strSvcBrCd;
	}

	public String getStrSvcTeamCd() {
		return strSvcTeamCd;
	}

	public void setStrSvcTeamCd(String strSvcTeamCd) {
		this.strSvcTeamCd = strSvcTeamCd;
	}

	public String getStrBrMgrPsnCd() {
		return strBrMgrPsnCd;
	}

	public void setStrBrMgrPsnCd(String strBrMgrPsnCd) {
		this.strBrMgrPsnCd = strBrMgrPsnCd;
	}

	public String getStrTrtyMgrPsnCd() {
		return strTrtyMgrPsnCd;
	}

	public void setStrTrtyMgrPsnCd(String strTrtyMgrPsnCd) {
		this.strTrtyMgrPsnCd = strTrtyMgrPsnCd;
	}

	public String getStrTmMgrPsncd() {
		return strTmMgrPsncd;
	}

	public void setStrTmMgrPsncd(String strTmMgrPsncd) {
		this.strTmMgrPsncd = strTmMgrPsncd;
	}

	public String getStrFsrVstNum() {
		return strFsrVstNum;
	}

	public void setStrFsrVstNum(String strFsrVstNum) {
		this.strFsrVstNum = strFsrVstNum;
	}

	public String getStrFsrVstStsCd() {
		return strFsrVstStsCd;
	}

	public void setStrFsrVstStsCd(String strFsrVstStsCd) {
		this.strFsrVstStsCd = strFsrVstStsCd;
	}

	public String getStrVstTechCd() {
		return strVstTechCd;
	}

	public void setStrVstTechCd(String strVstTechCd) {
		this.strVstTechCd = strVstTechCd;
	}

	public String getStrTechShdFmDt() {
		return strTechShdFmDt;
	}

	public void setStrTechShdFmDt(String strTechShdFmDt) {
		this.strTechShdFmDt = strTechShdFmDt;
	}

	public String getStrTechShdToDt() {
		return strTechShdToDt;
	}

	public void setStrTechShdToDt(String strTechShdToDt) {
		this.strTechShdToDt = strTechShdToDt;
	}

	public String getStrAsgTpCd() {
		return strAsgTpCd;
	}

	public void setStrAsgTpCd(String strAsgTpCd) {
		this.strAsgTpCd = strAsgTpCd;
	}
	
	public String getStrRespTm() {
		return strRespTm;
	}

	public void setStrRespTm(String strRespTm) {
		this.strRespTm = strRespTm;
	}

	public String getStrRestoreTm() {
		return strRestoreTm;
	}

	public void setStrRestoreTm(String strRestoreTm) {
		this.strRestoreTm = strRestoreTm;
	}

	public String getStrAssigneeNm() {
		return strAssigneeNm;
	}

	public void setStrAssigneeNm(String strAssigneeNm) {
		this.strAssigneeNm = strAssigneeNm;
	}

	public String getStrTskTpeNm() {
		return strTskTpeNm;
	}

	public void setStrTskTpeNm(String strTskTpeNm) {
		this.strTskTpeNm = strTskTpeNm;
	}


	public String getStrTskActlStart() {
		return strTskActlStart;
	}


	public void setStrTskActlStart(String strTskActlStart) {
		this.strTskActlStart = strTskActlStart;
	}

	public String getStrTskActlEnd() {
		return strTskActlEnd;
	}

	public void setStrTskActlEnd(String strTskActlEnd) {
		this.strTskActlEnd = strTskActlEnd;
	}
}
