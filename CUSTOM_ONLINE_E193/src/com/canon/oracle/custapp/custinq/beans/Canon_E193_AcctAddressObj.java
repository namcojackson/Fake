package com.canon.oracle.custapp.custinq.beans;

/**
 * Canon_E193_AcctAddressObj Contains address1, address2, city, state, postal_code.
 * Creation date: (8/23/2005 6:05:38 PM)
 * @author:
 */
public class Canon_E193_AcctAddressObj {
	public String address1;
	public String address2;
	public String state;
	public String city;
	public String postalCode;
	public String type;
	public String acctName;
	public String acctNum;
	public String acctID;
	public String locID;


	/**
	 * Canon_E193_AcctAddressObj constructor comment.
	 */

	public Canon_E193_AcctAddressObj() {
		super();
	}

	/**
	 * Get address1.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Get address2.
	 * Creation date: (8/23/2005 6:07:20 PM)
	 * @return java.lang.String
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Get city.
	 * Creation date: (8/23/2005 6:08:07 PM)
	 * @return java.lang.String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Get state.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @return java.lang.String
	 */
	public String getState() {
		return state;
	}

	/**
	 * Get postal_code.
	 * Creation date: (8/23/2005 6:13:15 PM)
	 * @return java.lang.String
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Get type.
	 * Creation date: (8/23/2005 6:16:36 PM)
	 * @return java.lang.String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set address1.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newAddress1 java.lang.String
	 */
	public void setAddress1(java.lang.String newAddress1) {
		address1 = newAddress1;
	}

	/**
	 * Set address2.
	 * Creation date: (8/23/2005 6:07:20 PM)
	 * @param newAddress2 java.lang.String
	 */
	public void setAddress2(java.lang.String newAddress2) {
		address2 = newAddress2;
	}

	/**
	 * Set city.
	 * Creation date: (8/23/2005 6:08:07 PM)
	 * @param newCity java.lang.String
	 */
	public void setCity(java.lang.String newCity) {
		city = newCity;
	}

	/**
	 * Set state.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @param newState java.lang.String
	 */
	public void setState(java.lang.String newState) {
		state = newState;
	}

	/**
	 * Set postal_code.
	 * Creation date: (8/23/2005 6:13:15 PM)
	 * @param newPostalCode java.lang.String
	 */
	public void setPostalCode(java.lang.String newPostalCode) {
		postalCode = newPostalCode;
	}

	/**
	 * Set type.
	 * Creation date: (8/23/2005 6:16:36 PM)
	 * @param newType java.lang.String
	 */
	public void setType(java.lang.String newType) {
		type = newType;
	}

	/**
	 * @return Returns the acctID.
	 */
	public String getAcctID() {
		return acctID;
	}

	/**
	 * @param acctID The acctID to set.
	 */
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}

	/**
	 * @return Returns the acctName.
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * @param acctName The acctName to set.
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	/**
	 * @return Returns the acctNum.
	 */
	public String getAcctNum() {
		return acctNum;
	}

	/**
	 * @param acctNum The acctNum to set.
	 */
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	/**
	 * @return Returns the locID.
	 */
	public String getLocID() {
		return locID;
	}

	/**
	 * @param locID The locID to set.
	 */
	public void setLocID(String locID) {
		this.locID = locID;
	}
}
