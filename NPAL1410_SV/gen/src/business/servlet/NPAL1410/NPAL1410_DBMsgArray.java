// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190402141953000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1410;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1410 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1410_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1410_DBMsg
	 */
	public   NPAL1410_DBMsg no(int n){
		return ( NPAL1410_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1410_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1410_DBMsg();
	}
}
