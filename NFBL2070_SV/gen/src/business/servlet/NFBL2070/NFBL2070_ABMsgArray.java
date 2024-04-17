// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161201095202000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2070_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL2070;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFBL2070 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2070_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2070_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2070_ABMsg
	 */
	public   NFBL2070_ABMsg no(int n){
		return ( NFBL2070_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2070_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2070_ABMsg();
	}
}
