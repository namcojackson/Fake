// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181130181851000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1900_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1900;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1900 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1900_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1900_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1900_ABMsg
	 */
	public   NWAL1900_ABMsg no(int n){
		return ( NWAL1900_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1900_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1900_ABMsg();
	}
}
