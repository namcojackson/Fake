// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221125172346000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL5140BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL5140;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFCL5140 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL5140BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL5140BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL5140BMsg
	 */
	public   NFCL5140BMsg no(int n){
		return ( NFCL5140BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL5140BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL5140BMsg();
	}
}
