package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import canon.apps.common.CanonConstants;

public class CanonE307DebriefNotesDetailsRec implements SQLData{

	private String strNoteSourceId;
	private int noteId;
	private String strNoteTp;
	private String strNoteDt;
	private String strNoteText;
	private String strCreatedBy;

	public CanonE307DebriefNotesDetailsRec(String strNoteSourceId, int noteId,
			String strNoteTp, String strNoteDt, String strNoteText,
			String strCreatedBy) {
		super();
		this.strNoteSourceId = strNoteSourceId;
		this.noteId = noteId;
		this.strNoteTp = strNoteTp;
		this.strNoteDt = strNoteDt;
		this.strNoteText = strNoteText;
		this.strCreatedBy = strCreatedBy;
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

	public String getStrNoteTp() {
		return strNoteTp;
	}

	public void setStrNoteTp(String strNoteTp) {
		this.strNoteTp = strNoteTp;
	}

	public String getStrNoteDt() {
		return strNoteDt;
	}

	public void setStrNoteDt(String strNoteDt) {
		this.strNoteDt = strNoteDt;
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

	public String getSQLTypeName() {
        return CanonConstants.SCHEMA_NAME+".CANON_E307_DEBRIEF_NOTE_REC";
    }
	public CanonE307DebriefNotesDetailsRec() {
		// TODO Auto-generated constructor stub 
	}

	public void readSQL(SQLInput stream, String type) throws SQLException {
		strNoteSourceId = stream.readString();
		noteId = stream.readInt();
		strNoteTp = stream.readString();
		strNoteDt = stream.readString();
		strNoteText = stream.readString();
		strCreatedBy= stream.readString();
	}
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(strNoteSourceId);
        stream.writeInt(noteId);
        stream.writeString(strNoteTp);
        stream.writeString(strNoteDt);
        stream.writeString(strNoteText);
        stream.writeString(strCreatedBy);
    }	
    		
}
