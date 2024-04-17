package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307EOLRec implements SQLData{
	private String stpSrvDt;
	private String srvTp;
	private String eolSvcContrCmnt;
	private String eolTmMatCmnt;
	private String eolTechSprtCmnt;
	private String eolOthComnt;
	private String eolDisptCmntTxt;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;

	public CanonE307EOLRec() {
		// TODO Auto-generated constructor stub
	}
	

	public CanonE307EOLRec(String stpSrvDt, String srvTp,
			String eolSvcContrCmnt, String eolTmMatCmnt,
			String eolTechSprtCmnt, String eolOthComnt, String eolDisptCmntTxt,
			String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5) {
		super();
		this.stpSrvDt = stpSrvDt;
		this.srvTp = srvTp;
		this.eolSvcContrCmnt = eolSvcContrCmnt;
		this.eolTmMatCmnt = eolTmMatCmnt;
		this.eolTechSprtCmnt = eolTechSprtCmnt;
		this.eolOthComnt = eolOthComnt;
		this.eolDisptCmntTxt = eolDisptCmntTxt;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_EOL_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		stpSrvDt = stream.readString();
		srvTp = stream.readString();
		eolSvcContrCmnt = stream.readString();
		eolTmMatCmnt = stream.readString();
		eolTechSprtCmnt = stream.readString();
		eolOthComnt = stream.readString();
		eolDisptCmntTxt = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
		attribute4 = stream.readString();
		attribute5 = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(stpSrvDt);
		stream.writeString(srvTp);
		stream.writeString(eolSvcContrCmnt);
		stream.writeString(eolTmMatCmnt);
		stream.writeString(eolTechSprtCmnt);
		stream.writeString(eolOthComnt);
		stream.writeString(eolDisptCmntTxt);
		stream.writeString(attribute1);
		stream.writeString(attribute2);
		stream.writeString(attribute3);
		stream.writeString(attribute4);
		stream.writeString(attribute5);
		
	}


	public String getStpSrvDt() {
		return stpSrvDt;
	}


	public void setStpSrvDt(String stpSrvDt) {
		this.stpSrvDt = stpSrvDt;
	}


	public String getSrvTp() {
		return srvTp;
	}


	public void setSrvTp(String srvTp) {
		this.srvTp = srvTp;
	}


	public String getEolSvcContrCmnt() {
		return eolSvcContrCmnt;
	}


	public void setEolSvcContrCmnt(String eolSvcContrCmnt) {
		this.eolSvcContrCmnt = eolSvcContrCmnt;
	}


	public String getEolTmMatCmnt() {
		return eolTmMatCmnt;
	}


	public void setEolTmMatCmnt(String eolTmMatCmnt) {
		this.eolTmMatCmnt = eolTmMatCmnt;
	}


	public String getEolTechSprtCmnt() {
		return eolTechSprtCmnt;
	}


	public void setEolTechSprtCmnt(String eolTechSprtCmnt) {
		this.eolTechSprtCmnt = eolTechSprtCmnt;
	}


	public String getEolOthComnt() {
		return eolOthComnt;
	}


	public void setEolOthComnt(String eolOthComnt) {
		this.eolOthComnt = eolOthComnt;
	}


	public String getEolDisptCmntTxt() {
		return eolDisptCmntTxt;
	}


	public void setEolDisptCmntTxt(String eolDisptCmntTxt) {
		this.eolDisptCmntTxt = eolDisptCmntTxt;
	}


	public String getAttribute1() {
		return attribute1;
	}


	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}


	public String getAttribute2() {
		return attribute2;
	}


	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}


	public String getAttribute3() {
		return attribute3;
	}


	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}


	public String getAttribute4() {
		return attribute4;
	}


	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}


	public String getAttribute5() {
		return attribute5;
	}


	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}


	@Override
	public String toString() {
		return "CanonE307EOLRec [stpSrvDt=" + stpSrvDt + ", srvTp=" + srvTp
				+ ", eolSvcContrCmnt=" + eolSvcContrCmnt + ", eolTmMatCmnt="
				+ eolTmMatCmnt + ", eolTechSprtCmnt=" + eolTechSprtCmnt
				+ ", eolOthComnt=" + eolOthComnt + ", eolDisptCmntTxt="
				+ eolDisptCmntTxt + ", attribute1=" + attribute1
				+ ", attribute2=" + attribute2 + ", attribute3=" + attribute3
				+ ", attribute4=" + attribute4 + ", attribute5=" + attribute5
				+ "]";
	}



}
