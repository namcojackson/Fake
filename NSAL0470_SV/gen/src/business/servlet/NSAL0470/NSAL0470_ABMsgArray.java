// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170725132811000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0470_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0470;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0470 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0470_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0470_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0470_ABMsg
	 */
	public   NSAL0470_ABMsg no(int n){
		return ( NSAL0470_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0470_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0470_ABMsg();
	}
}
