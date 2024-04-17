// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181120162610000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1150_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1150;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1150 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1150_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1150_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1150_CBMsg
	 */
	public   NPAL1150_CBMsg no(int n){
		return ( NPAL1150_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1150_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1150_CBMsg();
	}
}