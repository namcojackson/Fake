// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151005141532000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0110_ZBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0110;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL0110 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL0110_ZBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL0110_ZBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL0110_ZBMsg
	 */
	public   NLBL0110_ZBMsg no(int n){
		return ( NLBL0110_ZBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL0110_ZBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL0110_ZBMsg();
	}
}