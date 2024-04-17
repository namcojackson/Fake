// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190808083623000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3000_ZBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3000;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFCL3000 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3000_ZBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3000_ZBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3000_ZBMsg
	 */
	public   NFCL3000_ZBMsg no(int n){
		return ( NFCL3000_ZBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3000_ZBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3000_ZBMsg();
	}
}
