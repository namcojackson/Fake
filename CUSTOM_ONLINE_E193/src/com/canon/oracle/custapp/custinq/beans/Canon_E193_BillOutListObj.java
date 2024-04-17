package com.canon.oracle.custapp.custinq.beans;

/**
 * Title:		Canon_E193_BillOutListObj<br>
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

public class Canon_E193_BillOutListObj {
	
	private int ticketId;
	private String outStatus;
	private String outMessage;

	/**
	 * Canon_E193_BillOutListObj constructor comment.
	 */
	public Canon_E193_BillOutListObj() {
		super();
	}

	/**
	 * Get catDesc.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public int getticketId() {
		return ticketId;
	}

	/**
	 * Set catDesc.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newCatDesc java.lang.String
	 */
	public void setticketId(int newticketId) {
		this.ticketId = newticketId;
	}

	/**
	 * Get catId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return int
	 */
	public String getoutStatus() {
		return outStatus;
	}

	/**
	 * Set catId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newCatId int
	 */
	public void setoutStatus(String newoutStatus) {
		this.outStatus = newoutStatus;
	}

	/**
	 * Get parentCatId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @return int
	 */
	public String getoutMessage() {
		return outMessage;
	}

	/**
	 * Set parentCatId.
	 * Creation date: (9/15/2005 4:30:45 PM)
	 * @param newParentCatId int
	 */
	public void setoutMessage(String newoutMessage) {
		this.outMessage = newoutMessage;
	}


}
