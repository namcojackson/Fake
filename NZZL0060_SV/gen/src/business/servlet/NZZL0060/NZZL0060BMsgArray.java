// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170216154532000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NZZL0060BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NZZL0060;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NZZL0060 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NZZL0060BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NZZL0060BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NZZL0060BMsg
	 */
	public   NZZL0060BMsg no(int n){
		return ( NZZL0060BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NZZL0060BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NZZL0060BMsg();
	}
}