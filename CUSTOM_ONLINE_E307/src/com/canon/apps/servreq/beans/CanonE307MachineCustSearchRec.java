package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307MachineCustSearchRec implements SQLData{
	private String strSerialTagNumber="";
	private String strSolutionName="";
	private String strAccountNumber="";
	private String strCustomerName="";
	private String strAddress="";
	private String strCity="";
	private String strState="";
	private String strPostalCode="";
	private String strPhone1="";
	private String strPhone2="";
	private String strPhone3="";
	private String strSortBy="";
	private String strSortOrder="";
	
	
	public CanonE307MachineCustSearchRec() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStrSerialTagNumber() {
		return strSerialTagNumber;
	}

	public void setStrSerialTagNumber(String strSerialTagNumber) {
		this.strSerialTagNumber = strSerialTagNumber;
	}

	public String getStrSolutionName() {
		return strSolutionName;
	}

	public void setStrSolutionName(String strSolutionName) {
		this.strSolutionName = strSolutionName;
	}

	public String getStrAccountNumber() {
		return strAccountNumber;
	}

	public void setStrAccountNumber(String strAccountNumber) {
		this.strAccountNumber = strAccountNumber;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}

	public String getStrPostalCode() {
		return strPostalCode;
	}

	public void setStrPostalCode(String strPostalCode) {
		this.strPostalCode = strPostalCode;
	}

	public String getStrPhone1() {
		return strPhone1;
	}

	public void setStrPhone1(String strPhone1) {
		this.strPhone1 = strPhone1;
	}

	public String getStrPhone2() {
		return strPhone2;
	}

	public void setStrPhone2(String strPhone2) {
		this.strPhone2 = strPhone2;
	}

	public String getStrPhone3() {
		return strPhone3;
	}

	public void setStrPhone3(String strPhone3) {
		this.strPhone3 = strPhone3;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public String getStrSortBy() {
		return strSortBy;
	}

	public void setStrSortBy(String strSortBy) {
		this.strSortBy = strSortBy;
	}

	public String getStrSortOrder() {
		return strSortOrder;
	}

	public void setStrSortOrder(String strSortOrder) {
		this.strSortOrder = strSortOrder;
	}

	public CanonE307MachineCustSearchRec(
			String strSerialTagNumber,
			String strSolutionName,
			String strAccountNumber,
			String strCustomerName,
			String strAddress,
			String strCity,
			String strState,
			String strPostalCode,
			String strPhone1,
			String strPhone2,
			String strPhone3,
			String strSortBy,
			String strSortOrder) {
		this.strSerialTagNumber=strSerialTagNumber;
		this.strSolutionName=strSolutionName;
		this.strAccountNumber=strAccountNumber;
		this.strCustomerName=strCustomerName;
		this.strAddress=strAddress;
		this.strCity=strCity;
		this.strState = strState;
		this.strPostalCode=strPostalCode;
		this.strPhone1=strPhone1;
		this.strPhone2= strPhone2;
		this.strPhone3=strPhone3;
		this.strSortBy=strSortBy;
		this.strSortOrder = strSortOrder;
	}

	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SEARCH_SER_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strSerialTagNumber = stream.readString();
		strSolutionName=stream.readString();
		strAccountNumber=stream.readString();
		strCustomerName=stream.readString();
		strAddress=stream.readString();
		strCity=stream.readString();
		strState=stream.readString();
		strPostalCode=stream.readString();
		strPhone1=stream.readString();
		strPhone2=stream.readString();
		strPhone3=stream.readString();
		strSortBy=stream.readString();
		strSortOrder=stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strSerialTagNumber);
		stream.writeString(strSolutionName);
		stream.writeString(strAccountNumber);
		stream.writeString(strCustomerName);
		stream.writeString(strAddress);
		stream.writeString(strCity);
		stream.writeString(strState);
		stream.writeString(strPostalCode);
		stream.writeString(strPhone1);
		stream.writeString(strPhone2);
		stream.writeString(strPhone3);
		stream.writeString(strSortBy);
		stream.writeString(strSortOrder);
	}

	@Override
	public String toString() {
		return "CanonE307MachineCustSearchRec [strSerialTagNumber="
				+ strSerialTagNumber + ", strSolutionName=" + strSolutionName
				+ ", strAccountNumber=" + strAccountNumber
				+ ", strCustomerName=" + strCustomerName + ", strAddress="
				+ strAddress + ", strCity=" + strCity + ", strState="
				+ strState + ", strPostalCode=" + strPostalCode
				+ ", strPhone1=" + strPhone1 + ", strPhone2=" + strPhone2
				+ ", strPhone3=" + strPhone3 + ", strSortBy=" + strSortBy
				+ ", strSortOrder=" + strSortOrder + "]";
	}

}
