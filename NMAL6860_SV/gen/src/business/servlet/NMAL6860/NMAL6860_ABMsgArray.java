// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200229105712000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6860_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6860;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6860 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6860_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6860_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6860_ABMsg
	 */
	public   NMAL6860_ABMsg no(int n){
		return ( NMAL6860_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6860_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6860_ABMsg();
	}
}
