package com.canon.oracle.custapp.custinq.beans;

/**
 * Title:		Canon_E193_TicketDetail<br>
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

public class Canon_E193_TicketDetailObj {
	
	private int iticketId;
	private String strMessage;
	
	
	@Override
	public String toString() {
		return "Canon_E193_TicketDetailObj [iticketId=" + iticketId
				+ ", strMessage=" + strMessage + "]";
	}

	/**
	 * Canon_E193_TicketDetailObj constructor comment.
	 */
	
	public Canon_E193_TicketDetailObj() {
		super();
	}

	/**
	 * Get description.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public int getTicketId() {
		return iticketId;
	}

	/**
	 * Set description.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newDescription java.lang.String
	 */
	public void setTicketId(int newTicketId) {
		this.iticketId = newTicketId;
	}

	/**
	 * Get meaning.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getMessage() {
		return strMessage;
	}

	/**
	 * Set meaning.
	 * Creation date: (9/14/2005 4:30:45 PM)
	 * @param newMeaning java.lang.String
	 */
	public void setMessage(String newMessage) {
		this.strMessage = newMessage;
	}

}
