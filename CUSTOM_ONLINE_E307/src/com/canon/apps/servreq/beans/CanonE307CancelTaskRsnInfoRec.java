package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307CancelTaskRsnInfoRec implements SQLData{
	
	private String taskNum;
	private String fsrNum;
	private String svcCallTpCd;
	private String svcCallTpNm;
	private String svcTaskSts;
	private String svcTaskStsCd;
	private String cancelRsn;
	private String rsnNote;
	private String attribute1;
	private String attribute2;
	private String attribute3;

	public CanonE307CancelTaskRsnInfoRec() {
	}

	
	public CanonE307CancelTaskRsnInfoRec(String taskNum, String fsrNum,
			String svcCallTpCd, String svcCallTpNm, String svcTaskSts,
			String svcTaskStsCd, String cancelRsn, String rsnNote,
			String attribute1, String attribute2, String attribute3) {
		super();
		this.taskNum = taskNum;
		this.fsrNum = fsrNum;
		this.svcCallTpCd = svcCallTpCd;
		this.svcCallTpNm = svcCallTpNm;
		this.svcTaskSts = svcTaskSts;
		this.svcTaskStsCd = svcTaskStsCd;
		this.cancelRsn = cancelRsn;
		this.rsnNote = rsnNote;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
	}

	public String getTaskNum() {
		return taskNum;
	}


	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}


	public String getFsrNum() {
		return fsrNum;
	}


	public void setFsrNum(String fsrNum) {
		this.fsrNum = fsrNum;
	}


	public String getSvcCallTpCd() {
		return svcCallTpCd;
	}


	public void setSvcCallTpCd(String svcCallTpCd) {
		this.svcCallTpCd = svcCallTpCd;
	}


	public String getSvcCallTpNm() {
		return svcCallTpNm;
	}


	public void setSvcCallTpNm(String svcCallTpNm) {
		this.svcCallTpNm = svcCallTpNm;
	}


	public String getSvcTaskSts() {
		return svcTaskSts;
	}


	public void setSvcTaskSts(String svcTaskSts) {
		this.svcTaskSts = svcTaskSts;
	}


	public String getSvcTaskStsCd() {
		return svcTaskStsCd;
	}


	public void setSvcTaskStsCd(String svcTaskStsCd) {
		this.svcTaskStsCd = svcTaskStsCd;
	}


	public String getCancelRsn() {
		return cancelRsn;
	}


	public void setCancelRsn(String cancelRsn) {
		this.cancelRsn = cancelRsn;
	}


	public String getRsnNote() {
		return rsnNote;
	}


	public void setRsnNote(String rsnNote) {
		this.rsnNote = rsnNote;
	}


	public String getAttribute1() {
		return attribute1;
	}


	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}


	public String getAttribute2() {
		return attribute2;
	}


	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}


	public String getAttribute3() {
		return attribute3;
	}


	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		 return CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_RSN_INFO_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		taskNum = stream.readString();
		fsrNum = stream.readString();
		svcCallTpCd =  stream.readString();
		svcCallTpNm = stream.readString();
		svcTaskSts = stream.readString();
		svcTaskStsCd = stream.readString();
		cancelRsn = stream.readString();
		rsnNote = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		  stream.writeString(taskNum);
		  stream.writeString(fsrNum);
		  stream.writeString(svcCallTpCd);
		  stream.writeString(svcCallTpNm);
		  stream.writeString(svcTaskSts);
		  stream.writeString(svcTaskStsCd);
		  stream.writeString(cancelRsn);
		  stream.writeString(rsnNote);
		  stream.writeString(attribute1);
		  stream.writeString(attribute2);
		  stream.writeString(attribute3);
	}

	
}
