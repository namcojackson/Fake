package com.canon.apps.servreq.beans;

public class CanonE307SerialNumLov {

	private String strSerialNumber;
	private String strModel;
	private String strMachMstrPk;
	private String strEquipNumber;
	private String strShipToAcctNum;
	private String strAddress;
	private String strCustName;
	
	public CanonE307SerialNumLov() {
		// TODO Auto-generated constructor stub
	}


	public CanonE307SerialNumLov(String strSerialNumber, String strModel,
			String strMachMstrPk, String strEquipNumber,
			String strShipToAcctNum, String strAddress, String strCustName) {
		super();
		this.strSerialNumber = strSerialNumber;
		this.strModel = strModel;
		this.strMachMstrPk = strMachMstrPk;
		this.strEquipNumber = strEquipNumber;
		this.strShipToAcctNum = strShipToAcctNum;
		this.strAddress = strAddress;
		this.strCustName = strCustName;
	}



	public String getStrSerialNumber() {
		return strSerialNumber;
	}

	public void setStrSerialNumber(String strSerialNumber) {
		this.strSerialNumber = strSerialNumber;
	}

	public String getStrModel() {
		return strModel;
	}

	public void setStrModel(String strModel) {
		this.strModel = strModel;
	}

	public String getStrMachMstrPk() {
		return strMachMstrPk;
	}

	public void setStrMachMstrPk(String strMachMstrPk) {
		this.strMachMstrPk = strMachMstrPk;
	}

	public String getStrEquipNumber() {
		return strEquipNumber;
	}

	public void setStrEquipNumber(String strEquipNumber) {
		this.strEquipNumber = strEquipNumber;
	}

	public String getStrShipToAcctNum() {
		return strShipToAcctNum;
	}

	public void setStrShipToAcctNum(String strShipToAcctNum) {
		this.strShipToAcctNum = strShipToAcctNum;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}


	public String getStrCustName() {
		return strCustName;
	}


	public void setStrCustName(String strCustName) {
		this.strCustName = strCustName;
	}


	@Override
	public String toString() {
		return "CanonE307SerialNumLov [strSerialNumber=" + strSerialNumber
				+ ", strModel=" + strModel + ", strMachMstrPk=" + strMachMstrPk
				+ ", strEquipNumber=" + strEquipNumber + ", strShipToAcctNum="
				+ strShipToAcctNum + ", strAddress=" + strAddress
				+ ", strCustName=" + strCustName + "]";
	}

	

}
