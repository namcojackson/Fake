package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqLocRec   implements SQLData{

     private String custCode;
	 private String custName;
	 private String addr;
	 private String city;
	 private String state;
	 private String postalCode;
	 private String country;
	 private String custPk;
	
	 
	 
	 public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCustPk() {
		return custPk;
	}
	public void setCustPk(String custPk) {
		this.custPk = custPk;
	}

	
	@Override
	public String toString() {
		return "CanonE307ServiceReqLocRec [custCode=" + custCode
				+ ", custName=" + custName + ", addr=" + addr + ", city="
				+ city + ", state=" + state + ", postalCode=" + postalCode
				+ ", country=" + country + ", custPk=" + custPk + "]";
	}
	public CanonE307ServiceReqLocRec(){
		
	}
	
	public CanonE307ServiceReqLocRec(String custCode,String custName, String addr, String city,
			 String state,String postalCode, String country, String custPk) {
		this.custCode=custCode;
		this.custName = custName;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.custPk = custPk;
		
	}
	
	public void readSQL(SQLInput stream, String type) throws SQLException {
		 custCode= stream.readString();
		 custName= stream.readString();
		 addr= stream.readString();
		 city= stream.readString();
		 state= stream.readString();
		 postalCode= stream.readString();
		 country= stream.readString(); 
		 custPk= stream.readString(); 
	 }
	
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(custCode);
	    stream.writeString(custName);
		stream.writeString(addr);
		stream.writeString(city);
	    stream.writeString(state);
	    stream.writeString(postalCode);
	    stream.writeString(country); 
		stream.writeString(custPk); 
	
    }	
	
	public String getSQLTypeName() {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CUST_LOC_REC";
    }	
	
}
