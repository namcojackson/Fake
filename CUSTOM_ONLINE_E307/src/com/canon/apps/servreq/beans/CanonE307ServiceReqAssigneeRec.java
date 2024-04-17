package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqAssigneeRec implements SQLData {

	public CanonE307ServiceReqAssigneeRec() {
		// TODO Auto-generated constructor stub
	}
	private String strAssignee="";
	private String strAssigneeCode="";
	private String strBranch="";
	
	public CanonE307ServiceReqAssigneeRec(String strAssignee,
			String strAssigneeCode, String strBranch) {
		super();
		this.strAssignee = strAssignee;
		this.strAssigneeCode = strAssigneeCode;
		this.strBranch = strBranch;
	}

	public String getStrAssignee() {
		return strAssignee;
	}

	public void setStrAssignee(String strAssignee) {
		this.strAssignee = strAssignee;
	}

	public String getStrAssigneeCode() {
		return strAssigneeCode;
	}

	public void setStrAssigneeCode(String strAssigneeCode) {
		this.strAssigneeCode = strAssigneeCode;
	}

	public String getStrBranch() {
		return strBranch;
	}

	public void setStrBranch(String strBranch) {
		this.strBranch = strBranch;
	}

	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_RES_LOV_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strAssignee = stream.readString();
		strAssigneeCode= stream.readString();
		strBranch = stream.readString();
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strAssignee);
		stream.writeString(strAssigneeCode);
		stream.writeString(strBranch);
	}

}
