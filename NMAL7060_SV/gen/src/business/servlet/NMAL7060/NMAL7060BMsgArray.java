// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180821154600000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7060BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7060;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7060 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7060BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7060BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7060BMsg
	 */
	public   NMAL7060BMsg no(int n){
		return ( NMAL7060BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7060BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7060BMsg();
	}
}
