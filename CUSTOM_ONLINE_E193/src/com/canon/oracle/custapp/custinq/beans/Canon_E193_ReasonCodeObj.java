package com.canon.oracle.custapp.custinq.beans;

/**
 * Title:		Canon_E193_ReasonCodeObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (14-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 *
 * </pre>
 */

public class Canon_E193_ReasonCodeObj {
	
	private String reasonCode;
	private String meaning;
	private String reason;
	private String description;
	
	/**
	 * Canon_E193_ReasonCodeObj constructor comment.
	 */
	
	public Canon_E193_ReasonCodeObj() {
		super();
	}

	/**
	 * Get description.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newDescription java.lang.String
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	/**
	 * Get meaning.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getMeaning() {
		return meaning;
	}

	/**
	 * Set meaning.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newMeaning java.lang.String
	 */
	public void setMeaning(String newMeaning) {
		this.meaning = newMeaning;
	}

	/**
	 * Get reason.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Set reason.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newReason java.lang.String
	 */
	public void setReason(String newReason) {
		this.reason = newReason;
	}

	/**
	 * Get reasonCode.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * Set reasonCode.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newReasonCode java.lang.String
	 */
	public void setReasonCode(String newReasonCode) {
		this.reasonCode = newReasonCode;
	}

}
