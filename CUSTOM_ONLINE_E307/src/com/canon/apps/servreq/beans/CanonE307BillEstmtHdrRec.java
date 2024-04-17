package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307BillEstmtHdrRec implements SQLData{

	public CanonE307BillEstmtHdrRec() {
		// TODO Auto-generated constructor stub
	}
	private String strCustName;
	private String strCustAddress;
	private String strCustCity;
	private String strCustState;
	private String strCustZip;
	private String strCustAccNum;
	private String strCustContact;
	private String strCustEmail;
	private String strSrRqstNum;
	private String strTgNum;
	private String strMdl;
	private String strSrlNum;
	private String strSolNm;
	private String strCntryCd;
	
	public CanonE307BillEstmtHdrRec(String strCustName, String strCustAddress,
			String strCustCity, String strCustState, String strCustZip,
			String strCustAccNum, String strCustContact, String strCustEmail,
			String strSrRqstNum, String strTgNum, String strMdl,
			String strSrlNum, String strSolNm, String strCntryCd) {
		super();
		this.strCustName = strCustName;
		this.strCustAddress = strCustAddress;
		this.strCustCity = strCustCity;
		this.strCustState = strCustState;
		this.strCustZip = strCustZip;
		this.strCustAccNum = strCustAccNum;
		this.strCustContact = strCustContact;
		this.strCustEmail = strCustEmail;
		this.strSrRqstNum = strSrRqstNum;
		this.strTgNum = strTgNum;
		this.strMdl = strMdl;
		this.strSrlNum = strSrlNum;
		this.strSolNm = strSolNm;
		this.strCntryCd = strCntryCd;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_EST_HDR_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strCustName = stream.readString();
		strCustAddress = stream.readString();
		strCustCity = stream.readString();
		strCustState = stream.readString();
		strCustZip = stream.readString();
		strCustAccNum = stream.readString();
		strCustContact = stream.readString();
		strCustEmail = stream.readString();
		strSrRqstNum = stream.readString();
		strTgNum = stream.readString();
		strMdl = stream.readString();
		strSrlNum = stream.readString();
		strSolNm = stream.readString();
		strCntryCd = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strCustName);
		stream.writeString(strCustAddress);
		stream.writeString(strCustCity);
		stream.writeString(strCustState);
		stream.writeString(strCustZip);
		stream.writeString(strCustAccNum);
		stream.writeString(strCustContact);
		stream.writeString(strCustEmail);
		stream.writeString(strSrRqstNum);
		stream.writeString(strTgNum);
		stream.writeString(strMdl);
		stream.writeString(strSrlNum);
		stream.writeString(strSolNm);
		stream.writeString(strCntryCd);
	}

	public String getStrCustName() {
		return strCustName;
	}

	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
	}

	public String getStrCustAddress() {
		return strCustAddress;
	}

	public void setStrCustAddress(String strCustAddress) {
		this.strCustAddress = strCustAddress;
	}

	public String getStrCustCity() {
		return strCustCity;
	}

	public void setStrCustCity(String strCustCity) {
		this.strCustCity = strCustCity;
	}

	public String getStrCustState() {
		return strCustState;
	}

	public void setStrCustState(String strCustState) {
		this.strCustState = strCustState;
	}

	public String getStrCustZip() {
		return strCustZip;
	}

	public void setStrCustZip(String strCustZip) {
		this.strCustZip = strCustZip;
	}

	public String getStrCustAccNum() {
		return strCustAccNum;
	}

	public void setStrCustAccNum(String strCustAccNum) {
		this.strCustAccNum = strCustAccNum;
	}

	public String getStrCustContact() {
		return strCustContact;
	}

	public void setStrCustContact(String strCustContact) {
		this.strCustContact = strCustContact;
	}

	public String getStrCustEmail() {
		return strCustEmail;
	}

	public void setStrCustEmail(String strCustEmail) {
		this.strCustEmail = strCustEmail;
	}

	public String getStrSrRqstNum() {
		return strSrRqstNum;
	}

	public void setStrSrRqstNum(String strSrRqstNum) {
		this.strSrRqstNum = strSrRqstNum;
	}

	public String getStrTgNum() {
		return strTgNum;
	}

	public void setStrTgNum(String strTgNum) {
		this.strTgNum = strTgNum;
	}

	public String getStrMdl() {
		return strMdl;
	}

	public void setStrMdl(String strMdl) {
		this.strMdl = strMdl;
	}

	public String getStrSrlNum() {
		return strSrlNum;
	}

	public void setStrSrlNum(String strSrlNum) {
		this.strSrlNum = strSrlNum;
	}

	public String getStrSolNm() {
		return strSolNm;
	}

	public void setStrSolNm(String strSolNm) {
		this.strSolNm = strSolNm;
	}

	public String getStrCntryCd() {
		return strCntryCd;
	}

	public void setStrCntryCd(String strCntryCd) {
		this.strCntryCd = strCntryCd;
	}

}
