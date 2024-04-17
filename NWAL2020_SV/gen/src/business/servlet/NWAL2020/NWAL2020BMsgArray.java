// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240411084402000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2020BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2020BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2020BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2020BMsg
	 */
	public   NWAL2020BMsg no(int n){
		return ( NWAL2020BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2020BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2020BMsg();
	}
}
