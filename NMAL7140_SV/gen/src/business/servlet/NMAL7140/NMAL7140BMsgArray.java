// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191206145320000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7140BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7140;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7140 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7140BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7140BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7140BMsg
	 */
	public   NMAL7140BMsg no(int n){
		return ( NMAL7140BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7140BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7140BMsg();
	}
}
