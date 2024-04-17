// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180508103936000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2180_QBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2180;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2180 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2180_QBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2180_QBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2180_QBMsg
	 */
	public   NWAL2180_QBMsg no(int n){
		return ( NWAL2180_QBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2180_QBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2180_QBMsg();
	}
}