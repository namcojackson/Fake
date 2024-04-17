// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160414022320000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0600_RBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0600;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLCL0600 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0600_RBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0600_RBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0600_RBMsg
	 */
	public   NLCL0600_RBMsg no(int n){
		return ( NLCL0600_RBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0600_RBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0600_RBMsg();
	}
}
