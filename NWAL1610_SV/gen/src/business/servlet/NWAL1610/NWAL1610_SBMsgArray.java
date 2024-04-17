// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180411091628000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1610_SBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1610;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1610 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1610_SBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1610_SBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1610_SBMsg
	 */
	public   NWAL1610_SBMsg no(int n){
		return ( NWAL1610_SBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1610_SBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1610_SBMsg();
	}
}