// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160926205018000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2120_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL2120;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFBL2120 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2120_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2120_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2120_ABMsg
	 */
	public   NFBL2120_ABMsg no(int n){
		return ( NFBL2120_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2120_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2120_ABMsg();
	}
}
