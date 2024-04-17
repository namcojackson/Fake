// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231010145423000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1840;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1840 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1840_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1840_DBMsg
	 */
	public   NWAL1840_DBMsg no(int n){
		return ( NWAL1840_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1840_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1840_DBMsg();
	}
}
