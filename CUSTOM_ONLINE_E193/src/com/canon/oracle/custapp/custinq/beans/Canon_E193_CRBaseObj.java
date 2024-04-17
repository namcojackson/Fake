package com.canon.oracle.custapp.custinq.beans;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class Canon_E193_CRBaseObj implements SQLData {

	private String serialNum;
	private int contractLineId;
	private String startMtrRdDt;
	private String endMtrRdDt;
	private BigDecimal oldBasePrc;
	private BigDecimal newBasePrc;
	private String invNum;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getContractLineId() {
		return contractLineId;
	}

	public void setContractLineId(int contractLineId) {
		this.contractLineId = contractLineId;
	}

	public String getStartMtrRdDt() {
		return startMtrRdDt;
	}

	public void setStartMtrRdDt(String startMtrRdDt) {
		this.startMtrRdDt = startMtrRdDt;
	}

	public String getEndMtrRdDt() {
		return endMtrRdDt;
	}

	public void setEndMtrRdDt(String endMtrRdDt) {
		this.endMtrRdDt = endMtrRdDt;
	}

	public BigDecimal getOldBasePrc() {
		return oldBasePrc;
	}

	public void setOldBasePrc(BigDecimal oldBasePrc) {
		this.oldBasePrc = oldBasePrc;
	}

	public BigDecimal getNewBasePrc() {
		return newBasePrc;
	}

	public void setNewBasePrc(BigDecimal newBasePrc) {
		this.newBasePrc = newBasePrc;
	}

	public String getInvNum() {
		return invNum;
	}

	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}

	public Canon_E193_CRBaseObj() {
	}

	public Canon_E193_CRBaseObj(String serialNum, int contractLineId,
			String startMtrRdDt, String endMtrRdDt, BigDecimal oldBasePrc,
			BigDecimal newBasePrc, String invNum) {
		super();
		this.serialNum = serialNum;
		this.contractLineId = contractLineId;
		this.startMtrRdDt = startMtrRdDt;
		this.endMtrRdDt = endMtrRdDt;
		this.oldBasePrc = oldBasePrc;
		this.newBasePrc = newBasePrc;
		this.invNum = invNum;
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(serialNum);
		stream.writeInt(contractLineId);
		stream.writeString(startMtrRdDt);
		stream.writeString(endMtrRdDt);
		stream.writeBigDecimal(oldBasePrc);
		stream.writeBigDecimal(newBasePrc);
		stream.writeString(invNum);
	}

	public void readSQL(SQLInput stream, String type) throws SQLException {
		serialNum = stream.readString();
		contractLineId = stream.readInt();
		startMtrRdDt = stream.readString();
		endMtrRdDt = stream.readString();
		oldBasePrc = stream.readBigDecimal();
		newBasePrc = stream.readBigDecimal();
		invNum = stream.readString();
	}

	public String getSQLTypeName() {
		return CanonConstants.SCHEMA_NAME + ".CANON_E218_BASE_CHANGES_REC";
	}

	@Override
	public String toString() {
		return "Canon_E193_CRBaseObj [serialNum=" + serialNum
				+ ", contractLineId=" + contractLineId + ", startMtrRdDt="
				+ startMtrRdDt + ", endMtrRdDt=" + endMtrRdDt + ", oldBasePrc="
				+ oldBasePrc + ", newBasePrc=" + newBasePrc + ", invNum="
				+ invNum + "]";
	}
	
	

}
