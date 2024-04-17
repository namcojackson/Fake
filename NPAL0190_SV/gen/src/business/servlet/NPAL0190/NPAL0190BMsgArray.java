// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160406034025000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL0190BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL0190;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL0190 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL0190BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL0190BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL0190BMsg
	 */
	public   NPAL0190BMsg no(int n){
		return ( NPAL0190BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL0190BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL0190BMsg();
	}
}
