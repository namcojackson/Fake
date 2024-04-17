// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170703172120000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_EBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLGL0010;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLGL0010 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_EBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLGL0010_EBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLGL0010_EBMsg
	 */
	public   NLGL0010_EBMsg no(int n){
		return ( NLGL0010_EBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLGL0010_EBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLGL0010_EBMsg();
	}
}
