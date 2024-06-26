// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230207000518000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3170_LBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3170;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFCL3170 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3170_LBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3170_LBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3170_LBMsg
	 */
	public   NFCL3170_LBMsg no(int n){
		return ( NFCL3170_LBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3170_LBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3170_LBMsg();
	}
}
