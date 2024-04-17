package com.canon.oracle.custapp.custinq.beans;

/**
 * Canon_E193_AdminAssignObj Contains Invoice Header information.
 * Creation date: (8/23/2005 6:05:38 PM)
 * @author:
 * 
 *  Date   			By         				Description
 * ----   		---------- 			---------------------------------------------
 * 01/30/2016	Mangala Shenoy		Modified for S21 Changes.
 */
public class Canon_E193_AdminAssignObj {
	int iCatId;
	String strCatCode;
	String strCatDesc;
	String strParentCatCode;
	String iCrmRoleId;
	String strCrmRoleCode;
	String strCrmRoleName;
	String strCrmRoleTypeCode;
	int iCrmResourceGroupId;
	String strCrmGroupName;
	String strCrmGroupDesc;
	String strCrmResourceName;
	//Start changes for S21 by Mangala
	//int iCrmResourceId;
	String iCrmResourceId;
	//End Changes for S21 by Mangala

	/**
	 * Canon_E193_AdminAssignObj constructor comment.
	 */
	public Canon_E193_AdminAssignObj() {
		iCatId = 0;
		strCatCode = "";
		strCatDesc = "";
		strParentCatCode = "";
		iCrmRoleId = "";
		strCrmRoleCode = "";
		strCrmRoleName = "";
		strCrmRoleTypeCode = "";
		iCrmResourceGroupId = 0;
		strCrmGroupName = "";
		strCrmGroupDesc = "";
		strCrmResourceName = "";
		//Start Changes for S21 by Mangala
		//iCrmResourceId = 0;
		iCrmResourceId = "";
		//End Changes for S21 by Mangala
	}

	/**
	 * Get iCrmResourceId.
	 * Creation date: Oct 28, 2005.
	 * @return int.
	 */
	//Start Changes for S21 by Mangala
	/*public int getICrmResourceId() {
		return iCrmResourceId;
	}*/

	/**
	 * Set iCrmResourceId.
	 * Creation date: Oct 28, 2005.
	 * @param crmResourceId int.
	 */
	/*public void setICrmResourceId(int crmResourceId) {
		iCrmResourceId = crmResourceId;
	}*/
	public String getICrmResourceId() {
		return iCrmResourceId;
	}
	public void setICrmResourceId(String crmResourceId) {
		iCrmResourceId = crmResourceId;
	}	
	//End Changes for S21 by Mangala
	/**
	 * Get strCrmResourceName.
	 * Creation date: Oct 28, 2005.
	 * @return String.
	 */
	
	public String getStrCrmResourceName() {
		return strCrmResourceName;
	}

	/**
	 * Set strCrmResourceName.
	 * Creation date: Oct 28, 2005.
	 * @param strCrmResourceName String.
	 */
	public void setStrCrmResourceName(String strCrmResourceName) {
		this.strCrmResourceName = strCrmResourceName;
	}

	/**
	 * Get iCatId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return int.
	 */
	public int getICatId() {
		return iCatId;
	}

	/**
	 * Set iCatId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCatId int.
	 */
	public void setICatId(int newCatId) {
		iCatId = newCatId;
	}

	/**
	 * Get strCatCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCatCode() {
		return strCatCode;
	}

	/**
	 * Set strCatCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCatCode java.lang.String.
	 */
	public void setStrCatCode(String newCatCode) {
		this.strCatCode = newCatCode;
	}

	/**
	 * Get strCatDesc.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCatDesc() {
		return strCatDesc;
	}

	/**
	 * Set strCatDesc.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCatDesc java.lang.String.
	 */
	public void setStrCatDesc(String newCatDesc) {
		this.strCatDesc = newCatDesc;
	}

	/**
	 * Get strParentCatCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrParentCatCode() {
		return strParentCatCode;
	}

	/**
	 * Set strParentCatCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newParentCatCode java.lang.String.
	 */
	public void setStrParentCatCode(String newParentCatCode) {
		this.strParentCatCode = newParentCatCode;
	}

	/**
	 * Get iCrmRoleId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return int.
	 */
	public String getICrmRoleId() {
		return iCrmRoleId;
	}

	/**
	 * Set iCrmRoleId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmRoleId int
	 */
	public void setICrmRoleId(String newCrmRoleId) {
		this.iCrmRoleId = newCrmRoleId;
	}

	/**
	 * Get strCrmRoleCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCrmRoleCode() {
		return strCrmRoleCode;
	}

	/**
	 * Set strCrmRoleCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmRoleCode java.lang.String.
	 */
	public void setStrCrmRoleCode(String newCrmRoleCode) {
		this.strCrmRoleCode = newCrmRoleCode;
	}

	/**
	 * Get strCrmRoleName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCrmRoleName() {
		return strCrmRoleName;
	}

	/**
	 * Set strCrmRoleName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmRoleName java.lang.String.
	 */
	public void setStrCrmRoleName(String newCrmRoleName) {
		this.strCrmRoleName = newCrmRoleName;
	}

	/**
	 * Get strCrmRoleTypeCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCrmRoleTypeCode() {
		return strCrmRoleTypeCode;
	}

	/**
	 * Set strCrmRoleTypeCode.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmRoleTypeCode java.lang.String.
	 */
	public void setStrCrmRoleTypeCode(String newCrmRoleTypeCode) {
		this.strCrmRoleTypeCode = newCrmRoleTypeCode;
	}

	/**
	 * Get iCrmResourceGroupId.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return int.
	 */
	public int getICrmResourceGroupId() {
		return iCrmResourceGroupId;
	}

	/**
	 * Set iCrmResourceGroup.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmResourceGroupId int
	 */
	public void setICrmResourceGroupId(int newCrmResourceGroupId) {
		this.iCrmResourceGroupId = newCrmResourceGroupId;
	}

	/**
	 * Get strCrmGroupName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCrmGroupName() {
		return strCrmGroupName;
	}

	/**
	 * Set strCrmGroupName.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmGroupName java.lang.String.
	 */
	public void setStrCrmGroupName(String newCrmGroupName) {
		this.strCrmGroupName = newCrmGroupName;
	}

	/**
	 * Get strCrmGroupDesc
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return java.lang.String.
	 */
	public String getStrCrmGroupDesc() {
		return strCrmGroupDesc;
	}

	/**
	 * Set strCrmGroupDesc
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param newCrmGroupDesc java.lang.String.
	 */
	public void setStrCrmGroupDesc(String newCrmGroupDesc) {
		this.strCrmGroupDesc = newCrmGroupDesc;
	}
}
