// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161128143542000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0820_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0820;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0820 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0820_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0820_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0820_ABMsg
	 */
	public   NSAL0820_ABMsg no(int n){
		return ( NSAL0820_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0820_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0820_ABMsg();
	}
}
