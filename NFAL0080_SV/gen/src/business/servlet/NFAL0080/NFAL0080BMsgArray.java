// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100121185548000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0080BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0080;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFAL0080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0080BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0080BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0080BMsg
	 */
	public   NFAL0080BMsg no(int n){
		return ( NFAL0080BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0080BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0080BMsg();
	}
}