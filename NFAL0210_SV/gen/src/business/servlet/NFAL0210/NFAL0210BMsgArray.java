// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160615113616000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0210BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0210;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFAL0210 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0210BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0210BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0210BMsg
	 */
	public   NFAL0210BMsg no(int n){
		return ( NFAL0210BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0210BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0210BMsg();
	}
}
