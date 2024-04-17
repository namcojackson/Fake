// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160107135830000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2090BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2090;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2090 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2090BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2090BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2090BMsg
	 */
	public   NWAL2090BMsg no(int n){
		return ( NWAL2090BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2090BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2090BMsg();
	}
}
