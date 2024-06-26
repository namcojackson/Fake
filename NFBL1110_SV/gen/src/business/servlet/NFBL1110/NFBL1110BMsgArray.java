// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170316171714000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1110BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL1110;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFBL1110 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL1110BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL1110BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL1110BMsg
	 */
	public   NFBL1110BMsg no(int n){
		return ( NFBL1110BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL1110BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL1110BMsg();
	}
}
