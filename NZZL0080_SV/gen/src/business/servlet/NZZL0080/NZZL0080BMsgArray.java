// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220119120016000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NZZL0080BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NZZL0080;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NZZL0080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NZZL0080BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NZZL0080BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NZZL0080BMsg
	 */
	public   NZZL0080BMsg no(int n){
		return ( NZZL0080BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NZZL0080BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NZZL0080BMsg();
	}
}