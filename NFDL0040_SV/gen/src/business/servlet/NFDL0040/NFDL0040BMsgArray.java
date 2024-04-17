// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230519113131000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0040BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0040;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFDL0040 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0040BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0040BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0040BMsg
	 */
	public   NFDL0040BMsg no(int n){
		return ( NFDL0040BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0040BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0040BMsg();
	}
}
