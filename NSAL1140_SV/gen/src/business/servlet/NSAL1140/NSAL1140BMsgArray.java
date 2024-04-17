// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170524135009000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1140BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1140;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1140 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1140BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1140BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1140BMsg
	 */
	public   NSAL1140BMsg no(int n){
		return ( NSAL1140BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1140BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1140BMsg();
	}
}
