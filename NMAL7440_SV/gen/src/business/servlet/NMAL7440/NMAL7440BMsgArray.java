// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230421095823000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7440BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7440;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7440 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7440BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7440BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7440BMsg
	 */
	public   NMAL7440BMsg no(int n){
		return ( NMAL7440BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7440BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7440BMsg();
	}
}
