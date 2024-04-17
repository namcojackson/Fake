package com.canon.oracle.custapp.custinq.beans;

public class Canon_E193_AccountDetails {
	
	private String acctId;
	private String acctName;
	private String acctNum;
	private String serialNum;
	private String poNum;
	
	private String model;
	private String trxType; 
	
	/*private String billType;*/ 
	
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	/*public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}*/
	
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	
	public String getPoNum() {
		return poNum;
	}
	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}
	@Override
	public String toString() {
		return "Canon_E193_AccountDetails [acctId=" + acctId + ", acctName="
				+ acctName + ", acctNum=" + acctNum + ", serialNum="
				+ serialNum + ", model=" + model /*+ ", billType=" + billType*/
				+ ", poNum=" + poNum 
				+ "]";
	}
	
}
