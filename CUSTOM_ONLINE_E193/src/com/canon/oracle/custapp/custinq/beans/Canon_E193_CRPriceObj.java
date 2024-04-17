package com.canon.oracle.custapp.custinq.beans;

import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class Canon_E193_CRPriceObj implements SQLData {

	private String serialNum;
	private int contractLineId;
	private String dateBilledFrom;
	private String dateBilledTo;
	private BigDecimal oldTier1CopyVolume;
	private BigDecimal oldTier1Rate;
	private BigDecimal oldTier2CopyVolume;
	private BigDecimal oldTier2Rate;
	private BigDecimal oldTier3CopyVolume;
	private BigDecimal oldTier3Rate;
	private BigDecimal oldTier4CopyVolume;
	private BigDecimal oldTier4Rate;
	private BigDecimal newTier1CopyVolume;
	private BigDecimal newTier1Rate;
	private BigDecimal newTier2CopyVolume;
	private BigDecimal newTier2Rate;
	private BigDecimal newTier3CopyVolume;
	private BigDecimal newTier3Rate;
	private BigDecimal newTier4CopyVolume;
	private BigDecimal newTier4Rate;
	private String counterName;
	private String aggregateNumber;
	private String invNum;
	private String billIngMeterLbCd;

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

	public String getDateBilledFrom() {
		return dateBilledFrom;
	}

	public void setDateBilledFrom(String dateBilledFrom) {
		this.dateBilledFrom = dateBilledFrom;
	}

	public String getDateBilledTo() {
		return dateBilledTo;
	}

	public void setDateBilledTo(String dateBilledTo) {
		this.dateBilledTo = dateBilledTo;
	}
	
	
	public BigDecimal getOldTier1CopyVolume() {
		return oldTier1CopyVolume;
	}

	public void setOldTier1CopyVolume(BigDecimal oldTier1CopyVolume) {
		this.oldTier1CopyVolume = oldTier1CopyVolume;
	}

	public BigDecimal getOldTier1Rate() {
		return oldTier1Rate;
	}

	public void setOldTier1Rate(BigDecimal oldTier1Rate) {
		this.oldTier1Rate = oldTier1Rate;
	}

	public BigDecimal getOldTier2CopyVolume() {
		return oldTier2CopyVolume;
	}

	public void setOldTier2CopyVolume(BigDecimal oldTier2CopyVolume) {
		this.oldTier2CopyVolume = oldTier2CopyVolume;
	}

	public BigDecimal getOldTier2Rate() {
		return oldTier2Rate;
	}

	public void setOldTier2Rate(BigDecimal oldTier2Rate) {
		this.oldTier2Rate = oldTier2Rate;
	}

	public BigDecimal getOldTier3CopyVolume() {
		return oldTier3CopyVolume;
	}

	public void setOldTier3CopyVolume(BigDecimal oldTier3CopyVolume) {
		this.oldTier3CopyVolume = oldTier3CopyVolume;
	}

	public BigDecimal getOldTier3Rate() {
		return oldTier3Rate;
	}

	public void setOldTier3Rate(BigDecimal oldTier3Rate) {
		this.oldTier3Rate = oldTier3Rate;
	}

	public BigDecimal getOldTier4CopyVolume() {
		return oldTier4CopyVolume;
	}

	public void setOldTier4CopyVolume(BigDecimal oldTier4CopyVolume) {
		this.oldTier4CopyVolume = oldTier4CopyVolume;
	}

	public BigDecimal getOldTier4Rate() {
		return oldTier4Rate;
	}

	public void setOldTier4Rate(BigDecimal oldTier4Rate) {
		this.oldTier4Rate = oldTier4Rate;
	}

	public BigDecimal getNewTier1CopyVolume() {
		return newTier1CopyVolume;
	}

	public void setNewTier1CopyVolume(BigDecimal newTier1CopyVolume) {
		this.newTier1CopyVolume = newTier1CopyVolume;
	}

	public BigDecimal getNewTier1Rate() {
		return newTier1Rate;
	}

	public void setNewTier1Rate(BigDecimal newTier1Rate) {
		this.newTier1Rate = newTier1Rate;
	}

	public BigDecimal getNewTier2CopyVolume() {
		return newTier2CopyVolume;
	}

	public void setNewTier2CopyVolume(BigDecimal newTier2CopyVolume) {
		this.newTier2CopyVolume = newTier2CopyVolume;
	}

	public BigDecimal getNewTier2Rate() {
		return newTier2Rate;
	}

	public void setNewTier2Rate(BigDecimal newTier2Rate) {
		this.newTier2Rate = newTier2Rate;
	}

	public BigDecimal getNewTier3CopyVolume() {
		return newTier3CopyVolume;
	}

	public void setNewTier3CopyVolume(BigDecimal newTier3CopyVolume) {
		this.newTier3CopyVolume = newTier3CopyVolume;
	}

	public BigDecimal getNewTier3Rate() {
		return newTier3Rate;
	}

	public void setNewTier3Rate(BigDecimal newTier3Rate) {
		this.newTier3Rate = newTier3Rate;
	}

	public BigDecimal getNewTier4CopyVolume() {
		return newTier4CopyVolume;
	}

	public void setNewTier4CopyVolume(BigDecimal newTier4CopyVolume) {
		this.newTier4CopyVolume = newTier4CopyVolume;
	}

	public BigDecimal getNewTier4Rate() {
		return newTier4Rate;
	}

	public void setNewTier4Rate(BigDecimal newTier4Rate) {
		this.newTier4Rate = newTier4Rate;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	
	public String getAggregateNumber() {
		return aggregateNumber;
	}

	public void setAggregateNumber(String aggregateNumber) {
		this.aggregateNumber = aggregateNumber;
	}
	
	public String getInvNum() {
		return invNum;
	}

	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}	

	public Canon_E193_CRPriceObj() {
	}
	
	public String getBillIngMeterLbCd() {
		return billIngMeterLbCd;
	}

	public void setBillIngMeterLbCd(String _billIngMeterLbCd) {
		this.billIngMeterLbCd = _billIngMeterLbCd;
	}
	
	public Canon_E193_CRPriceObj(String serialNum, int contractLineId,
			String dateBilledFrom, String dateBilledTo, BigDecimal oldTier1CopyVolume,
			BigDecimal oldTier1Rate,BigDecimal oldTier2CopyVolume,
			BigDecimal oldTier2Rate,BigDecimal oldTier3CopyVolume,
			BigDecimal oldTier3Rate,BigDecimal oldTier4CopyVolume,
			BigDecimal oldTier4Rate,BigDecimal newTier1CopyVolume,BigDecimal newTier1Rate,
			BigDecimal newTier2CopyVolume,BigDecimal newTier2Rate,
			BigDecimal newTier3CopyVolume,BigDecimal newTier3Rate,
			BigDecimal newTier4CopyVolume,BigDecimal newTier4Rate,
			String counterName,String aggregateNumber, String invNum,String _billIngMeterLbCd) {
		super();
		this.serialNum = serialNum;
		this.contractLineId = contractLineId;
		this.dateBilledFrom = dateBilledFrom;
		this.dateBilledTo = dateBilledTo;
		this.oldTier1CopyVolume = oldTier1CopyVolume;
		this.oldTier1Rate = oldTier1Rate;
		this.oldTier2CopyVolume = oldTier2CopyVolume;
		this.oldTier2Rate = oldTier2Rate;
		this.oldTier3CopyVolume = oldTier3CopyVolume;
		this.oldTier3Rate = oldTier3Rate;
		this.oldTier4CopyVolume = oldTier4CopyVolume;
		this.oldTier4Rate = oldTier4Rate;
		this.newTier1CopyVolume = newTier1CopyVolume;
		this.newTier1Rate = newTier1Rate;
		this.newTier2CopyVolume = newTier2CopyVolume;
		this.newTier2Rate = newTier2Rate;
		this.newTier3CopyVolume = newTier3CopyVolume;
		this.newTier3Rate = newTier3Rate;
		this.newTier4CopyVolume = newTier4CopyVolume;
		this.newTier4Rate = newTier4Rate;
		this.counterName = counterName;
		this.aggregateNumber = aggregateNumber;
		this.invNum = invNum;
		this.billIngMeterLbCd = _billIngMeterLbCd;
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(serialNum);
		stream.writeInt(contractLineId);
		stream.writeString(dateBilledFrom);
		stream.writeString(dateBilledTo);
		stream.writeBigDecimal(oldTier1CopyVolume);
		stream.writeBigDecimal(oldTier1Rate);
		stream.writeBigDecimal(oldTier2CopyVolume);
		stream.writeBigDecimal(oldTier2Rate);
		stream.writeBigDecimal(oldTier3CopyVolume);
		stream.writeBigDecimal(oldTier3Rate);
		stream.writeBigDecimal(oldTier4CopyVolume);
		stream.writeBigDecimal(oldTier4Rate);
		stream.writeBigDecimal(newTier1CopyVolume);
		stream.writeBigDecimal(newTier1Rate);
		stream.writeBigDecimal(newTier2CopyVolume);
		stream.writeBigDecimal(newTier2Rate);
		stream.writeBigDecimal(newTier3CopyVolume);
		stream.writeBigDecimal(newTier3Rate);
		stream.writeBigDecimal(newTier4CopyVolume);
		stream.writeBigDecimal(newTier4Rate);
		stream.writeString(counterName);
		stream.writeString(aggregateNumber);
		stream.writeString(invNum);
		stream.writeString(billIngMeterLbCd);
	}

	public void readSQL(SQLInput stream, String type) throws SQLException {
		serialNum = stream.readString();
		contractLineId = stream.readInt();
		dateBilledFrom = stream.readString();
		dateBilledTo = stream.readString();
		oldTier1CopyVolume = stream.readBigDecimal();
		oldTier1Rate = stream.readBigDecimal();
		oldTier2CopyVolume = stream.readBigDecimal();
		oldTier2Rate = stream.readBigDecimal();
		oldTier3CopyVolume = stream.readBigDecimal();
		oldTier3Rate = stream.readBigDecimal();
		oldTier4CopyVolume = stream.readBigDecimal();
		oldTier4Rate = stream.readBigDecimal();
		newTier1CopyVolume = stream.readBigDecimal();
		newTier1Rate = stream.readBigDecimal();
		newTier2CopyVolume = stream.readBigDecimal();
		newTier2Rate = stream.readBigDecimal();
		newTier3CopyVolume = stream.readBigDecimal();
		newTier3Rate = stream.readBigDecimal();
		newTier4CopyVolume = stream.readBigDecimal();
		newTier4Rate = stream.readBigDecimal();
		counterName = stream.readString();
		aggregateNumber = stream.readString();
		invNum = stream.readString();
		billIngMeterLbCd = stream.readString();
	}

	public String getSQLTypeName() {
		return CanonConstants.SCHEMA_NAME + ".CANON_E218_PRICE_CHANGES_REC";
	}

	@Override
	public String toString() {
		return "Canon_E193_CRPriceObj [serialNum=" + serialNum
				+ ", contractLineId=" + contractLineId + ", dateBilledFrom="
				+ dateBilledFrom + ", dateBilledTo=" + dateBilledTo
				+ ", oldTier1CopyVolume=" + oldTier1CopyVolume
				+ ", oldTier1Rate=" + oldTier1Rate + ", oldTier2CopyVolume="
				+ oldTier2CopyVolume + ", oldTier2Rate=" + oldTier2Rate
				+ ", oldTier3CopyVolume=" + oldTier3CopyVolume
				+ ", oldTier3Rate=" + oldTier3Rate + ", oldTier4CopyVolume="
				+ oldTier4CopyVolume + ", oldTier4Rate=" + oldTier4Rate
				+ ", newTier1CopyVolume=" + newTier1CopyVolume
				+ ", newTier1Rate=" + newTier1Rate + ", newTier2CopyVolume="
				+ newTier2CopyVolume + ", newTier2Rate=" + newTier2Rate
				+ ", newTier3CopyVolume=" + newTier3CopyVolume
				+ ", newTier3Rate=" + newTier3Rate + ", newTier4CopyVolume="
				+ newTier4CopyVolume + ", newTier4Rate=" + newTier4Rate
				+ ", counterName=" + counterName + ", aggregateNumber="
				+ aggregateNumber + ", invNum=" + invNum
				+ ", billIngMeterLbCd=" + billIngMeterLbCd + "]";
	}
	
	

}
