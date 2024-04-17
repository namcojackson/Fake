// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160627164929000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8860BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8860;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NYEL8860 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8860BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8860BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8860BMsg
	 */
	public   NYEL8860BMsg no(int n){
		return ( NYEL8860BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8860BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8860BMsg();
	}
}
