// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230316094857000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1080_OBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1080;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1080_OBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1080_OBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1080_OBMsg
	 */
	public   NPAL1080_OBMsg no(int n){
		return ( NPAL1080_OBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1080_OBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1080_OBMsg();
	}
}
