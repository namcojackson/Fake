// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180307151001000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3070_TBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3070;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL3070 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3070_TBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3070_TBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3070_TBMsg
	 */
	public   NLBL3070_TBMsg no(int n){
		return ( NLBL3070_TBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3070_TBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3070_TBMsg();
	}
}
