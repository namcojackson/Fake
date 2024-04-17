// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20210112102752000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1090BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1090;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1090 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1090BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1090BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1090BMsg
	 */
	public   NSAL1090BMsg no(int n){
		return ( NSAL1090BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1090BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1090BMsg();
	}
}
