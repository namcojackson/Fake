// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531102245000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL0100BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL0100;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL0100 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL0100BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL0100BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL0100BMsg
	 */
	public   NPAL0100BMsg no(int n){
		return ( NPAL0100BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL0100BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL0100BMsg();
	}
}
