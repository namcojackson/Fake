// This file was automatically generated by Screen Field Definition Document and XLA710010.
// Generated on    :20180801091329000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA710010
/*
 *NFDL0010_MBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0010;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFDL0010 Screen Data Message Array class.
 * @author
 * @version XLA710010
 */
public class NFDL0010_MBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0010_MBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0010_MBMsg
	 */
	public   NFDL0010_MBMsg no(int n){
		return ( NFDL0010_MBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0010_MBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0010_MBMsg();
	}
}
