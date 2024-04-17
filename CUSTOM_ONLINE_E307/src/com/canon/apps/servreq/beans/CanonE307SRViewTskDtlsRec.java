package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRViewTskDtlsRec implements SQLData {

	public CanonE307SRViewTskDtlsRec() {
		// TODO Auto-generated constructor stub
	}
	
	private String taskNum;
	private String tskTpe;
	private String tskSts;
	private String tskPblmCd;
	private String tskPblmNt;
	private String mobileNt;
	private String prmryMtr;
	private String tskRespTm;
	private String tskLbrStrtTm;
	private String tskLbrEndTm;
	private String pblmTpCd;
	private String corCtnCd;
	private String tskLocTnCd;
	private String tskRsnCd;
	private String tskMachSts;
	private String tskTech;
	
	
	public CanonE307SRViewTskDtlsRec(String taskNum, String tskTpe,
			String tskSts, String tskPblmCd, String tskPblmNt, String mobileNt,
			String prmryMtr, String tskRespTm, String tskLbrStrtTm,
			String tskLbrEndTm, String pblmTpCd, String corCtnCd,
			String tskLocTnCd, String tskRsnCd, String tskMachSts,
			String tskTech) {
		super();
		this.taskNum = taskNum;
		this.tskTpe = tskTpe;
		this.tskSts = tskSts;
		this.tskPblmCd = tskPblmCd;
		this.tskPblmNt = tskPblmNt;
		this.mobileNt = mobileNt;
		this.prmryMtr = prmryMtr;
		this.tskRespTm = tskRespTm;
		this.tskLbrStrtTm = tskLbrStrtTm;
		this.tskLbrEndTm = tskLbrEndTm;
		this.pblmTpCd = pblmTpCd;
		this.corCtnCd = corCtnCd;
		this.tskLocTnCd = tskLocTnCd;
		this.tskRsnCd = tskRsnCd;
		this.tskMachSts = tskMachSts;
		this.tskTech = tskTech;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SRVIEW_TSK_DTLS_REC";
	}


	public String getTaskNum() {
		return taskNum;
	}


	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}


	public String getTskTpe() {
		return tskTpe;
	}


	public void setTskTpe(String tskTpe) {
		this.tskTpe = tskTpe;
	}


	public String getTskSts() {
		return tskSts;
	}


	public void setTskSts(String tstSts) {
		this.tskSts = tstSts;
	}


	public String getTskPblmCd() {
		return tskPblmCd;
	}


	public void setTskPblmCd(String tskPblmCd) {
		this.tskPblmCd = tskPblmCd;
	}


	public String getTskPblmNt() {
		return tskPblmNt;
	}


	public void setTskPblmNt(String tskPblmNt) {
		this.tskPblmNt = tskPblmNt;
	}


	public String getMobileNt() {
		return mobileNt;
	}


	public void setMobileNt(String mobileNt) {
		this.mobileNt = mobileNt;
	}


	public String getPrmryMtr() {
		return prmryMtr;
	}


	public void setPrmryMtr(String prmryMtr) {
		this.prmryMtr = prmryMtr;
	}


	public String getTskRespTm() {
		return tskRespTm;
	}


	public void setTskRespTm(String tskRespTm) {
		this.tskRespTm = tskRespTm;
	}


	public String getTskLbrStrtTm() {
		return tskLbrStrtTm;
	}


	public void setTskLbrStrtTm(String tskLbrStrtTm) {
		this.tskLbrStrtTm = tskLbrStrtTm;
	}


	public String getTskLbrEndTm() {
		return tskLbrEndTm;
	}


	public void setTskLbrEndTm(String tskLbrEndTm) {
		this.tskLbrEndTm = tskLbrEndTm;
	}


	public String getPblmTpCd() {
		return pblmTpCd;
	}


	public void setPblmTpCd(String pblmTpCd) {
		this.pblmTpCd = pblmTpCd;
	}


	public String getCorCtnCd() {
		return corCtnCd;
	}


	public void setCorCtnCd(String corCtnCd) {
		this.corCtnCd = corCtnCd;
	}


	public String getTskLocTnCd() {
		return tskLocTnCd;
	}


	public void setTskLocTnCd(String tskLocTnCd) {
		this.tskLocTnCd = tskLocTnCd;
	}


	public String getTskRsnCd() {
		return tskRsnCd;
	}


	public void setTskRsnCd(String tskRsnCd) {
		this.tskRsnCd = tskRsnCd;
	}


	public String getTskMachSts() {
		return tskMachSts;
	}


	public void setTskMachSts(String tskMachSts) {
		this.tskMachSts = tskMachSts;
	}


	public String getTskTech() {
		return tskTech;
	}


	public void setTskTech(String tskTech) {
		this.tskTech = tskTech;
	}


	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		taskNum = stream.readString();
		tskTpe = stream.readString();
		tskSts = stream.readString();
		tskPblmCd = stream.readString();
		tskPblmNt = stream.readString();
		mobileNt = stream.readString();
		prmryMtr = stream.readString();
		tskRespTm = stream.readString();
		tskLbrStrtTm = stream.readString();
		tskLbrEndTm = stream.readString();
		pblmTpCd = stream.readString();
		corCtnCd = stream.readString();
		tskLocTnCd = stream.readString();
		tskRsnCd = stream.readString();
		tskMachSts = stream.readString();		
		tskTech = stream.readString();
	}


	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(taskNum);
		stream.writeString(tskTpe);	
		stream.writeString(tskSts);
		stream.writeString(tskPblmCd);
		stream.writeString(tskPblmNt);
		stream.writeString(mobileNt);
		stream.writeString(prmryMtr);
		stream.writeString(tskRespTm);
		stream.writeString(tskLbrStrtTm);
		stream.writeString(tskLbrEndTm);
		stream.writeString(pblmTpCd);
		stream.writeString(corCtnCd);
		stream.writeString(tskLocTnCd);
		stream.writeString(tskRsnCd);
		stream.writeString(tskMachSts);
		stream.writeString(tskTech);		
	}


	@Override
	public String toString() {
		return "CanonE307SRViewTskDtlsRec [taskNum=" + taskNum
				+ ", tskTpe=" + tskTpe + ", tskSts=" + tskSts + ", tskPblmCd="
				+ tskPblmCd + ", tskPblmNt=" + tskPblmNt + ", mobileNt="
				+ mobileNt + ", prmryMtr=" + prmryMtr + ", tskRespTm="
				+ tskRespTm + ", tskLbrStrtTm=" + tskLbrStrtTm
				+ ", tskLbrEndTm=" + tskLbrEndTm + ", pblmTpCd=" + pblmTpCd
				+ ", corCtnCd=" + corCtnCd + ", tskLocTnCd=" + tskLocTnCd
				+ ", tskRsnCd=" + tskRsnCd + ", tskMachSts=" + tskMachSts
				+ ", tskTech=" + tskTech + "]";
	}
	

}
