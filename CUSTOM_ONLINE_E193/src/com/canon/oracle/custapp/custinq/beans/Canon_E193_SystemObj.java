package com.canon.oracle.custapp.custinq.beans;

/**
 * Canon_E193_SystemObj Contains user name, user id, responsibility id and application id
 * Creation date: (8/15/2005 3:51:35 PM)
 * Last Updated Date: (11/30/2006)
 * Update Comments: ITG# 74988 - CFS Changes
 * @author: Dipti Shedji
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * </pre>
 */
public class Canon_E193_SystemObj {

    public static final String CBS_REGION_CODE = "EAST_REGION";

	public String strUserName = "";
	public String strEmpName = "";
	public String strOrgName = "";
	public String iUserId = "";
	public String iRespId = "";
	public int iApplId = 0;
	public int iOrgId = 0;
	public String iResourceId = "";

/*ITG# 74988 */
    public String strCFSAccessFlag;
    public String strCFSUserFlag;

    // ITG# 73987 - Kireet K Bollam
    public String strRegionCode= "";


	/**
	 * Canon_E193_SystemObj constructor comment..
	 */
	public Canon_E193_SystemObj() {
		this.strUserName = "";
		this.strEmpName = "";
		this.strOrgName = "";
		this.iUserId = "";
		this.iRespId ="";
		this.iApplId = 0;
		this.iOrgId = 0;
		this.iResourceId = "";

/*ITG# 74988 - Begin */
		this.strCFSAccessFlag = "N"; /*Default set to N */
		this.strCFSUserFlag = "N";

		// ITG# 73987 - Kireet K Bollam
		this.strRegionCode = CBS_REGION_CODE;

	}
/*ITG# 74988 - End */

	/**
	 * Get application id.
	 * Creation date: (8/15/2005 3:56:50 PM)
	 * @return int
	 */
	public int getApplId() {
		return this.iApplId;
	}

	/**
	 * Get responsibility id.
	 * Creation date: (8/15/2005 3:56:30 PM)
	 * @return int
	 */
	public String getRespId() {
		return this.iRespId;
	}

	/**
	 * Get user id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @return int
	 */
	public String getUserId() {
		return this.iUserId;
	}

	/**
	 * Get user name.
	 * Creation date: (8/15/2005 3:55:43 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getUserName() {
		return this.strUserName;
	}

	/**
	 * Get employee name.
	 * Creation date: (8/15/2005 3:55:43 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getEmpName() {
		return this.strEmpName;
	}

	/**
	 * Get org id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @return int
	 */
	public int getOrgId() {
		return this.iOrgId;
	}

	/**
	 * Get resource id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @return int
	 */
	public String getResourceId() {
		return this.iResourceId;
	}

	/**
	 * Get org name.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getOrgName() {
		return this.strOrgName;
	}

/*ITG# 74988 - Begin */
	/**
	 * Get CFSAccessFlag.
	 * Creation date: Nov 29, 2006.
	 * @return java.lang.String.
	 */

	public java.lang.String getCFSAccessFlag() {
		return this.strCFSAccessFlag;
	}

	/**
	 * Get CFSUserFlag.
	 * Creation date: Nov 29, 2006.
	 * @return java.lang.String.
	 */

	public java.lang.String getCFSUserFlag() {
		return this.strCFSUserFlag;
	}
/*ITG# 74988 - End */


// ITG# 73987 - Kireet K Bollam : Begin
	/**
	 * Get RegionCode.
	 * Creation date: Dec 18, 2006.
	 * @return java.lang.String.
	 */

	public java.lang.String getRegionCode() {
		return this.strRegionCode;
	}
// ITG# 73987 - Kireet K Bollam : End


	/**
	 * Set application id.
	 * Creation date: (8/15/2005 3:56:50 PM)
	 * @param newApplId int
	 */
	public void setApplId(int newApplId) {
		this.iApplId = newApplId;
	}

	/**
	 * Set responsibility id.
	 * Creation date: (8/15/2005 3:56:30 PM)
	 * @param newRespId int
	 */
	public void setRespId(String newRespId) {
		this.iRespId = newRespId;
	}

	/**
	 * Set user id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @param newUserId int
	 */
	public void setUserId(String newUserId) {
		this.iUserId = newUserId;
	}

	/**
	 * Set org id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @param newOrgId int
	 */
	public void setOrgId(int newOrgId) {
		this.iOrgId = newOrgId;
	}

	/**
	 * Set resource id.
	 * Creation date: (8/15/2005 3:56:16 PM)
	 * @param newResourceId int
	 */
	public void setResourceId(String newResourceId) {
		this.iResourceId = newResourceId;
	}

	/**
	 * Set user name.
	 * Creation date: (8/15/2005 3:55:43 PM)
	 * @param newUserName java.lang.String
	 */
	public void setUserName(java.lang.String newUserName) {
		this.strUserName = newUserName;
	}

	/**
	 * Set employee name.
	 * Creation date: (8/15/2005 3:55:43 PM)
	 * @param newEmpName java.lang.String
	 */
	public void setEmpName(java.lang.String newEmpName) {
		this.strEmpName = newEmpName;
	}

	/**
	 * Set organization name.
	 * Creation date: (8/15/2005 3:55:43 PM)
	 * @param newOrgName java.lang.String
	 */
	public void setOrgName(java.lang.String newOrgName) {
		this.strOrgName = newOrgName;
	}

/*ITG# 74988 - Begin */
	/**
	 * Set CFSAccessFlag.
	 * Creation date: Nov 29, 2006.
	 * @param newCFSAccessFlag java.lang.String
	 */

	public void setCFSAccessFlag(java.lang.String newCFSAccessFlag) {
		this.strCFSAccessFlag = newCFSAccessFlag;
	}

	/**
	 * Set CFSUserFlag.
	 * Creation date: Nov 29, 2006.
	 * @param newCFSUserFlag java.lang.String
	 */

    public void setCFSUserFlag(java.lang.String newCFSUserFlag) {
		this.strCFSUserFlag = newCFSUserFlag;
	}
/*ITG# 74988 - End */

// ITG# 73987 - Kireet K Bollam : Begin
	/**
	 * Set RegionCode.
	 * Creation date: Dec 18, 2006.
	 * @param newRegionCode java.lang.String
	 */

	public void setRegionCode(java.lang.String newRegionCode) {
		this.strRegionCode = newRegionCode;
	}
// ITG# 73987 - Kireet K Bollam : End


}