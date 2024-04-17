// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118094538000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0390BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0390;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0390 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0390BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0390BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0390BMsg
	 */
	public   NSAL0390BMsg no(int n){
		return ( NSAL0390BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0390BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0390BMsg();
	}
}