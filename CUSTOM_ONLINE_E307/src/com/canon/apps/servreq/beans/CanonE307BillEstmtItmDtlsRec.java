package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307BillEstmtItmDtlsRec implements SQLData{

	private String strLineNum;
	private String strItmNum;
	private String strDescription;
	private double qty;
	private String strUom;
	private double lstPrice;
	private double netPrice;
	
	public CanonE307BillEstmtItmDtlsRec() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_EST_PRICE_REC";
	}

	public CanonE307BillEstmtItmDtlsRec(String strLineNum, String strItmNum,
			String strDescription, double qty, String strUom, double lstPrice,
			double netPrice) {
		super();
		this.strLineNum = strLineNum;
		this.strItmNum = strItmNum;
		this.strDescription = strDescription;
		this.qty = qty;
		this.strUom = strUom;
		this.lstPrice = lstPrice;
		this.netPrice = netPrice;
	}


	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strLineNum = stream.readString();
		strItmNum = stream.readString();
		strDescription = stream.readString();
		qty = stream.readDouble();
		strUom=stream.readString();
		lstPrice=stream.readDouble();
		netPrice=stream.readDouble();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strLineNum);
		stream.writeString(strItmNum);
		stream.writeString(strDescription);
		stream.writeDouble(qty);
		stream.writeString(strUom);
		stream.writeDouble(lstPrice);
		stream.writeDouble(netPrice);
	}


	public String getStrLineNum() {
		return strLineNum;
	}


	public void setStrLineNum(String strLineNum) {
		this.strLineNum = strLineNum;
	}


	public String getStrItmNum() {
		return strItmNum;
	}


	public void setStrItmNum(String strItmNum) {
		this.strItmNum = strItmNum;
	}


	public String getStrDescription() {
		return strDescription;
	}


	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}


	public double getQty() {
		return qty;
	}


	public void setQty(double qty) {
		this.qty = qty;
	}


	public String getStrUom() {
		return strUom;
	}


	public void setStrUom(String strUom) {
		this.strUom = strUom;
	}


	public double getLstPrice() {
		return lstPrice;
	}


	public void setLstPrice(double lstPrice) {
		this.lstPrice = lstPrice;
	}


	public double getNetPrice() {
		return netPrice;
	}


	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

	
}
