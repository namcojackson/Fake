package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307WasteTonerRec implements SQLData{

	private String serialNumber;
	private String model;
	private String customer;
	private String location;
	private String contactName;
	private String contactPhone;
	private String contactPhnExt;
	private String emailAddress;
	private String shelfStock;
	private String quantity;
	
	public CanonE307WasteTonerRec() {
		// TODO Auto-generated constructor stub
	}
	
	public CanonE307WasteTonerRec(String serialNumber, String model,
			String customer, String location, String contactName,
			String contactPhone, String contactPhnExt, String emailAddress,
			String shelfStock, String quantity) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.customer = customer;
		this.location = location;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactPhnExt = contactPhnExt;
		this.emailAddress = emailAddress;
		this.shelfStock = shelfStock;
		this.quantity = quantity;
	}



	@Override
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_WASTE_TONER_REC";
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		serialNumber 	= stream.readString();
		model			= stream.readString();
		customer		= stream.readString();
		location		= stream.readString();
		contactName		= stream.readString();
		contactPhone	= stream.readString();
		contactPhnExt	= stream.readString();
		emailAddress	= stream.readString();
		shelfStock		= stream.readString();
		quantity		= stream.readString();
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(serialNumber);
		stream.writeString(model);
		stream.writeString(customer);
		stream.writeString(location);
		stream.writeString(contactName);
		stream.writeString(contactPhone);
		stream.writeString(contactPhnExt);
		stream.writeString(emailAddress);
		stream.writeString(shelfStock);
		stream.writeString(quantity);
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactPhnExt() {
		return contactPhnExt;
	}

	public void setContactPhnExt(String contactPhnExt) {
		this.contactPhnExt = contactPhnExt;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getShelfStock() {
		return shelfStock;
	}

	public void setShelfStock(String shelfStock) {
		this.shelfStock = shelfStock;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CanonE307WasteTonerRec [serialNumber=" + serialNumber
				+ ", model=" + model + ", customer=" + customer + ", location="
				+ location + ", contactName=" + contactName + ", contactPhone="
				+ contactPhone + ", contactPhnExt=" + contactPhnExt
				+ ", emailAddress=" + emailAddress + ", shelfStock="
				+ shelfStock + ", quantity=" + quantity + "]";
	}
	

}
