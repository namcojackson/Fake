package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307NoteTypeRec implements SQLData{
	
	public CanonE307NoteTypeRec() {
		// TODO Auto-generated constructor stub
	}
	
	private String noteTpCd;
	private String noteTpDesc;
	private String asccDefFlg;
	
	public CanonE307NoteTypeRec(String noteTpCd, String noteTpDesc,
			String asccDefFlg) {
		super();
		this.noteTpCd = noteTpCd;
		this.noteTpDesc = noteTpDesc;
		this.asccDefFlg = asccDefFlg;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		noteTpCd = stream.readString();
		noteTpDesc = stream.readString();
		asccDefFlg = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(noteTpCd);
		stream.writeString(noteTpDesc);
		stream.writeString(asccDefFlg);
	}

	public String getNoteTpCd() {
		return noteTpCd;
	}

	public void setNoteTpCd(String noteTpCd) {
		this.noteTpCd = noteTpCd;
	}

	public String getNoteTpDesc() {
		return noteTpDesc;
	}

	public void setNoteTpDesc(String noteTpDesc) {
		this.noteTpDesc = noteTpDesc;
	}

	public String getAsccDefFlg() {
		return asccDefFlg;
	}

	public void setAsccDefFlg(String asccDefFlg) {
		this.asccDefFlg = asccDefFlg;
	}

	@Override
	public String toString() {
		return "CanonE307NoteTypeRec [noteTpCd=" + noteTpCd + ", noteTpDesc="
				+ noteTpDesc + ", asccDefFlg=" + asccDefFlg + "]";
	}
	
}
