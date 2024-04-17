// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160530135550000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2660BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2660;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL2660 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2660BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2660BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2660BMsg
	 */
	public   NMAL2660BMsg no(int n){
		return ( NMAL2660BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2660BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2660BMsg();
	}
}
