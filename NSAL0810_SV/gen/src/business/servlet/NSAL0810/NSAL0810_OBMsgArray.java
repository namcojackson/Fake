// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220328075727000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0810_OBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0810;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0810 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0810_OBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0810_OBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0810_OBMsg
	 */
	public   NSAL0810_OBMsg no(int n){
		return ( NSAL0810_OBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0810_OBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0810_OBMsg();
	}
}
