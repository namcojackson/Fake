// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180625155248000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0080_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0080;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLEL0080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLEL0080_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLEL0080_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLEL0080_ABMsg
	 */
	public   NLEL0080_ABMsg no(int n){
		return ( NLEL0080_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLEL0080_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLEL0080_ABMsg();
	}
}