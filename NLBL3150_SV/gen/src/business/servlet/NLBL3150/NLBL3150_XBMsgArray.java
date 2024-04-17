// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170803014821000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3150_XBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3150;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL3150 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3150_XBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3150_XBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3150_XBMsg
	 */
	public   NLBL3150_XBMsg no(int n){
		return ( NLBL3150_XBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3150_XBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3150_XBMsg();
	}
}
