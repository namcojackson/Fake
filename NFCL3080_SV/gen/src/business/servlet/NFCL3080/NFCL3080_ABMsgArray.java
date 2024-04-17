// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180629090610000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3080;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFCL3080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3080_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3080_ABMsg
	 */
	public   NFCL3080_ABMsg no(int n){
		return ( NFCL3080_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3080_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3080_ABMsg();
	}
}