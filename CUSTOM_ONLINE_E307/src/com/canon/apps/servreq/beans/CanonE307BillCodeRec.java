package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307BillCodeRec implements SQLData{
	
	public CanonE307BillCodeRec() {
		// TODO Auto-generated constructor stub
	}
	
	private String billTpCd;
	private String billTpVal;
	private String billTpDesc;
	
	public CanonE307BillCodeRec(String billTpCd, String billTpVal,
			String billTpDesc) {
		super();
		this.billTpCd = billTpCd;
		this.billTpVal = billTpVal;
		this.billTpDesc = billTpDesc;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TYPE_LOV_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		billTpCd = stream.readString();
		billTpVal = stream.readString();
		billTpDesc = stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(billTpCd);
		stream.writeString(billTpVal);
		stream.writeString(billTpDesc);
	}

	public String getBillTpCd() {
		return billTpCd;
	}

	public void setBillTpCd(String billTpCd) {
		this.billTpCd = billTpCd;
	}

	public String getBillTpVal() {
		return billTpVal;
	}

	public void setBillTpVal(String billTpVal) {
		this.billTpVal = billTpVal;
	}

	public String getBillTpDesc() {
		return billTpDesc;
	}

	public void setBillTpDesc(String billTpDesc) {
		this.billTpDesc = billTpDesc;
	}

	@Override
	public String toString() {
		return "CanonE307BillCodeRec [billTpCd=" + billTpCd + ", billTpVal="
				+ billTpVal + ", billTpDesc=" + billTpDesc + "]";
	}

}
