package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SvcMtrRdsRec implements SQLData{

	private String mtrLbDescTxt;
	private String svcPhysMtrPk;
	private String svcMachMstrPk;
	private String dsMtrRdTpCd;
	private String outMtrRdCnt;
	private String inMtrRdCnt;
	private String fsrNum;
	private String svcTaskNum;
	private String mtrSubmitFlg;
	private String mtrMndtrFlg;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String mtrEntryCmntTxt;
	
	public CanonE307SvcMtrRdsRec() {
		// TODO Auto-generated constructor stub
	}



	public CanonE307SvcMtrRdsRec(String mtrLbDescTxt, String svcPhysMtrPk,
			String svcMachMstrPk, String dsMtrRdTpCd, String outMtrRdCnt,
			String inMtrRdCnt, String fsrNum, String svcTaskNum,
			String mtrSubmitFlg, String mtrMndtrFlg, String attribute1,
			String attribute2, String attribute3, String attribute4,
			String attribute5, String mtrEntryCmntTxt) {
		super();
		this.mtrLbDescTxt = mtrLbDescTxt;
		this.svcPhysMtrPk = svcPhysMtrPk;
		this.svcMachMstrPk = svcMachMstrPk;
		this.dsMtrRdTpCd = dsMtrRdTpCd;
		this.outMtrRdCnt = outMtrRdCnt;
		this.inMtrRdCnt = inMtrRdCnt;
		this.fsrNum = fsrNum;
		this.svcTaskNum = svcTaskNum;
		this.mtrSubmitFlg = mtrSubmitFlg;
		this.mtrMndtrFlg = mtrMndtrFlg;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.mtrEntryCmntTxt = mtrEntryCmntTxt;
	}




	public String getMtrLbDescTxt() {
		return mtrLbDescTxt;
	}

	public void setMtrLbDescTxt(String mtrLbDescTxt) {
		this.mtrLbDescTxt = mtrLbDescTxt;
	}

	public String getSvcPhysMtrPk() {
		return svcPhysMtrPk;
	}

	public void setSvcPhysMtrPk(String svcPhysMtrPk) {
		this.svcPhysMtrPk = svcPhysMtrPk;
	}


	public String getDsMtrRdTpCd() {
		return dsMtrRdTpCd;
	}

	public void setDsMtrRdTpCd(String dsMtrRdTpCd) {
		this.dsMtrRdTpCd = dsMtrRdTpCd;
	}

	public String getOutMtrRdCnt() {
		return outMtrRdCnt;
	}

	public void setOutMtrRdCnt(String outMtrRdCnt) {
		this.outMtrRdCnt = outMtrRdCnt;
	}

	public String getInMtrRdCnt() {
		return inMtrRdCnt;
	}

	public void setInMtrRdCnt(String inMtrRdCnt) {
		this.inMtrRdCnt = inMtrRdCnt;
	}

	public String getFsrNum() {
		return fsrNum;
	}

	public void setFsrNum(String fsrNum) {
		this.fsrNum = fsrNum;
	}

	public String getSvcTaskNum() {
		return svcTaskNum;
	}

	public void setSvcTaskNum(String svcTaskNum) {
		this.svcTaskNum = svcTaskNum;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		attribute5 = attribute5;
	}
	
	public String getSvcMachMstrPk() {
		return svcMachMstrPk;
	}


	public void setSvcMachMstrPk(String svcMachMstrPk) {
		this.svcMachMstrPk = svcMachMstrPk;
	}


	public String getMtrSubmitFlg() {
		return mtrSubmitFlg;
	}


	public void setMtrSubmitFlg(String mtrSubmitFlg) {
		this.mtrSubmitFlg = mtrSubmitFlg;
	}


	public String getMtrMndtrFlg() {
		return mtrMndtrFlg;
	}


	public void setMtrMndtrFlg(String mtrMndtrFlg) {
		this.mtrMndtrFlg = mtrMndtrFlg;
	}


	public String getMtrEntryCmntTxt() {
		return mtrEntryCmntTxt;
	}



	public void setMtrEntryCmntTxt(String mtrEntryCmntTxt) {
		this.mtrEntryCmntTxt = mtrEntryCmntTxt;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SVC_READS_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		mtrLbDescTxt = stream.readString();
		svcPhysMtrPk = stream.readString();
		svcMachMstrPk = stream.readString();
		dsMtrRdTpCd = stream.readString();
		outMtrRdCnt = stream.readString();
		inMtrRdCnt = stream.readString();
		fsrNum = stream.readString();
		svcTaskNum = stream.readString();
		mtrSubmitFlg = stream.readString();
		mtrMndtrFlg = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
		attribute4 = stream.readString();
		attribute5 = stream.readString();
		mtrEntryCmntTxt = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(mtrLbDescTxt);
		stream.writeString(svcPhysMtrPk);
		stream.writeString(svcMachMstrPk);
		stream.writeString(dsMtrRdTpCd);
		stream.writeString(outMtrRdCnt);
		stream.writeString(inMtrRdCnt);
		stream.writeString(fsrNum);
		stream.writeString(svcTaskNum);
		stream.writeString(mtrSubmitFlg);
		stream.writeString(mtrMndtrFlg);
		stream.writeString(attribute1);
		stream.writeString(attribute2);
		stream.writeString(attribute3);
		stream.writeString(attribute4);
		stream.writeString(attribute5);
		stream.writeString(mtrEntryCmntTxt);
	}



	@Override
	public String toString() {
		return "CanonE307SvcMtrRdsRec [mtrLbDescTxt=" + mtrLbDescTxt
				+ ", svcPhysMtrPk=" + svcPhysMtrPk + ", svcMachMstrPk="
				+ svcMachMstrPk + ", dsMtrRdTpCd=" + dsMtrRdTpCd
				+ ", outMtrRdCnt=" + outMtrRdCnt + ", inMtrRdCnt=" + inMtrRdCnt
				+ ", fsrNum=" + fsrNum + ", svcTaskNum=" + svcTaskNum
				+ ", mtrSubmitFlg=" + mtrSubmitFlg + ", mtrMndtrFlg="
				+ mtrMndtrFlg + ", attribute1=" + attribute1 + ", attribute2="
				+ attribute2 + ", attribute3=" + attribute3 + ", attribute4="
				+ attribute4 + ", attribute5=" + attribute5
				+ ", mtrEntryCmntTxt=" + mtrEntryCmntTxt + "]";
	}



}
