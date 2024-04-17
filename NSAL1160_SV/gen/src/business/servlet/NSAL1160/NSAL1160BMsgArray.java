// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160128112518000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1160BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1160;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1160 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1160BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1160BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1160BMsg
	 */
	public   NSAL1160BMsg no(int n){
		return ( NSAL1160BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1160BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1160BMsg();
	}
}