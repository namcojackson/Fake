package com.canon.oracle.custapp.custinq.beans;

/**
 * Canon_E193_AcctInfoObj Contains account name, number, id, and CI information.
 * Creation date: (8/23/2005 6:05:38 PM)
 * @author:
 */
public class Canon_E193_AcctInfoObj {
	public String strAcctName;
	public String strAcctNum;
	public String strContactName;
	public String strContactNum;
	public int iAcctId;
	public String strOpenCIFlag;
	public int iOpenCIForNinetyDays;
	public String strPORequiredFlag;
	public String strBillFlg;

	/**
	 * Canon_E193_AcctInfoObj constructor comment.
	 */
	public Canon_E193_AcctInfoObj() {
		this.strAcctName = null;
		this.strAcctNum = null;
		this.strContactName = null;
		this.strContactNum = null;
		this.iAcctId = 0;
		this.strOpenCIFlag = null;
		this.iOpenCIForNinetyDays = 0;
		this.strPORequiredFlag = null;
		this.strBillFlg =null;
	}

	/**
	 * Get strAcctName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String
	 */
	public String getAcctName() {
		return strAcctName;
	}

	/**
	 * Get strAcctNum.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String
	 */
	public String getAcctNum() {
		return strAcctNum;
	}

	/**
	 * Get strContactName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String
	 */
	public String getContactName() {
		return strContactName;
	}

	/**
	 * Get strContactNum.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String
	 */
	public String getContactNum() {
		return strContactNum;
	}

	/**
	 * Get iAcctId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return int
	 */
	public int getAcctId() {
		return iAcctId;
	}

	/**
	 * Get strOpenCIFlag.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @return java.lang.String
	 */
	public String getOpenCIFlag() {
		return strOpenCIFlag;
	}

	/**
	 * Get iOpenCIForNinetyDays.
	 * Creation date: (8/23/2005 6:13:15 PM)
	 * @return int
	 */
	public int getOpenCIForNinetyDays() {
		return iOpenCIForNinetyDays;
	}

	/**
	 * Get strPORequiredFlag.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @return java.lang.String
	 */
	public String getPORequiredFlag() {
		return strPORequiredFlag;
	}

	/**
	 * Set strAcctName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newAcctName java.lang.String
	 */
	public void setAcctName(java.lang.String newAcctName) {
		strAcctName = newAcctName;
	}

	/**
	 * Set strAcctNum.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newAcctNum java.lang.String
	 */
	public void setAcctNum(java.lang.String newAcctNum) {
		strAcctNum = newAcctNum;
	}

	/**
	 * Set strContactName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newContactName java.lang.String
	 */
	public void setContactName(java.lang.String newContactName) {
		strContactName = newContactName;
	}

	/**
	 * Set strContactNum.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newContactNum java.lang.String
	 */
	public void setContactNum(java.lang.String newContactNum) {
		strContactNum = newContactNum;
	}

	/**
	 * Set iAcctId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newAcctId int
	 */
	public void setAcctId(int newAcctId) {
		iAcctId = newAcctId;
	}

	/**
	 * Set strOpenCIFlag.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @param newOpenCIFlag java.lang.String
	 */
	public void setOpenCIFlag(java.lang.String newOpenCIFlag) {
		strOpenCIFlag = newOpenCIFlag;
	}

	/**
	 * Set iOpenCIForNinetyDays.
	 * Creation date: (8/23/2005 6:13:15 PM)
	 * @param newOpenCIForNinetyDays int
	 */
	public void setOpenCIForNinetyDays(int newOpenCIForNinetyDays) {
		iOpenCIForNinetyDays = newOpenCIForNinetyDays;
	}

	/**
	 * Set strPORequiredFlag.
	 * Creation date: (8/23/2005 6:07:53 PM)
	 * @param newPORequiredFlag java.lang.String
	 */
	public void setPORequiredFlag(java.lang.String newPORequiredFlag) {
		strPORequiredFlag = newPORequiredFlag;
	}

	public String getStrBillFlg() {
		return strBillFlg;
	}

	public void setStrBillFlg(String strBillFlg) {
		this.strBillFlg = strBillFlg;
	}

}
