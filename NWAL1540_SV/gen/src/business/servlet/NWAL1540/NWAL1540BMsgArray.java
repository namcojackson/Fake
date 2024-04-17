// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161018162428000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1540BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1540;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1540 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1540BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1540BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1540BMsg
	 */
	public   NWAL1540BMsg no(int n){
		return ( NWAL1540BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1540BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1540BMsg();
	}
}
