package com.canon.oracle.custapp.custinq.beans;

import java.util.*;

/**
 * Canon_E193_SessionObj Contains arraylist, string objects to be stored in session.
 * Creation date: (8/23/2005 6:05:38 PM)
 * @author:
 */
public class Canon_E193_SessionObj {
	
	private String strSessionString;
	private ArrayList alSessionArraylist;
	
	private ArrayList aggregateList;

	private String[] mBSerialNumbers;
	private String[] mRSerialNumbers;
	private String[] mPSerialNumbers;
	
	private boolean isBaseFlag = false;
	private boolean isUsageFlag = false;
	
	private boolean isContactFlag = false;
	/*private boolean isSavedClicked = false;	
	
	public boolean isSavedClicked() {
		return isSavedClicked;
	}

	public void setSavedClicked(boolean isSavedClicked) {
		this.isSavedClicked = isSavedClicked;
	}*/

	private ArrayList serial_InvPK;
	
	public ArrayList getSerial_InvPK() {
		return this.serial_InvPK;
	}

	public void setSerial_InvPK(ArrayList serial_InvPK) {
		this.serial_InvPK = serial_InvPK;
	}

	/**
	 * Canon_E193_SessionObj constructor comment.
	 */

	public Canon_E193_SessionObj() {
		super();
	}

	/**
	 * Get alSessionArraylist.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.util.ArrayList
	 */
	public ArrayList getArrayList() {
		return alSessionArraylist;
	}

	/**
	 * Get strSessionString.
	 * Creation date: (8/23/2005 6:07:20 PM)
	 * @return java.lang.String
	 */
	public String getString() {
		return strSessionString;
	}

	/**
	 * Set alSessionArraylist.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newArrayList java.util.ArrayList
	 */
	public void setArrayList(java.util.ArrayList newArrayList) {
		alSessionArraylist = newArrayList;
	}

	/**
	 * Set strSessionString.
	 * Creation date: (8/23/2005 6:07:20 PM)
	 * @param newString java.lang.String
	 */
	public void setString(java.lang.String newString) {
		strSessionString = newString;
	}
	
	/**
	 * Get BSerialNumber
	 * @return array of String as SerialNumbers
	 */
	public String[] getBSerialNumbers() {
		return this.mBSerialNumbers;
	}
	/**
	 * Set BSerialNumber
	 * @param strSerialNums Array of SerialNumber as a String
	 */
	public void setBSerialNumbers(String[] strSerialNums) {
		this.mBSerialNumbers = strSerialNums;
	}
	
	/**
	 * Get Usage(READ) SerialNumber
	 * @return - Array of Usage(Read) SerialNumber as String
	 */
	public String[] getRSerialNumbers() {
		return this.mRSerialNumbers;
	}
	
	/**
	 * Set Usage Serial Number
	 * @param strSerialNums_ Array of Usage SerialNumber As a String
	 */
	public void setRSerialNumbers(String[] strSerialNums_) {
		this.mRSerialNumbers = strSerialNums_;
	}
	
	
	public String[] getmPSerialNumbers() {
		return mPSerialNumbers;
	}

	public void setmPSerialNumbers(String[] mPSerialNumbers) {
		this.mPSerialNumbers = mPSerialNumbers;
	}

	/**
	 * Get Base Flag
	 * @return - true or false
	 */
	public boolean getIsBaseFalge() {
		return this.isBaseFlag;
	}
	/**
	 * Set Base Flag - Default value is false. True- Means Presently
	 * we are going through Base 
	 * @param _baseFlag - True or False
	 */
	public void setIsBaseFalge(boolean _baseFlag) {
		this.isBaseFlag = _baseFlag;
	}
	/**
	 * Get Usage(READ) Flag
	 * @return True or False.
	 */
	public boolean getIsUsageFlag() {
		return this.isUsageFlag;
	}
	/**
	 * Set Usage(Read) Flag - Default value is false. True- Means Presently
	 * we are going through Usage Section
	 * @param _usageFlag- True or False.
	 */
	public void setIsUsageFlag(boolean _usageFlag) {
		this.isUsageFlag = _usageFlag;
	}
	
	public boolean getContactFlag() {
		return this.isContactFlag;
	}
	public void setContactFlag(boolean _contactFlag) {
		this.isContactFlag = _contactFlag;
	}

	public ArrayList getAggregateList() {
		return aggregateList;
	}

	public void setAggregateList(ArrayList aggregateList) {
		this.aggregateList = aggregateList;
	}
	
	}
