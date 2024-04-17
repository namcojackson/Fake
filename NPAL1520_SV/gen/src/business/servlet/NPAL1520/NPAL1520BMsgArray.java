// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221020071144000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1520BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1520;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1520 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1520BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1520BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1520BMsg
	 */
	public   NPAL1520BMsg no(int n){
		return ( NPAL1520BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1520BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1520BMsg();
	}
}
