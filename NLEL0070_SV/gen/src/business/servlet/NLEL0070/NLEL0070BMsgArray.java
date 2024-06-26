// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160328214224000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0070BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0070;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLEL0070 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLEL0070BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLEL0070BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLEL0070BMsg
	 */
	public   NLEL0070BMsg no(int n){
		return ( NLEL0070BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLEL0070BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLEL0070BMsg();
	}
}
