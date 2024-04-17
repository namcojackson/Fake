package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServReqChargesHdrRec implements SQLData{
	
	private String strFsrNum;
	private String strSerNum;
	private String strChrgNum;
	private int chrgTotal;
	private String strInvcNum;
	private String strInvcDate;
	private String strInvcSts;
	private int invAmt;
	private String strInvCurncy;
	private String strTskNum;
	private String strModel;
	private String strContractNum;
	private String strCovrgTpe;
	private String strBilCustCd;
	private String strBilCustNm;
	private String strBilAddr1;
	private String strBilAddr2;
	private String strBilCity;
	private String strBilState;
	private String strBilZipCd;
	private String strShpCustCd;
	private String strShpCustNm;
	private String strShpAddr1;
	private String strShpAddr2;
	private String strShpCity;
	private String strShpState;
	private String strShpZipCd;
	private String svcMachMstrPk;
	private String strFsrVstNum;
	private String strFsrStsCd;
	private String strUpdFlg;
	private String strProfileId;
	private String strHldrNm;
	private String strCardTp;
	private String strExprDt;
	private String strAuthAmt;
	private String strAttr1;
	private String strAttr2;
	private String strAttr3;
	private String strTaskTypeCd;
	private String strAhsFlg;
	private String strLnBzTpCd;
	private String strCovTm;
	private String strOnlnValdFlg;
	

	public CanonE307ServReqChargesHdrRec(String strFsrNum, String strSerNum,
			String strChrgNum, int chrgTotal, String strInvcNum,
			String strInvcDate, String strInvcSts, int invAmt,
			String strInvCurncy, String strTskNum, String strModel,
			String strContractNum, String strCovrgTpe, String strBilCustCd,
			String strBilCustNm, String strBilAddr1, String strBilAddr2,
			String strBilCity, String strBilState, String strBilZipCd,
			String strShpCustCd, String strShpCustNm, String strShpAddr1,
			String strShpAddr2, String strShpCity, String strShpState,
			String strShpZipCd, String svcMachMstrPk, String strFsrVstNum,
			String strFsrStsCd, String strUpdFlg, String strProfileId,
			String strHldrNm, String strCardTp, String strExprDt,
			String strAuthAmt, String strAttr1, String strAttr2,
			String strAttr3, String strTaskTypeCd, String strAhsFlg,
			String strLnBzTpCd, String strCovTm, String strOnlnValdFlg) {
		super();
		this.strFsrNum = strFsrNum;
		this.strSerNum = strSerNum;
		this.strChrgNum = strChrgNum;
		this.chrgTotal = chrgTotal;
		this.strInvcNum = strInvcNum;
		this.strInvcDate = strInvcDate;
		this.strInvcSts = strInvcSts;
		this.invAmt = invAmt;
		this.strInvCurncy = strInvCurncy;
		this.strTskNum = strTskNum;
		this.strModel = strModel;
		this.strContractNum = strContractNum;
		this.strCovrgTpe = strCovrgTpe;
		this.strBilCustCd = strBilCustCd;
		this.strBilCustNm = strBilCustNm;
		this.strBilAddr1 = strBilAddr1;
		this.strBilAddr2 = strBilAddr2;
		this.strBilCity = strBilCity;
		this.strBilState = strBilState;
		this.strBilZipCd = strBilZipCd;
		this.strShpCustCd = strShpCustCd;
		this.strShpCustNm = strShpCustNm;
		this.strShpAddr1 = strShpAddr1;
		this.strShpAddr2 = strShpAddr2;
		this.strShpCity = strShpCity;
		this.strShpState = strShpState;
		this.strShpZipCd = strShpZipCd;
		this.svcMachMstrPk = svcMachMstrPk;
		this.strFsrVstNum = strFsrVstNum;
		this.strFsrStsCd = strFsrStsCd;
		this.strUpdFlg = strUpdFlg;
		this.strProfileId = strProfileId;
		this.strHldrNm = strHldrNm;
		this.strCardTp = strCardTp;
		this.strExprDt = strExprDt;
		this.strAuthAmt = strAuthAmt;
		this.strAttr1 = strAttr1;
		this.strAttr2 = strAttr2;
		this.strAttr3 = strAttr3;
		this.strTaskTypeCd = strTaskTypeCd;
		this.strAhsFlg = strAhsFlg;
		this.strLnBzTpCd = strLnBzTpCd;
		this.strCovTm = strCovTm;
		this.strOnlnValdFlg = strOnlnValdFlg;
	}

	public CanonE307ServReqChargesHdrRec() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_HDR_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFsrNum = stream.readString();
		strSerNum = stream.readString();
		strChrgNum = stream.readString();
		chrgTotal = stream.readInt();
		strInvcNum = stream.readString();
		strInvcDate = stream.readString();
		strInvcSts = stream.readString();;
		invAmt = stream.readInt();
		strInvCurncy = stream.readString();
		strTskNum = stream.readString();
		strModel = stream.readString();
		strContractNum = stream.readString();
		strCovrgTpe = stream.readString();
		strBilCustCd = stream.readString();
		strBilCustNm = stream.readString();
		strBilAddr1 = stream.readString();
		strBilAddr2 = stream.readString();
		strBilCity = stream.readString();
		strBilState = stream.readString();
		strBilZipCd = stream.readString();
		strShpCustCd = stream.readString();
		strShpCustNm = stream.readString();
		strShpAddr1 = stream.readString();
		strShpAddr2 = stream.readString();
		strShpCity = stream.readString();
		strShpState = stream.readString();
		strShpZipCd = stream.readString();
		svcMachMstrPk = stream.readString();
		strFsrVstNum = stream.readString();
		strFsrStsCd = stream.readString();
		strUpdFlg = stream.readString();
		strProfileId = stream.readString();
		strHldrNm = stream.readString();
		strCardTp = stream.readString();
		strExprDt = stream.readString();
		strAuthAmt = stream.readString();
		strAttr1 = stream.readString();
		strAttr2 = stream.readString();
		strAttr3 = stream.readString();
		strTaskTypeCd = stream.readString();
		strAhsFlg = stream.readString();
		strLnBzTpCd= stream.readString();
		strCovTm = stream.readString();
		strOnlnValdFlg = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFsrNum);
		stream.writeString(strSerNum);
		stream.writeString(strChrgNum);
		stream.writeInt(chrgTotal);
		stream.writeString(strInvcNum);
		stream.writeString(strInvcDate);
		stream.writeString(strInvcSts);
		stream.writeInt(invAmt);
		stream.writeString(strInvCurncy);
		stream.writeString(strTskNum);
		stream.writeString(strModel);
		stream.writeString(strContractNum);
		stream.writeString(strCovrgTpe);
		stream.writeString(strBilCustCd);
		stream.writeString(strBilCustNm);
		stream.writeString(strBilAddr1);
		stream.writeString(strBilAddr2);
		stream.writeString(strBilCity);
		stream.writeString(strBilState);
		stream.writeString(strBilZipCd);
		stream.writeString(strShpCustCd);
		stream.writeString(strShpCustNm);
		stream.writeString(strShpAddr1);
		stream.writeString(strShpAddr2);
		stream.writeString(strShpCity);
		stream.writeString(strShpState);
		stream.writeString(strShpZipCd);
		stream.writeString(svcMachMstrPk);
		stream.writeString(strFsrVstNum);
		stream.writeString(strFsrStsCd);
		stream.writeString(strUpdFlg);
		stream.writeString(strProfileId);
		stream.writeString(strHldrNm);
		stream.writeString(strCardTp);
		stream.writeString(strExprDt);
		stream.writeString(strAuthAmt);
		stream.writeString(strAttr1);
		stream.writeString(strAttr2);
		stream.writeString(strAttr3);
		stream.writeString(strTaskTypeCd);
		stream.writeString(strAhsFlg);
		stream.writeString(strLnBzTpCd);
		stream.writeString(strCovTm);
		stream.writeString(strOnlnValdFlg);
	}

	public String getStrFsrNum() {
		return strFsrNum;
	}

	public void setStrFsrNum(String strFsrNum) {
		this.strFsrNum = strFsrNum;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrChrgNum() {
		return strChrgNum;
	}

	public void setStrChrgNum(String strChrgNum) {
		this.strChrgNum = strChrgNum;
	}

	public int getChrgTotal() {
		return chrgTotal;
	}

	public void setChrgTotal(int chrgTotal) {
		this.chrgTotal = chrgTotal;
	}

	public String getStrInvcNum() {
		return strInvcNum;
	}

	public void setStrInvcNum(String strInvcNum) {
		this.strInvcNum = strInvcNum;
	}

	public String getStrInvcDate() {
		return strInvcDate;
	}

	public void setStrInvcDate(String strInvcDate) {
		this.strInvcDate = strInvcDate;
	}

	public String getStrInvcSts() {
		return strInvcSts;
	}

	public void setStrInvcSts(String strInvcSts) {
		this.strInvcSts = strInvcSts;
	}

	public int getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(int invAmt) {
		this.invAmt = invAmt;
	}

	public String getStrInvCurncy() {
		return strInvCurncy;
	}

	public void setStrInvCurncy(String strInvCurncy) {
		this.strInvCurncy = strInvCurncy;
	}

	public String getStrTskNum() {
		return strTskNum;
	}

	public void setStrTskNum(String strTskNum) {
		this.strTskNum = strTskNum;
	}

	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrContractNum() {
		return strContractNum;
	}

	public void setStrContractNum(String strContractNum) {
		this.strContractNum = strContractNum;
	}

	public String getStrCovrgTpe() {
		return strCovrgTpe;
	}

	public void setStrCovrgTpe(String strCovrgTpe) {
		this.strCovrgTpe = strCovrgTpe;
	}

	public String getStrBilCustCd() {
		return strBilCustCd;
	}

	public void setStrBilCustCd(String strBilCustCd) {
		this.strBilCustCd = strBilCustCd;
	}

	public String getStrBilCustNm() {
		return strBilCustNm;
	}

	public void setStrBilCustNm(String strBilCustNm) {
		this.strBilCustNm = strBilCustNm;
	}

	public String getStrBilAddr1() {
		return strBilAddr1;
	}

	public void setStrBilAddr1(String strBilAddr1) {
		this.strBilAddr1 = strBilAddr1;
	}

	public String getStrBilAddr2() {
		return strBilAddr2;
	}

	public void setStrBilAddr2(String strBilAddr2) {
		this.strBilAddr2 = strBilAddr2;
	}

	public String getStrBilCity() {
		return strBilCity;
	}

	public void setStrBilCity(String strBilCity) {
		this.strBilCity = strBilCity;
	}

	public String getStrBilState() {
		return strBilState;
	}

	public void setStrBilState(String strBilState) {
		this.strBilState = strBilState;
	}

	public String getStrBilZipCd() {
		return strBilZipCd;
	}

	public void setStrBilZipCd(String strBilZipCd) {
		this.strBilZipCd = strBilZipCd;
	}

	public String getStrShpCustCd() {
		return strShpCustCd;
	}

	public void setStrShpCustCd(String strShpCustCd) {
		this.strShpCustCd = strShpCustCd;
	}

	public String getStrShpCustNm() {
		return strShpCustNm;
	}

	public void setStrShpCustNm(String strShpCustNm) {
		this.strShpCustNm = strShpCustNm;
	}

	public String getStrShpAddr1() {
		return strShpAddr1;
	}

	public void setStrShpAddr1(String strShpAddr1) {
		this.strShpAddr1 = strShpAddr1;
	}

	public String getStrShpAddr2() {
		return strShpAddr2;
	}

	public void setStrShpAddr2(String strShpAddr2) {
		this.strShpAddr2 = strShpAddr2;
	}

	public String getStrShpCity() {
		return strShpCity;
	}

	public void setStrShpCity(String strShpCity) {
		this.strShpCity = strShpCity;
	}

	public String getStrShpState() {
		return strShpState;
	}

	public void setStrShpState(String strShpState) {
		this.strShpState = strShpState;
	}

	public String getStrShpZipCd() {
		return strShpZipCd;
	}

	public void setStrShpZipCd(String strShpZipCd) {
		this.strShpZipCd = strShpZipCd;
	}

	public String getStrFsrVstNum() {
		return strFsrVstNum;
	}

	public void setStrFsrVstNum(String strFsrVstNum) {
		this.strFsrVstNum = strFsrVstNum;
	}

	public String getSvcMachMstrPk() {
		return svcMachMstrPk;
	}

	public void setSvcMachMstrPk(String svcMachMstrPk) {
		this.svcMachMstrPk = svcMachMstrPk;
	}

	public String getStrFsrStsCd() {
		return strFsrStsCd;
	}

	public void setStrFsrStsCd(String strFsrStsCd) {
		this.strFsrStsCd = strFsrStsCd;
	}

	public String getStrUpdFlg() {
		return strUpdFlg;
	}

	public void setStrUpdFlg(String strUpdFlg) {
		this.strUpdFlg = strUpdFlg;
	}

	public String getStrProfileId() {
		return strProfileId;
	}

	public void setStrProfileId(String strProfileId) {
		this.strProfileId = strProfileId;
	}

	public String getStrHldrNm() {
		return strHldrNm;
	}

	public void setStrHldrNm(String strHldrNm) {
		this.strHldrNm = strHldrNm;
	}

	public String getStrCardTp() {
		return strCardTp;
	}

	public void setStrCardTp(String strCardTp) {
		this.strCardTp = strCardTp;
	}

	public String getStrExprDt() {
		return strExprDt;
	}

	public void setStrExprDt(String strExprDt) {
		this.strExprDt = strExprDt;
	}

	public String getStrAuthAmt() {
		return strAuthAmt;
	}

	public void setStrAuthAmt(String strAuthAmt) {
		this.strAuthAmt = strAuthAmt;
	}

	public String getStrAttr1() {
		return strAttr1;
	}

	public void setStrAttr1(String strAttr1) {
		this.strAttr1 = strAttr1;
	}

	public String getStrAttr2() {
		return strAttr2;
	}

	public void setStrAttr2(String strAttr2) {
		this.strAttr2 = strAttr2;
	}

	public String getStrAttr3() {
		return strAttr3;
	}

	public void setStrAttr3(String strAttr3) {
		this.strAttr3 = strAttr3;
	}

	public String getStrTaskTypeCd() {
		return strTaskTypeCd;
	}

	public void setStrTaskTypeCd(String strTaskTypeCd) {
		this.strTaskTypeCd = strTaskTypeCd;
	}

	public String getStrAhsFlg() {
		return strAhsFlg;
	}

	public void setStrAhsFlg(String strAhsFlg) {
		this.strAhsFlg = strAhsFlg;
	}

	public String getStrLnBzTpCd() {
		return strLnBzTpCd;
	}

	public void setStrLnBzTpCd(String strLnBzTpCd) {
		this.strLnBzTpCd = strLnBzTpCd;
	}

	public String getStrCovTm() {
		return strCovTm;
	}

	public void setStrCovTm(String strCovTm) {
		this.strCovTm = strCovTm;
	}
	
	public String getStrOnlnValdFlg() {
		return strOnlnValdFlg;
	}

	public void setStrOnlnValdFlg(String strOnlnValdFlg) {
		this.strOnlnValdFlg = strOnlnValdFlg;
	}

	@Override
	public String toString() {
		return "CanonE307ServReqChargesHdrRec [strFsrNum=" + strFsrNum
				+ ", strSerNum=" + strSerNum + ", strChrgNum=" + strChrgNum
				+ ", chrgTotal=" + chrgTotal + ", strInvcNum=" + strInvcNum
				+ ", strInvcDate=" + strInvcDate + ", strInvcSts=" + strInvcSts
				+ ", invAmt=" + invAmt + ", strInvCurncy=" + strInvCurncy
				+ ", strTskNum=" + strTskNum + ", strModel=" + strModel
				+ ", strContractNum=" + strContractNum + ", strCovrgTpe="
				+ strCovrgTpe + ", strBilCustCd=" + strBilCustCd
				+ ", strBilCustNm=" + strBilCustNm + ", strBilAddr1="
				+ strBilAddr1 + ", strBilAddr2=" + strBilAddr2
				+ ", strBilCity=" + strBilCity + ", strBilState=" + strBilState
				+ ", strBilZipCd=" + strBilZipCd + ", strShpCustCd="
				+ strShpCustCd + ", strShpCustNm=" + strShpCustNm
				+ ", strShpAddr1=" + strShpAddr1 + ", strShpAddr2="
				+ strShpAddr2 + ", strShpCity=" + strShpCity + ", strShpState="
				+ strShpState + ", strShpZipCd=" + strShpZipCd
				+ ", svcMachMstrPk=" + svcMachMstrPk + ", strFsrVstNum="
				+ strFsrVstNum + ", strFsrStsCd=" + strFsrStsCd
				+ ", strUpdFlg=" + strUpdFlg + ", strProfileId=" + strProfileId
				+ ", strHldrNm=" + strHldrNm + ", strCardTp=" + strCardTp
				+ ", strExprDt=" + strExprDt + ", strAuthAmt=" + strAuthAmt
				+ ", strAttr1=" + strAttr1 + ", strAttr2=" + strAttr2
				+ ", strAttr3=" + strAttr3 + ", strTaskTypeCd=" + strTaskTypeCd
				+ ", strAhsFlg=" + strAhsFlg + ", strLnBzTpCd=" + strLnBzTpCd
				+ ", strCovTm=" + strCovTm + ", strOnlnValdFlg="
				+ strOnlnValdFlg + "]";
	}


}
