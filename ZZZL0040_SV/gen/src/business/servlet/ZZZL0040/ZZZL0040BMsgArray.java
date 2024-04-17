// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20110104134022000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0040BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0040;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZZL0040 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0040BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZZL0040BMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZL0040BMsg
	 */
	public   ZZZL0040BMsg no(int n){
		return ( ZZZL0040BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZL0040BMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZL0040BMsg();
	}
}
