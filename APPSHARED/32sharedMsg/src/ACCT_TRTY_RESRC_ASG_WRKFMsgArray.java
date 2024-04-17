// This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20171012164732000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *ACCT_TRTY_RESRC_ASG_WRKFMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.db;

import parts.common.*;
import parts.common.EZDFMsgArray;

/**
 * It is ACCT_TRTY_RESRC_ASG_WRK Table Layout Message Array class.
 * @author
 * @version XLA200710010c
 */
public class ACCT_TRTY_RESRC_ASG_WRKFMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ACCT_TRTY_RESRC_ASG_WRKFMsg of the specified element number .
	 * @param n Index Number
	 * @return  ACCT_TRTY_RESRC_ASG_WRKFMsg
	 */
	public   ACCT_TRTY_RESRC_ASG_WRKFMsg no(int n){
		return ( ACCT_TRTY_RESRC_ASG_WRKFMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ACCT_TRTY_RESRC_ASG_WRKFMsg
	 */
	public EZDMsg getMyComponent() {
		return new ACCT_TRTY_RESRC_ASG_WRKFMsg();
	}
}
