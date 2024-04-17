// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151222183537000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3120_SBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3120;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL3120 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3120_SBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3120_SBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3120_SBMsg
	 */
	public   NLBL3120_SBMsg no(int n){
		return ( NLBL3120_SBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3120_SBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3120_SBMsg();
	}
}
