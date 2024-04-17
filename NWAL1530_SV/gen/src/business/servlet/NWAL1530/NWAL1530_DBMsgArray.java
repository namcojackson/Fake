// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230417140947000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1530_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1530;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1530 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1530_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1530_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1530_DBMsg
	 */
	public   NWAL1530_DBMsg no(int n){
		return ( NWAL1530_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1530_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1530_DBMsg();
	}
}