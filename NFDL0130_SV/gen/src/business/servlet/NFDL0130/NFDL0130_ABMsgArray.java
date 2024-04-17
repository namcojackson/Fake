// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160912182527000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0130_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0130;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFDL0130 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0130_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0130_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0130_ABMsg
	 */
	public   NFDL0130_ABMsg no(int n){
		return ( NFDL0130_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0130_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0130_ABMsg();
	}
}
