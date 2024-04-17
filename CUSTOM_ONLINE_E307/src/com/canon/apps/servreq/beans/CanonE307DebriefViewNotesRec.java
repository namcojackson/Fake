package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefViewNotesRec implements SQLData{

	private String strNoteSourceId;
	private int noteId;
	private String strNoteType;
	private String noteDate;
	private String strNoteText;
	private String strCreatedBy;
	
	public CanonE307DebriefViewNotesRec() {
		// TODO Auto-generated constructor stub 
	}
	public CanonE307DebriefViewNotesRec(
			String strNoteSourceId,
			int noteId,
			String strNoteType,
			String noteDate,
			String strNoteText,
			String strCreatedBy
			) {
			this.strNoteSourceId =strNoteSourceId;
			this.noteId=noteId;
			this.strNoteType=strNoteType;
			this.noteDate=noteDate;
			this.strNoteText=strNoteText;
			this.strCreatedBy=strCreatedBy;
	}

	public String getStrNoteSourceId() {
		return strNoteSourceId;
	}
	public void setStrNoteSourceId(String strNoteSourceId) {
		this.strNoteSourceId = strNoteSourceId;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getStrNoteType() {
		return strNoteType;
	}
	public void setStrNoteType(String strNoteType) {
		this.strNoteType = strNoteType;
	}
	public String getStrNoteText() {
		return strNoteText;
	}
	public void setStrNoteText(String strNoteText) {
		this.strNoteText = strNoteText;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public String getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strNoteSourceId = stream.readString();
		noteId = stream.readInt();
		strNoteType = stream.readString();
		noteDate = stream.readString();
		strNoteText = stream.readString();
		strCreatedBy = stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		  stream.writeString(strNoteSourceId);
		  stream.writeInt(noteId);
		  stream.writeString(strNoteType);
		  stream.writeString(noteDate);
		  stream.writeString(strNoteText);
		  stream.writeString(strCreatedBy);
	}
    public java.sql.Date getSqlParsedDate(Date dateObj){
		 java.sql.Date sqlObj = null;
	      if(dateObj!=null){
	        try{
	          sqlObj =  new java.sql.Date(dateObj.getTime())  ;
	        }catch(Exception ex){
	          sqlObj = null;
	        }
	      }
	      return sqlObj;
	 }	    	

}
