// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180419161021000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0750BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL0750;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFCL0750 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL0750BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL0750BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL0750BMsg
	 */
	public   NFCL0750BMsg no(int n){
		return ( NFCL0750BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL0750BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL0750BMsg();
	}
}