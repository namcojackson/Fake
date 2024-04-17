package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307TaskDtlInfoRec implements SQLData {

	public CanonE307TaskDtlInfoRec() {
		// TODO Auto-generated constructor stub
	}
	
	private String strFsrNum;
	private String strSvcTskNum;
	private String strTskStsCd;
	private String strTskSTsNm;
	private String strAssigneeCd;
	private String strAssignee;
	private String strAssigneeTpCd;
	private String strLastUpdateDate;
	
	
	public CanonE307TaskDtlInfoRec(String strFsrNum, String strSvcTskNum,
			String strTskStsCd, String strTskSTsNm, String strAssigneeCd,
			String strAssignee, String strAssigneeTpCd, String strLastUpdateDate) {
		super();
		this.strFsrNum = strFsrNum;
		this.strSvcTskNum = strSvcTskNum;
		this.strTskStsCd = strTskStsCd;
		this.strTskSTsNm = strTskSTsNm;
		this.strAssigneeCd = strAssigneeCd;
		this.strAssignee = strAssignee;
		this.strAssigneeTpCd = strAssigneeTpCd;
		this.strLastUpdateDate = strLastUpdateDate;
	}

	public String getStrFsrNum() {
		return strFsrNum;
	}

	public void setStrFsrNum(String strFsrNum) {
		this.strFsrNum = strFsrNum;
	}

	public String getStrSvcTskNum() {
		return strSvcTskNum;
	}

	public void setStrSvcTskNum(String strSvcTskNum) {
		this.strSvcTskNum = strSvcTskNum;
	}

	public String getStrTskStsCd() {
		return strTskStsCd;
	}

	public void setStrTskStsCd(String strTskStsCd) {
		this.strTskStsCd = strTskStsCd;
	}

	public String getStrTskSTsNm() {
		return strTskSTsNm;
	}

	public void setStrTskSTsNm(String strTskSTsNm) {
		this.strTskSTsNm = strTskSTsNm;
	}

	public String getStrAssigneeCd() {
		return strAssigneeCd;
	}

	public void setStrAssigneeCd(String strAssigneeCd) {
		this.strAssigneeCd = strAssigneeCd;
	}

	public String getStrAssignee() {
		return strAssignee;
	}

	public void setStrAssignee(String strAssignee) {
		this.strAssignee = strAssignee;
	}

	public String getStrAssigneeTpCd() {
		return strAssigneeTpCd;
	}

	public void setStrAssigneeTpCd(String strAssigneeTpCd) {
		this.strAssigneeTpCd = strAssigneeTpCd;
	}

	public String getStrLastUpdateDate() {
		return strLastUpdateDate;
	}

	public void setStrLastUpdateDate(String strLastUpdateDate) {
		this.strLastUpdateDate = strLastUpdateDate;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_DTL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strFsrNum = stream.readString();
		strSvcTskNum = stream.readString();
		strTskStsCd = stream.readString();
		strTskSTsNm = stream.readString();
		strAssigneeCd = stream.readString();
		strAssignee = stream.readString();
		strAssigneeTpCd = stream.readString();
		strLastUpdateDate = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strFsrNum);
		stream.writeString(strSvcTskNum);
		stream.writeString(strTskStsCd);
		stream.writeString(strTskSTsNm);
		stream.writeString(strAssigneeCd);
		stream.writeString(strAssignee);
		stream.writeString(strAssigneeTpCd);
		stream.writeString(strLastUpdateDate);
	}

	@Override
	public String toString() {
		return "CanonE307TaskDtlInfoRec [strFsrNum=" + strFsrNum
				+ ", strSvcTskNum=" + strSvcTskNum + ", strTskStsCd="
				+ strTskStsCd + ", strTskSTsNm=" + strTskSTsNm
				+ ", strAssigneeCd=" + strAssigneeCd + ", strAssignee="
				+ strAssignee + ", strAssigneeTpCd=" + strAssigneeTpCd
				+ ", strLastUpdateDate=" + strLastUpdateDate + "]";
	}
	
	
}
