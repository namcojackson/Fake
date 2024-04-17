// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118110609000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_QBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1330;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1330 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_QBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1330_QBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1330_QBMsg
	 */
	public   NSAL1330_QBMsg no(int n){
		return ( NSAL1330_QBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1330_QBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1330_QBMsg();
	}
}
