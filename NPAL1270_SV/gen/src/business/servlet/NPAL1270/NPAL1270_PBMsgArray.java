// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230207094145000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1270_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1270;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1270 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1270_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1270_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1270_PBMsg
	 */
	public   NPAL1270_PBMsg no(int n){
		return ( NPAL1270_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1270_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1270_PBMsg();
	}
}
