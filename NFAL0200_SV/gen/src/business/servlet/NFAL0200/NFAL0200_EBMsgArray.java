// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171204191917000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0200_EBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0200;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NFAL0200 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0200_EBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0200_EBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0200_EBMsg
	 */
	public   NFAL0200_EBMsg no(int n){
		return ( NFAL0200_EBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0200_EBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0200_EBMsg();
	}
}
