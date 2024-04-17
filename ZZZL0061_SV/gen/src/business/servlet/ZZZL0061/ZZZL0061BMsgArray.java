// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20110114105645000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0061BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0061;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZZL0061 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0061BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZZL0061BMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZL0061BMsg
	 */
	public   ZZZL0061BMsg no(int n){
		return ( ZZZL0061BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZL0061BMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZL0061BMsg();
	}
}