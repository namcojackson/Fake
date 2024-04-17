// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160609103407000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1090_FBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1090;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1090 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1090_FBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1090_FBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1090_FBMsg
	 */
	public   NPAL1090_FBMsg no(int n){
		return ( NPAL1090_FBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1090_FBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1090_FBMsg();
	}
}
