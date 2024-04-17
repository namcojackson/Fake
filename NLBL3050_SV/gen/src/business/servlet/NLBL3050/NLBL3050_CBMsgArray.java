// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170601124214000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3050_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3050;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL3050 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3050_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3050_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3050_CBMsg
	 */
	public   NLBL3050_CBMsg no(int n){
		return ( NLBL3050_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3050_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3050_CBMsg();
	}
}
