// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20130716074944000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3030BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3030;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL3030 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3030BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3030BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3030BMsg
	 */
	public   NLBL3030BMsg no(int n){
		return ( NLBL3030BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3030BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3030BMsg();
	}
}