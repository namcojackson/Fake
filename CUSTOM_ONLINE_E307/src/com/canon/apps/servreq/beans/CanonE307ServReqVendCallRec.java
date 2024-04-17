package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServReqVendCallRec implements SQLData{
	private String strSerNum;
	private String strTagNum;
	private String strCustNm;
	private String strSrNum;
	private String srTskNum;
	private String strFutureDt;
	private String strTskTpe;
	private String strAssignee;
	private String strModel;
	private String strBranch;
	private String strStatus;
	private String strPostalCd;
	private String strCntryCd;
	
	public CanonE307ServReqVendCallRec(){
		
	}

	public CanonE307ServReqVendCallRec(String strSerNum, String strTagNum,
			String strCustNm, String strSrNum, String srTskNum,
			String strFutureDt, String strTskTpe, String strAssignee,
			String strModel, String strBranch, String strStatus,
			String strPostalCd, String strCntryCd) {
		super();
		this.strSerNum = strSerNum;
		this.strTagNum = strTagNum;
		this.strCustNm = strCustNm;
		this.strSrNum = strSrNum;
		this.srTskNum = srTskNum;
		this.strFutureDt = strFutureDt;
		this.strTskTpe = strTskTpe;
		this.strAssignee = strAssignee;
		this.strModel = strModel;
		this.strBranch = strBranch;
		this.strStatus = strStatus;
		this.strPostalCd = strPostalCd;
		this.strCntryCd = strCntryCd;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_SR_DETL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strSerNum = stream.readString();
		strTagNum = stream.readString();
		strCustNm = stream.readString();
		strSrNum = stream.readString();
		srTskNum = stream.readString();
		strFutureDt = stream.readString();
		strTskTpe = stream.readString();
		strAssignee = stream.readString();
		strModel = stream.readString();
		strBranch = stream.readString();
		strStatus = stream.readString();
		strPostalCd = stream.readString();
		strCntryCd = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strSerNum);
		stream.writeString(strTagNum);
		stream.writeString(strCustNm);
		stream.writeString(strSrNum);
		stream.writeString(srTskNum);
		stream.writeString(strFutureDt);
		stream.writeString(strTskTpe);
		stream.writeString(strAssignee);
		stream.writeString(strModel);
		stream.writeString(strBranch);
		stream.writeString(strStatus);
		stream.writeString(strPostalCd);
		stream.writeString(strCntryCd);
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

	public String getStrCustNm() {
		return strCustNm;
	}

	public void setStrCustNm(String strCustNm) {
		this.strCustNm = strCustNm;
	}

	public String getStrSrNum() {
		return strSrNum;
	}

	public void setStrSrNum(String strSrNum) {
		this.strSrNum = strSrNum;
	}

	public String getSrTskNum() {
		return srTskNum;
	}

	public void setSrTskNum(String srTskNum) {
		this.srTskNum = srTskNum;
	}

	public String getStrFutureDt() {
		return strFutureDt;
	}

	public void setStrFutureDt(String strFutureDt) {
		this.strFutureDt = strFutureDt;
	}

	public String getStrTskTpe() {
		return strTskTpe;
	}

	public void setStrTskTpe(String strTskTpe) {
		this.strTskTpe = strTskTpe;
	}

	public String getStrAssignee() {
		return strAssignee;
	}

	public void setStrAssignee(String strAssignee) {
		this.strAssignee = strAssignee;
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

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public String getStrPostalCd() {
		return strPostalCd;
	}

	public void setStrPostalCd(String strPostalCd) {
		this.strPostalCd = strPostalCd;
	}

	public String getStrCntryCd() {
		return strCntryCd;
	}

	public void setStrCntryCd(String strCntryCd) {
		this.strCntryCd = strCntryCd;
	}
	
	
}
