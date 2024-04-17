// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180828143538000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0060;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLEL0060 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLEL0060_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLEL0060_DBMsg
	 */
	public   NLEL0060_DBMsg no(int n){
		return ( NLEL0060_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLEL0060_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLEL0060_DBMsg();
	}
}
