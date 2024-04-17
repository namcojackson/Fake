// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180803095224000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2170;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2170 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2170_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2170_DBMsg
	 */
	public   NWAL2170_DBMsg no(int n){
		return ( NWAL2170_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2170_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2170_DBMsg();
	}
}
