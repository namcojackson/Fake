package com.canon.oracle.custapp.custinq.beans;

/**
 * Title:		Canon_E193_IssueListObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (15-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 *
 * </pre>
 */

public class Canon_E193_IssueListObj {
	
	private int catId;
	private int parentCatId;
	private String catCode;
	private String catDesc;

	/**
	 * Canon_E193_IssueListObj constructor comment.
	 */
	public Canon_E193_IssueListObj() {
		super();
	}

	/**
	 * Get catDesc.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getCatDesc() {
		return catDesc;
	}

	/**
	 * Set catDesc.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newCatDesc java.lang.String
	 */
	public void setCatDesc(String newCatDesc) {
		this.catDesc = newCatDesc;
	}

	/**
	 * Get catId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return int
	 */
	public int getCatId() {
		return catId;
	}

	/**
	 * Set catId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newCatId int
	 */
	public void setCatId(int newCatId) {
		this.catId = newCatId;
	}

	/**
	 * Get parentCatId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return int
	 */
	public int getParentCatId() {
		return parentCatId;
	}

	/**
	 * Set parentCatId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newParentCatId int
	 */
	public void setParentCatId(int newParentCatId) {
		this.parentCatId = newParentCatId;
	}

	/**
	 * Get catCode.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */
	
	public String getCatCode() {
		return catCode;
	}

	/**
	 * Set catCode.
	 * Creation date: Sep 27, 2005.
	 * @param newCatCode String.
	 */
	public void setCatCode(String newCatCode) {
		this.catCode = newCatCode;
	}

	@Override
	public String toString() {
		return "Canon_E193_IssueListObj [catId=" + catId + ", parentCatId="
				+ parentCatId + ", catCode=" + catCode + ", catDesc=" + catDesc
				+ "]";
	}

}
