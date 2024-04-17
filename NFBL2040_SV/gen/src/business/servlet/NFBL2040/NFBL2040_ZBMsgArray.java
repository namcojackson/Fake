// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220221152832000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2040_ZBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL2040;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFBL2040 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2040_ZBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2040_ZBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2040_ZBMsg
	 */
	public   NFBL2040_ZBMsg no(int n){
		return ( NFBL2040_ZBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2040_ZBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2040_ZBMsg();
	}
}
