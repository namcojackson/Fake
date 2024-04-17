// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161118182617000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0000BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZVL0000;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZVL0000 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0000BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZVL0000BMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZVL0000BMsg
	 */
	public   ZZVL0000BMsg no(int n){
		return ( ZZVL0000BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZVL0000BMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZVL0000BMsg();
	}
}
