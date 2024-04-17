package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307UGWErrCodeRec implements SQLData{

	private String strSerNum;
	private String strTagNum;
	private String strMachMstrPk;
	private String strCustName;
	private String strErrCd;
	private String strErrDt;
	private String strModel;
	private String strBranch;
	private String strPostalCd;
	private String strCtryCd;

	public CanonE307UGWErrCodeRec(){
		// TODO Auto-generated constructor stub
	}

	public CanonE307UGWErrCodeRec(String strSerNum, String strTagNum,
			String strMachMstrPk, String strCustName, String strErrCd,
			String strErrDt, String strModel, String strBranch,
			String strPostalCd, String strCtryCd) {
		super();
		this.strSerNum = strSerNum;
		this.strTagNum = strTagNum;
		this.strMachMstrPk = strMachMstrPk;
		this.strCustName = strCustName;
		this.strErrCd = strErrCd;
		this.strErrDt = strErrDt;
		this.strModel = strModel;
		this.strBranch = strBranch;
		this.strPostalCd = strPostalCd;
		this.strCtryCd = strCtryCd;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_UGW_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strSerNum = stream.readString();
		strTagNum = stream.readString();
		strMachMstrPk=stream.readString();
		strCustName = stream.readString();
		strErrCd = stream.readString();
		strErrDt = stream.readString();
		strModel = stream.readString();
		strBranch = stream.readString();
		strPostalCd = stream.readString();
		strCtryCd = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strSerNum);
		stream.writeString(strTagNum);
		stream.writeString(strMachMstrPk);
		stream.writeString(strCustName);
		stream.writeString(strErrCd);
		stream.writeString(strErrDt);
		stream.writeString(strModel);
		stream.writeString(strBranch);
		stream.writeString(strPostalCd);
		stream.writeString(strCtryCd);
	}

	public String getStrSerNum() {
		return strSerNum;
	}

	public void setStrSerNum(String strSerNum) {
		this.strSerNum = strSerNum;
	}

	public String getStrTagNum() {
		return strTagNum;
	}

	public void setStrTagNum(String strTagNum) {
		this.strTagNum = strTagNum;
	}

	public String getStrMachMstrPk() {
		return strMachMstrPk;
	}

	public void setStrMachMstrPk(String strMachMstrPk) {
		this.strMachMstrPk = strMachMstrPk;
	}

	public String getStrCustName() {
		return strCustName;
	}

	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
	}

	public String getStrErrCd() {
		return strErrCd;
	}

	public void setStrErrCd(String strErrCd) {
		this.strErrCd = strErrCd;
	}

	public String getStrErrDt() {
		return strErrDt;
	}

	public void setStrErrDt(String strErrDt) {
		this.strErrDt = strErrDt;
	}

	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrBranch() {
		return strBranch;
	}

	public void setStrBranch(String strBranch) {
		this.strBranch = strBranch;
	}

	public String getStrPostalCd() {
		return strPostalCd;
	}

	public void setStrPostalCd(String strPostalCd) {
		this.strPostalCd = strPostalCd;
	}

	public String getStrCtryCd() {
		return strCtryCd;
	}

	public void setStrCtryCd(String strCtryCd) {
		this.strCtryCd = strCtryCd;
	}

}
