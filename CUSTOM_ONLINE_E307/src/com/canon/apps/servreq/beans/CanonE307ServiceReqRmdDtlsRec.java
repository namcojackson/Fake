package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqRmdDtlsRec implements SQLData {

	private String svcPblmNarrTxt;   
	private String svcRmdTxt;
	private String docName;
	
	public String getSvcPblmNarrTxt() {
		return svcPblmNarrTxt;
	}
	public void setSvcPblmNarrTxt(String svcPblmNarrTxt) {
		this.svcPblmNarrTxt = svcPblmNarrTxt;
	}
	public String getSvcRmdTxt() {
		return svcRmdTxt;
	}
	public void setSvcRmdTxt(String svcRmdTxt) {
		this.svcRmdTxt = svcRmdTxt;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public CanonE307ServiceReqRmdDtlsRec() {
		
	}

	public CanonE307ServiceReqRmdDtlsRec(String svcPblmNarrTxt,
			String svcRmdTxt, String docName) {
		super();
		this.svcPblmNarrTxt = svcPblmNarrTxt;
		this.svcRmdTxt = svcRmdTxt;
		this.docName = docName;
	}
	
	@Override

	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_REMEDY_REC";
	}
	@Override
	public String toString() {
		return "CanonE307ServiceReqRmdDtlsRec [svcPblmNarrTxt="
				+ svcPblmNarrTxt + ", svcRmdTxt=" + svcRmdTxt + ", docName="
				+ docName + "]";
	}
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		svcPblmNarrTxt = stream.readString();
		svcRmdTxt = stream.readString();
		docName = stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(svcPblmNarrTxt);
		stream.writeString(svcRmdTxt);
		stream.writeString(docName);
	}   
	
}
