package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ContractInfoRec implements SQLData {

	public CanonE307ContractInfoRec() {
		// TODO Auto-generated constructor stub
	}
	private String strCustName;
	private String strContractNum;
	private String strContractType;
	private String strCHeaderSDate;
	private String strCHeaderEDate;
	private String strCLineSDate;
	private String strCLineEDate;
	private String strCHeaderStatus;
	private String strCLineStatus;

	public CanonE307ContractInfoRec(String strCustName,
			String strContractNum, String strContractType,
			String strCHeaderSDate, String strCHeaderEDate,
			String strCLineSDate, String strCLineEDate,
			String strCHeaderStatus, String strCLineStatus) {
		super();
		this.strCustName = strCustName;
		this.strContractNum = strContractNum;
		this.strContractType = strContractType;
		this.strCHeaderSDate = strCHeaderSDate;
		this.strCHeaderEDate = strCHeaderEDate;
		this.strCLineSDate = strCLineSDate;
		this.strCLineEDate = strCLineEDate;
		this.strCHeaderStatus = strCHeaderStatus;
		this.strCLineStatus = strCLineStatus;
	}
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CONTRACT_REC";
	}
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strCustName = stream.readString();
		strContractNum = stream.readString();
		strContractType = stream.readString();
		strCHeaderSDate = stream.readString();
		strCHeaderEDate = stream.readString();
		strCLineSDate = stream.readString();
		strCLineEDate = stream.readString();
		strCHeaderStatus = stream.readString();
		strCLineStatus = stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strCustName);
		stream.writeString(strContractNum);
		stream.writeString(strContractType);
		stream.writeString(strCHeaderSDate);
		stream.writeString(strCHeaderEDate);
		stream.writeString(strCLineSDate);
		stream.writeString(strCLineEDate);
		stream.writeString(strCHeaderStatus);
		stream.writeString(strCLineStatus);
	}
	public String getStrCustName() {
		return strCustName;
	}
	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
	}
	public String getStrContractNum() {
		return strContractNum;
	}
	public void setStrContractNum(String strContractNum) {
		this.strContractNum = strContractNum;
	}
	public String getStrContractType() {
		return strContractType;
	}
	public void setStrContractType(String strContractType) {
		this.strContractType = strContractType;
	}
	public String getStrCHeaderSDate() {
		return strCHeaderSDate;
	}
	public void setStrCHeaderSDate(String strCHeaderSDate) {
		this.strCHeaderSDate = strCHeaderSDate;
	}
	public String getStrCHeaderEDate() {
		return strCHeaderEDate;
	}
	public void setStrCHeaderEDate(String strCHeaderEDate) {
		this.strCHeaderEDate = strCHeaderEDate;
	}
	public String getStrCLineSDate() {
		return strCLineSDate;
	}
	public void setStrCLineSDate(String strCLineSDate) {
		this.strCLineSDate = strCLineSDate;
	}
	public String getStrCLineEDate() {
		return strCLineEDate;
	}
	public void setStrCLineEDate(String strCLineEDate) {
		this.strCLineEDate = strCLineEDate;
	}
	public String getStrCHeaderStatus() {
		return strCHeaderStatus;
	}
	public void setStrCHeaderStatus(String strCHeaderStatus) {
		this.strCHeaderStatus = strCHeaderStatus;
	}
	public String getStrCLineStatus() {
		return strCLineStatus;
	}
	public void setStrCLineStatus(String strCLineStatus) {
		this.strCLineStatus = strCLineStatus;
	}
	@Override
	public String toString() {
		return "CanonE307ContractInfoRec [strCustName=" + strCustName
				+ ", strContractNum=" + strContractNum + ", strContractType="
				+ strContractType + ", strCHeaderSDate=" + strCHeaderSDate
				+ ", strCHeaderEDate=" + strCHeaderEDate + ", strCLineSDate="
				+ strCLineSDate + ", strCLineEDate=" + strCLineEDate
				+ ", strCHeaderStatus=" + strCHeaderStatus
				+ ", strCLineStatus=" + strCLineStatus + "]";
	}
	
}
