package com.canon.apps.servreq.beans;
import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;


public class CanonE307DebriefNoteTypeRec implements SQLData{
	
	private String  noteSourceId ;                
	private BigDecimal  noteId  ;                     
	private String  noteType  ;                   
	private String  noteDate  ;                   
	private String  noteText  ;                   
	private String  createdBy  ;
	
	
	public CanonE307DebriefNoteTypeRec(){
		
	}
	
	public CanonE307DebriefNoteTypeRec(String noteSourceId, BigDecimal noteId,
			String noteType, String noteDate, String noteText, String createdBy) {
		this.noteSourceId = noteSourceId;
		this.noteId = noteId;
		this.noteType = noteType;
		this.noteDate = noteDate;
		this.noteText = noteText;
		this.createdBy = createdBy;
	}

	public String getNoteSourceId() {
		return noteSourceId;
	}

	public void setNoteSourceId(String noteSourceId) {
		this.noteSourceId = noteSourceId;
	}

	public BigDecimal getNoteId() {
		return noteId;
	}

	public void setNoteId(BigDecimal noteId) {
		this.noteId = noteId;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSQLTypeName() throws SQLException {
		
		return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_REC";
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		   noteSourceId   =stream.readString();              
		   noteId    =stream.readBigDecimal();              
		   noteType =stream.readString();                    
		   noteDate =stream.readString();                    
		   noteText=stream.readString();                     
		   createdBy =stream.readString(); 
	}

	public void writeSQL(SQLOutput stream) throws SQLException {
		   stream.writeString(noteSourceId);                 
		   stream.writeBigDecimal(noteId);                       
		   stream.writeString(noteType);                     
		   stream.writeString(noteDate);                     
		   stream.writeString(noteText);                     
		   stream.writeString(createdBy);   
	}
}
