// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230419132753000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1070_XBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1070;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1070 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1070_XBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1070_XBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1070_XBMsg
	 */
	public   NPAL1070_XBMsg no(int n){
		return ( NPAL1070_XBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1070_XBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1070_XBMsg();
	}
}
