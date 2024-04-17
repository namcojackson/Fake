// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161202170836000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1170_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1170;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1170 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1170_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1170_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1170_ABMsg
	 */
	public   NSAL1170_ABMsg no(int n){
		return ( NSAL1170_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1170_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1170_ABMsg();
	}
}
