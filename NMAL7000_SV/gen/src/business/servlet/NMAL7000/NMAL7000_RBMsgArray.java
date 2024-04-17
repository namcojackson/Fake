// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160801150152000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7000_RBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7000;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7000 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7000_RBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7000_RBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7000_RBMsg
	 */
	public   NMAL7000_RBMsg no(int n){
		return ( NMAL7000_RBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7000_RBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7000_RBMsg();
	}
}
