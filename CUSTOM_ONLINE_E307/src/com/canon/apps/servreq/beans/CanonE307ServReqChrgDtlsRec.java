package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServReqChrgDtlsRec implements SQLData{

	private String strChrgNum;
	private String strLnNum;
	private String strBilngTpe;
	private String strItmNum;
	private String strItmDesc;
	private int qty;
	private String strUomCd;
	private double unitLstPrice;
	private String unitOvrRidPrice;
	private double extendedAmt;
	private String strNoChrgFlg;
	private String strTrxTpe;
	private String strTrxPriceLst;
	private String strTrxSrc;
	private String strTrxSrcRef;
	private String strCreationDt;
	private String strUpdFlg;
	private String strFsrNum;
	private String strTskNum;
	private String strChangRsn;
	private double contractPrice;
	private String strUpdatedBy;
	private double netPrice;
	private int fsrChrgDtlPk;
	private String strFsrVstNum;
	private String strChrgTrxTpCd;
	private String strChrgDealAmt;
	private String strPrcCatgCd;
	private String strSvcChrgTpCd;
	private String strSvcChrgDiscRt;

	public CanonE307ServReqChrgDtlsRec() {
		// TODO Auto-generated constructor stub
	}



	public CanonE307ServReqChrgDtlsRec(String strChrgNum, String strLnNum,
			String strBilngTpe, String strItmNum, String strItmDesc, int qty,
			String strUomCd, double unitLstPrice, String unitOvrRidPrice,
			double extendedAmt, String strNoChrgFlg, String strTrxTpe,
			String strTrxPriceLst, String strTrxSrc, String strTrxSrcRef,
			String strCreationDt, String strUpdFlg, String strFsrNum,
			String strTskNum, String strChangRsn, double contractPrice,
			String strUpdatedBy, double netPrice, int fsrChrgDtlPk,
			String strFsrVstNum, String strChrgTrxTpCd, String strChrgDealAmt,
			String strPrcCatgCd, String strSvcChrgTpCd, String strSvcChrgDiscRt) {
		super();
		this.strChrgNum = strChrgNum;
		this.strLnNum = strLnNum;
		this.strBilngTpe = strBilngTpe;
		this.strItmNum = strItmNum;
		this.strItmDesc = strItmDesc;
		this.qty = qty;
		this.strUomCd = strUomCd;
		this.unitLstPrice = unitLstPrice;
		this.unitOvrRidPrice = unitOvrRidPrice;
		this.extendedAmt = extendedAmt;
		this.strNoChrgFlg = strNoChrgFlg;
		this.strTrxTpe = strTrxTpe;
		this.strTrxPriceLst = strTrxPriceLst;
		this.strTrxSrc = strTrxSrc;
		this.strTrxSrcRef = strTrxSrcRef;
		this.strCreationDt = strCreationDt;
		this.strUpdFlg = strUpdFlg;
		this.strFsrNum = strFsrNum;
		this.strTskNum = strTskNum;
		this.strChangRsn = strChangRsn;
		this.contractPrice = contractPrice;
		this.strUpdatedBy = strUpdatedBy;
		this.netPrice = netPrice;
		this.fsrChrgDtlPk = fsrChrgDtlPk;
		this.strFsrVstNum = strFsrVstNum;
		this.strChrgTrxTpCd = strChrgTrxTpCd;
		this.strChrgDealAmt = strChrgDealAmt;
		this.strPrcCatgCd = strPrcCatgCd;
		this.strSvcChrgTpCd = strSvcChrgTpCd;
		this.strSvcChrgDiscRt = strSvcChrgDiscRt;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CHG_DTL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strChrgNum = stream.readString();
		strLnNum = stream.readString();
		strBilngTpe = stream.readString();
		strItmNum = stream.readString();
		strItmDesc = stream.readString();
		qty = stream.readInt();
		strUomCd = stream.readString();
		unitLstPrice = stream.readDouble();
		unitOvrRidPrice = stream.readString();
		extendedAmt = stream.readDouble();
		strNoChrgFlg = stream.readString();
		strTrxTpe = stream.readString();
		strTrxPriceLst = stream.readString();
		strTrxSrc = stream.readString();
		strTrxSrcRef = stream.readString();
		strCreationDt = stream.readString();
		strUpdFlg = stream.readString();
		strFsrNum = stream.readString();
		strTskNum = stream.readString();
		strChangRsn =stream.readString();
		contractPrice = stream.readDouble();	
		strUpdatedBy = stream.readString();
		netPrice= stream.readDouble();
		fsrChrgDtlPk = stream.readInt();
		strFsrVstNum= stream.readString();
		strChrgTrxTpCd = stream.readString();
		strChrgDealAmt = stream.readString();
		strPrcCatgCd = stream.readString();
		strSvcChrgTpCd = stream.readString();
		strSvcChrgDiscRt = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strChrgNum);
		stream.writeString(strLnNum);
		stream.writeString(strBilngTpe);
		stream.writeString(strItmNum);
		stream.writeString(strItmDesc);
		stream.writeInt(qty);
		stream.writeString(strUomCd);
		stream.writeDouble(unitLstPrice);
		stream.writeString(unitOvrRidPrice);
		stream.writeDouble(extendedAmt);
		stream.writeString(strNoChrgFlg);
		stream.writeString(strTrxTpe);
		stream.writeString(strTrxPriceLst);
		stream.writeString(strTrxSrc);
		stream.writeString(strTrxSrcRef);
		stream.writeString(strCreationDt);
		stream.writeString(strUpdFlg);
		stream.writeString(strFsrNum);
		stream.writeString(strTskNum);
		stream.writeString(strChangRsn);
		stream.writeDouble(contractPrice);
		stream.writeString(strUpdatedBy);
		stream.writeDouble(netPrice);
		stream.writeInt(fsrChrgDtlPk);
		stream.writeString(strFsrVstNum);
		stream.writeString(strChrgTrxTpCd);
		stream.writeString(strChrgDealAmt);
		stream.writeString(strPrcCatgCd);
		stream.writeString(strSvcChrgTpCd);
		stream.writeString(strSvcChrgDiscRt);
	}

	public String getStrChrgNum() {
		return strChrgNum;
	}

	public void setStrChrgNum(String strChrgNum) {
		this.strChrgNum = strChrgNum;
	}

	public String getStrLnNum() {
		return strLnNum;
	}

	public void setStrLnNum(String strLnNum) {
		this.strLnNum = strLnNum;
	}

	public String getStrBilngTpe() {
		return strBilngTpe;
	}

	public void setStrBilngTpe(String strBilngTpe) {
		this.strBilngTpe = strBilngTpe;
	}

	public String getStrItmNum() {
		return strItmNum;
	}

	public void setStrItmNum(String strItmNum) {
		this.strItmNum = strItmNum;
	}

	public String getStrItmDesc() {
		return strItmDesc;
	}

	public void setStrItmDesc(String strItmDesc) {
		this.strItmDesc = strItmDesc;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getStrUomCd() {
		return strUomCd;
	}

	public void setStrUomCd(String strUomCd) {
		this.strUomCd = strUomCd;
	}

	public double getUnitLstPrice() {
		return unitLstPrice;
	}


	public void setUnitLstPrice(double unitLstPrice) {
		this.unitLstPrice = unitLstPrice;
	}


	public String getUnitOvrRidPrice() {
		return unitOvrRidPrice;
	}


	public void setUnitOvrRidPrice(String unitOvrRidPrice) {
		this.unitOvrRidPrice = unitOvrRidPrice;
	}


	public double getExtendedAmt() {
		return extendedAmt;
	}


	public void setExtendedAmt(double extendedAmt) {
		this.extendedAmt = extendedAmt;
	}


	public void setContractPrice(double contractPrice) {
		this.contractPrice = contractPrice;
	}


	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}


	public void setExtendedAmt(int extendedAmt) {
		this.extendedAmt = extendedAmt;
	}

	public String getStrNoChrgFlg() {
		return strNoChrgFlg;
	}

	public void setStrNoChrgFlg(String strNoChrgFlg) {
		this.strNoChrgFlg = strNoChrgFlg;
	}

	public String getStrTrxTpe() {
		return strTrxTpe;
	}

	public void setStrTrxTpe(String strTrxTpe) {
		this.strTrxTpe = strTrxTpe;
	}

	public String getStrTrxPriceLst() {
		return strTrxPriceLst;
	}

	public void setStrTrxPriceLst(String strTrxPriceLst) {
		this.strTrxPriceLst = strTrxPriceLst;
	}

	public String getStrTrxSrc() {
		return strTrxSrc;
	}

	public void setStrTrxSrc(String strTrxSrc) {
		this.strTrxSrc = strTrxSrc;
	}

	public String getStrTrxSrcRef() {
		return strTrxSrcRef;
	}

	public void setStrTrxSrcRef(String strTrxSrcRef) {
		this.strTrxSrcRef = strTrxSrcRef;
	}

	public String getStrCreationDt() {
		return strCreationDt;
	}

	public void setStrCreationDt(String strCreationDt) {
		this.strCreationDt = strCreationDt;
	}

	public String getStrUpdFlg() {
		return strUpdFlg;
	}

	public void setStrUpdFlg(String strUpdFlg) {
		this.strUpdFlg = strUpdFlg;
	}

	public String getStrFsrNum() {
		return strFsrNum;
	}

	public void setStrFsrNum(String strFsrNum) {
		this.strFsrNum = strFsrNum;
	}

	public String getStrTskNum() {
		return strTskNum;
	}

	public void setStrTskNum(String strTskNum) {
		this.strTskNum = strTskNum;
	}


	public String getStrChangRsn() {
		return strChangRsn;
	}


	public void setStrChangRsn(String strChangRsn) {
		this.strChangRsn = strChangRsn;
	}


	public String getStrUpdatedBy() {
		return strUpdatedBy;
	}


	public void setStrUpdatedBy(String strUpdatedBy) {
		this.strUpdatedBy = strUpdatedBy;
	}


	public int getFsrChrgDtlPk() {
		return fsrChrgDtlPk;
	}

	public void setFsrChrgDtlPk(int fsrChrgDtlPk) {
		this.fsrChrgDtlPk = fsrChrgDtlPk;
	}

	public String getStrFsrVstNum() {
		return strFsrVstNum;
	}

	public void setStrFsrVstNum(String strFsrVstNum) {
		this.strFsrVstNum = strFsrVstNum;
	}

	public String getStrChrgTrxTpCd() {
		return strChrgTrxTpCd;
	}

	public void setStrChrgTrxTpCd(String strChrgTrxTpCd) {
		this.strChrgTrxTpCd = strChrgTrxTpCd;
	}

	public String getStrChrgDealAmt() {
		return strChrgDealAmt;
	}

	public void setStrChrgDealAmt(String strChrgDealAmt) {
		this.strChrgDealAmt = strChrgDealAmt;
	}

	public String getStrPrcCatgCd() {
		return strPrcCatgCd;
	}

	public void setStrPrcCatgCd(String strPrcCatgCd) {
		this.strPrcCatgCd = strPrcCatgCd;
	}


	public double getContractPrice() {
		return contractPrice;
	}


	public double getNetPrice() {
		return netPrice;
	}


	public String getStrSvcChrgTpCd() {
		return strSvcChrgTpCd;
	}


	public void setStrSvcChrgTpCd(String strSvcChrgTpCd) {
		this.strSvcChrgTpCd = strSvcChrgTpCd;
	}


	public String getStrSvcChrgDiscRt() {
		return strSvcChrgDiscRt;
	}


	public void setStrSvcChrgDiscRt(String strSvcChrgDiscRt) {
		this.strSvcChrgDiscRt = strSvcChrgDiscRt;
	}


	@Override
	public String toString() {
		return "CanonE307ServReqChrgDtlsRec [strChrgNum=" + strChrgNum
				+ ", strLnNum=" + strLnNum + ", strBilngTpe=" + strBilngTpe
				+ ", strItmNum=" + strItmNum + ", strItmDesc=" + strItmDesc
				+ ", qty=" + qty + ", strUomCd=" + strUomCd + ", unitLstPrice="
				+ unitLstPrice + ", unitOvrRidPrice=" + unitOvrRidPrice
				+ ", extendedAmt=" + extendedAmt + ", strNoChrgFlg="
				+ strNoChrgFlg + ", strTrxTpe=" + strTrxTpe
				+ ", strTrxPriceLst=" + strTrxPriceLst + ", strTrxSrc="
				+ strTrxSrc + ", strTrxSrcRef=" + strTrxSrcRef
				+ ", strCreationDt=" + strCreationDt + ", strUpdFlg="
				+ strUpdFlg + ", strFsrNum=" + strFsrNum + ", strTskNum="
				+ strTskNum + ", strChangRsn=" + strChangRsn
				+ ", contractPrice=" + contractPrice + ", strUpdatedBy="
				+ strUpdatedBy + ", netPrice=" + netPrice + ", fsrChrgDtlPk="
				+ fsrChrgDtlPk + ", strFsrVstNum=" + strFsrVstNum
				+ ", strChrgTrxTpCd=" + strChrgTrxTpCd + ", strChrgDealAmt="
				+ strChrgDealAmt + ", strPrcCatgCd=" + strPrcCatgCd
				+ ", strSvcChrgTpCd=" + strSvcChrgTpCd + ", strSvcChrgDiscRt="
				+ strSvcChrgDiscRt + "]";
	}


}
