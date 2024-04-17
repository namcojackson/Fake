package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307SRHistHdrLvlInfoRec implements SQLData{

	private String strModel;
	private String strSerNum;
	private String strBranch;
	private String strMachMngr;
	private String strCustName;
	private String strAddress;
	private String strCity;
	private String strState;
	private String strPostalCd;
	private String strContactNm;
	private String strCntactPhone;
	private String strEmailAddress;
	
	
	public CanonE307SRHistHdrLvlInfoRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307SRHistHdrLvlInfoRec(String strModel, String strSerNum,
			String strBranch, String strMachMngr, String strCustName,
			String strAddress, String strCity, String strState,
			String strPostalCd, String strContactNm, String strCntactPhone,
			String strEmailAddress) {
		super();
		this.strModel = strModel;
		this.strSerNum = strSerNum;
		this.strBranch = strBranch;
		this.strMachMngr = strMachMngr;
		this.strCustName = strCustName;
		this.strAddress = strAddress;
		this.strCity = strCity;
		this.strState = strState;
		this.strPostalCd = strPostalCd;
		this.strContactNm = strContactNm;
		this.strCntactPhone = strCntactPhone;
		this.strEmailAddress = strEmailAddress;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_HDR_INFO_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strModel         =stream.readString();
		strSerNum         =stream.readString();
		strBranch         =stream.readString();
		strMachMngr         =stream.readString();
		strCustName         =stream.readString();
		strAddress         =stream.readString();
		strCity         =stream.readString();
		strState         =stream.readString();
		strPostalCd         =stream.readString();
		strContactNm         =stream.readString();
		strCntactPhone         =stream.readString();
		strEmailAddress         =stream.readString();		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(   strModel   );
		stream.writeString(   strSerNum   );
		stream.writeString(   strBranch   );
		stream.writeString(   strMachMngr   );
		stream.writeString(   strCustName   );
		stream.writeString(   strAddress   );
		stream.writeString(   strCity   );
		stream.writeString(   strState   );
		stream.writeString(   strPostalCd   );
		stream.writeString(   strContactNm   );
		stream.writeString(   strCntactPhone   );
		stream.writeString(   strEmailAddress   );		
	}

	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrBranch() {
		return strBranch;
	}

	public void setStrBranch(String strBranch) {
		this.strBranch = strBranch;
	}

	public String getStrMachMngr() {
		return strMachMngr;
	}

	public void setStrMachMngr(String strMachMngr) {
		this.strMachMngr = strMachMngr;
	}

	public String getStrCustName() {
		return strCustName;
	}

	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
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

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public String getStrPostalCd() {
		return strPostalCd;
	}

	public void setStrPostalCd(String strPostalCd) {
		this.strPostalCd = strPostalCd;
	}

	public String getStrContactNm() {
		return strContactNm;
	}

	public void setStrContactNm(String strContactNm) {
		this.strContactNm = strContactNm;
	}

	public String getStrCntactPhone() {
		return strCntactPhone;
	}

	public void setStrCntactPhone(String strCntactPhone) {
		this.strCntactPhone = strCntactPhone;
	}

	public String getStrEmailAddress() {
		return strEmailAddress;
	}

	public void setStrEmailAddress(String strEmailAddress) {
		this.strEmailAddress = strEmailAddress;
	}

	@Override
	public String toString() {
		return "CanonE307SRHistHdrLvlInfoRec [strModel=" + strModel
				+ ", strSerNum=" + strSerNum + ", strBranch=" + strBranch
				+ ", strMachMngr=" + strMachMngr + ", strCustName="
				+ strCustName + ", strAddress=" + strAddress + ", strCity="
				+ strCity + ", strState=" + strState + ", strPostalCd="
				+ strPostalCd + ", strContactNm=" + strContactNm
				+ ", strCntactPhone=" + strCntactPhone + ", strEmailAddress="
				+ strEmailAddress + "]";
	}
	

}
