// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230831190543000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1160_EBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1160;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1160 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1160_EBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1160_EBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1160_EBMsg
	 */
	public   NPAL1160_EBMsg no(int n){
		return ( NPAL1160_EBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1160_EBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1160_EBMsg();
	}
}
