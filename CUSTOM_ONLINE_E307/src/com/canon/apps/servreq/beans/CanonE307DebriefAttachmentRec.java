package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefAttachmentRec implements SQLData {
	private String strAttachSourceId;
	private String strDebriefNumber;
	private int attachmentId;
	private String strAttachFileName;
	private String strAttachLink;
	
	public CanonE307DebriefAttachmentRec() {
		// TODO Auto-generated constructor stub
	}
	public CanonE307DebriefAttachmentRec(
			String strAttachSourceId,
			String strDebriefNumber,
			int attachmentId,
			String strAttachFileName,
			String strAttachLink) {
		this.strAttachSourceId=strAttachSourceId;
		this.strDebriefNumber=strDebriefNumber;
		this.attachmentId=attachmentId;
		this.strAttachFileName=strAttachFileName;
		this.strAttachLink=strAttachLink;
	}
	
	public String getStrAttachSourceId() {
		return strAttachSourceId;
	}
	public void setStrAttachSourceId(String strAttachSourceId) {
		this.strAttachSourceId = strAttachSourceId;
	}
	public String getStrDebriefNumber() {
		return strDebriefNumber;
	}
	public void setStrDebriefNumber(String strDebriefNumber) {
		this.strDebriefNumber = strDebriefNumber;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getStrAttachFileName() {
		return strAttachFileName;
	}
	public void setStrAttachFileName(String strAttachFileName) {
		this.strAttachFileName = strAttachFileName;
	}
	public String getStrAttachLink() {
		return strAttachLink;
	}
	public void setStrAttachLink(String strAttachLink) {
		this.strAttachLink = strAttachLink;
	}
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_ATTACH_REC";
	}
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strAttachSourceId = stream.readString();
		strDebriefNumber=stream.readString();
		attachmentId= stream.readInt();
		strAttachFileName= stream.readString();
		strAttachLink= stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strAttachSourceId);
		stream.writeString(strDebriefNumber);
		stream.writeInt(attachmentId);
		stream.writeString(strAttachFileName);
		stream.writeString(strAttachLink);
	}

}
