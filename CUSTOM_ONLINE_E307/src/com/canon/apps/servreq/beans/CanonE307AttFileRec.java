package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307AttFileRec implements SQLData{

	private String strBusinessId;
	private String strAttGrpTxt;
	private int fileId;
	private String strfileName;
	
	public CanonE307AttFileRec() {
		// TODO Auto-generated constructor stub
	}

	public CanonE307AttFileRec(String strBusinessId, String strAttGrpTxt,
			int fileId, String strfileName) {
		super();
		this.strBusinessId = strBusinessId;
		this.strAttGrpTxt = strAttGrpTxt;
		this.fileId = fileId;
		this.strfileName = strfileName;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strBusinessId = stream.readString();
		strAttGrpTxt = stream.readString();
		fileId = stream.readInt();
		strfileName = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strBusinessId);
		stream.writeString(strAttGrpTxt);
		stream.writeInt(fileId);
		stream.writeString(strfileName);
	}
	
	public String getStrBusinessId() {
		return strBusinessId;
	}

	public void setStrBusinessId(String strBusinessId) {
		this.strBusinessId = strBusinessId;
	}

	public String getStrAttGrpTxt() {
		return strAttGrpTxt;
	}

	public void setStrAttGrpTxt(String strAttGrpTxt) {
		this.strAttGrpTxt = strAttGrpTxt;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getStrfileName() {
		return strfileName;
	}

	public void setStrfileName(String strfileName) {
		this.strfileName = strfileName;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_ATT_FILE_REC";
	}
}
