package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307HardHoldInfoRec implements SQLData{

	private String strModel;
	private String strColctnMangr;
	private String strCEmailAddress;
	private String strDutyMngr;
	private String strTextEmailAddrs;
	private String strCustomerName;
	private String strCustAddress;
	private String strContactName;
	private String strContTelNum;
	private String strContTelExt;
	private String strEmailAddress;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strAttribute4;
	private String strAttribute5;	
	
	public CanonE307HardHoldInfoRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307HardHoldInfoRec(String strModel, String strColctnMangr,
			String strCEmailAddress, String strDutyMngr,
			String strTextEmailAddrs, String strCustomerName,
			String strCustAddress, String strContactName, String strContTelNum,
			String strContTelExt, String strEmailAddress, String strAttribute1,
			String strAttribute2, String strAttribute3, String strAttribute4,
			String strAttribute5) {
		super();
		this.strModel = strModel;
		this.strColctnMangr = strColctnMangr;
		this.strCEmailAddress = strCEmailAddress;
		this.strDutyMngr = strDutyMngr;
		this.strTextEmailAddrs = strTextEmailAddrs;
		this.strCustomerName = strCustomerName;
		this.strCustAddress = strCustAddress;
		this.strContactName = strContactName;
		this.strContTelNum = strContTelNum;
		this.strContTelExt = strContTelExt;
		this.strEmailAddress = strEmailAddress;
		this.strAttribute1 = strAttribute1;
		this.strAttribute2 = strAttribute2;
		this.strAttribute3 = strAttribute3;
		this.strAttribute4 = strAttribute4;
		this.strAttribute5 = strAttribute5;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return CanonConstants.SCHEMA_NAME+".CANON_E307_HARD_HOLD_INFO_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		strModel = stream.readString();
		strColctnMangr = stream.readString();
		strCEmailAddress = stream.readString();
		strDutyMngr = stream.readString();
		strTextEmailAddrs = stream.readString();
		strCustomerName = stream.readString();
		strCustAddress = stream.readString();
		strContactName = stream.readString();
		strContTelNum = stream.readString();
		strContTelExt = stream.readString();
		strEmailAddress = stream.readString();
		strAttribute1 = stream.readString();
		strAttribute2 = stream.readString();
		strAttribute3 = stream.readString();
		strAttribute4 = stream.readString();
		strAttribute5 = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(strModel);
		stream.writeString(strColctnMangr);
		stream.writeString(strColctnMangr);
		stream.writeString(strDutyMngr);
		stream.writeString(strTextEmailAddrs);
		stream.writeString(strCustomerName);
		stream.writeString(strCustAddress);
		stream.writeString(strContactName);
		stream.writeString(strContTelNum);
		stream.writeString(strContTelExt);
		stream.writeString(strEmailAddress);
		stream.writeString(strAttribute1);
		stream.writeString(strAttribute2);
		stream.writeString(strAttribute3);
		stream.writeString(strAttribute4);
		stream.writeString(strAttribute5);
	}
	
	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrColctnMangr() {
		return strColctnMangr;
	}

	public void setStrColctnMangr(String strColctnMangr) {
		this.strColctnMangr = strColctnMangr;
	}

	public String getStrCEmailAddress() {
		return strCEmailAddress;
	}

	public void setStrCEmailAddress(String strCEmailAddress) {
		this.strCEmailAddress = strCEmailAddress;
	}

	public String getStrDutyMngr() {
		return strDutyMngr;
	}

	public void setStrDutyMngr(String strDutyMngr) {
		this.strDutyMngr = strDutyMngr;
	}

	public String getStrTextEmailAddrs() {
		return strTextEmailAddrs;
	}

	public void setStrTextEmailAddrs(String strTextEmailAddrs) {
		this.strTextEmailAddrs = strTextEmailAddrs;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public String getStrCustAddress() {
		return strCustAddress;
	}

	public void setStrCustAddress(String strCustAddress) {
		this.strCustAddress = strCustAddress;
	}

	public String getStrContactName() {
		return strContactName;
	}

	public void setStrContactName(String strContactName) {
		this.strContactName = strContactName;
	}

	public String getStrContTelNum() {
		return strContTelNum;
	}

	public void setStrContTelNum(String strContTelNum) {
		this.strContTelNum = strContTelNum;
	}

	public String getStrContTelExt() {
		return strContTelExt;
	}

	public void setStrContTelExt(String strContTelExt) {
		this.strContTelExt = strContTelExt;
	}

	public String getStrEmailAddress() {
		return strEmailAddress;
	}

	public void setStrEmailAddress(String strEmailAddress) {
		this.strEmailAddress = strEmailAddress;
	}

	public String getStrAttribute1() {
		return strAttribute1;
	}

	public void setStrAttribute1(String strAttribute1) {
		this.strAttribute1 = strAttribute1;
	}

	public String getStrAttribute2() {
		return strAttribute2;
	}

	public void setStrAttribute2(String strAttribute2) {
		this.strAttribute2 = strAttribute2;
	}

	public String getStrAttribute3() {
		return strAttribute3;
	}

	public void setStrAttribute3(String strAttribute3) {
		this.strAttribute3 = strAttribute3;
	}

	public String getStrAttribute4() {
		return strAttribute4;
	}

	public void setStrAttribute4(String strAttribute4) {
		this.strAttribute4 = strAttribute4;
	}

	public String getStrAttribute5() {
		return strAttribute5;
	}

	public void setStrAttribute5(String strAttribute5) {
		this.strAttribute5 = strAttribute5;
	}
	

	@Override
	public String toString() {
		return "CanonE307HardHoldInfoRec [strModel=" + strModel
				+ ", strColctnMangr=" + strColctnMangr + ", strCEmailAddress="
				+ strCEmailAddress + ", strDutyMngr=" + strDutyMngr
				+ ", strTextEmailAddrs=" + strTextEmailAddrs
				+ ", strCustomerName=" + strCustomerName + ", strCustAddress="
				+ strCustAddress + ", strContactName=" + strContactName
				+ ", strContTelNum=" + strContTelNum + ", strContTelExt="
				+ strContTelExt + ", strEmailAddress=" + strEmailAddress
				+ ", strAttribute1=" + strAttribute1 + ", strAttribute2="
				+ strAttribute2 + ", strAttribute3=" + strAttribute3
				+ ", strAttribute4=" + strAttribute4 + ", strAttribute5="
				+ strAttribute5 + "]";
	}
	
}
