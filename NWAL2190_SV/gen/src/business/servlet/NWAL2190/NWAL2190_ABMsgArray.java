// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531142520000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2190_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2190;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2190 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2190_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2190_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2190_ABMsg
	 */
	public   NWAL2190_ABMsg no(int n){
		return ( NWAL2190_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2190_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2190_ABMsg();
	}
}
