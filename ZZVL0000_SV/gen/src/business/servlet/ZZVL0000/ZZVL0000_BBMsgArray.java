// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161118182617000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0000_BBMsgArray.java Copyright FUJITSU LIMITED 2007
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
public class ZZVL0000_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZVL0000_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZVL0000_BBMsg
	 */
	public   ZZVL0000_BBMsg no(int n){
		return ( ZZVL0000_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZVL0000_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZVL0000_BBMsg();
	}
}