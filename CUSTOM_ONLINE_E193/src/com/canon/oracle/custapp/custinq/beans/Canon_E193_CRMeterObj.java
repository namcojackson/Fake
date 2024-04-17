package com.canon.oracle.custapp.custinq.beans;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class Canon_E193_CRMeterObj implements SQLData {

	private String serialNum;
	private String stMtrRdDt;
	private String endMtrRdDt;
	private BigDecimal stTotHardRd;
	private BigDecimal endTotHardRd;
	private BigDecimal oldTestCp;
	private BigDecimal newTestCp;	
	private String billIngMeterLbCd;
	private String physMtrLbCd;
	private String invNum;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getStartMtrRd() {
		return stMtrRdDt;
	}

	public void setStartMtrRd(String stMtrRdDt) {
		this.stMtrRdDt = stMtrRdDt;
	}

	public String getEndMtrRd() {
		return endMtrRdDt;
	}

	public void setEndMtrRd(String endMtrRdDt) {
		this.endMtrRdDt = endMtrRdDt;
	}

	public String getStMtrRdDt() {
		return stMtrRdDt;
	}

	public void setStMtrRdDt(String stMtrRdDt) {
		this.stMtrRdDt = stMtrRdDt;
	}

	public String getEndMtrRdDt() {
		return endMtrRdDt;
	}

	public void setEndMtrRdDt(String endMtrRdDt) {
		this.endMtrRdDt = endMtrRdDt;
	}

	public BigDecimal getStTotHardRd() {
		return stTotHardRd;
	}

	public void setStTotHardRd(BigDecimal stTotHardRd) {
		this.stTotHardRd = stTotHardRd;
	}

	public BigDecimal getEndTotHardRd() {
		return endTotHardRd;
	}

	public void setEndTotHardRd(BigDecimal endTotHardRd) {
		this.endTotHardRd = endTotHardRd;
	}

	public BigDecimal getOldTestCp() {
		return oldTestCp;
	}

	public void setOldTestCp(BigDecimal oldTestCp) {
		this.oldTestCp = oldTestCp;
	}

	public BigDecimal getNewTestCp() {
		return newTestCp;
	}

	public void setNewTestCp(BigDecimal newTestCp) {
		this.newTestCp = newTestCp;
	}

	public String getBillIngMeterLbCd() {
		return billIngMeterLbCd;
	}

	public void setBillIngMeterLbCd(String billIngMeterLbCd) {
		this.billIngMeterLbCd = billIngMeterLbCd;
	}

	public String getPhysMtrLbCd() {
		return physMtrLbCd;
	}

	public void setPhysMtrLbCd(String physMtrLbCd) {
		this.physMtrLbCd = physMtrLbCd;
	}

	public String getInvNum() {
		return invNum;
	}

	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}

	@Override
	public String toString() {
		return "Canon_E193_CRMeterObj [serialNum=" + serialNum + ", stMtrRdDt="
				+ stMtrRdDt + ", endMtrRdDt=" + endMtrRdDt + ", stTotHardRd="
				+ stTotHardRd + ", endTotHardRd=" + endTotHardRd
				+ ", oldTestCp=" + oldTestCp + ", newTestCp=" + newTestCp
				+ ", billIngMeterLbCd=" + billIngMeterLbCd+ ", physMtrLbCd=" + physMtrLbCd + ", invNum=" + invNum + "]";
	}

	public Canon_E193_CRMeterObj() {
	}

	public Canon_E193_CRMeterObj(String serialNum, String stMtrRdDt,
			String endMtrRdDt, BigDecimal stTotHardRd, BigDecimal endTotHardRd,
			BigDecimal oldTestCp, BigDecimal newTestCp, String billIngMeterLbCd, String physMtrLbCd,String invNum) {
		super();
		this.serialNum = serialNum;
		this.stMtrRdDt = stMtrRdDt;
		this.endMtrRdDt = endMtrRdDt;
		this.stTotHardRd = stTotHardRd;
		this.endTotHardRd = endTotHardRd;
		this.oldTestCp = oldTestCp;
		this.newTestCp = newTestCp;
		this.billIngMeterLbCd = billIngMeterLbCd;
		this.physMtrLbCd=physMtrLbCd;
		this.invNum = invNum;
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(serialNum);
		stream.writeString(stMtrRdDt);
		stream.writeString(endMtrRdDt);
		stream.writeBigDecimal(stTotHardRd);
		stream.writeBigDecimal(endTotHardRd);
		stream.writeBigDecimal(oldTestCp);
		stream.writeBigDecimal(newTestCp);
		stream.writeString(billIngMeterLbCd);
		stream.writeString(physMtrLbCd);
		stream.writeString(invNum);
	}

	public void readSQL(SQLInput stream, String type) throws SQLException {
		serialNum = stream.readString();
		stMtrRdDt = stream.readString();
		endMtrRdDt = stream.readString();
		stTotHardRd = stream.readBigDecimal();
		endTotHardRd = stream.readBigDecimal();
		oldTestCp = stream.readBigDecimal();
		newTestCp = stream.readBigDecimal();
		billIngMeterLbCd = stream.readString();
		physMtrLbCd=stream.readString();
		invNum = stream.readString();
	}

	public String getSQLTypeName() {
		return CanonConstants.SCHEMA_NAME + ".CANON_E218_MTR_READ_CHNG_REC";
	}

}
