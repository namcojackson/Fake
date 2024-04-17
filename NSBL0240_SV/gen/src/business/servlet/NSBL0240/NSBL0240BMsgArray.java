// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170412110717000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0240BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0240;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSBL0240 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0240BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0240BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0240BMsg
	 */
	public   NSBL0240BMsg no(int n){
		return ( NSBL0240BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0240BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0240BMsg();
	}
}
