// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161005163355000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0030_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLGL0030;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLGL0030 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLGL0030_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLGL0030_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLGL0030_ABMsg
	 */
	public   NLGL0030_ABMsg no(int n){
		return ( NLGL0030_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLGL0030_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLGL0030_ABMsg();
	}
}
