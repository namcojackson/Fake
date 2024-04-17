// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140221130226000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZKL0010_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZKL0010;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZKL0010 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZKL0010_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZKL0010_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZKL0010_ABMsg
	 */
	public   ZZKL0010_ABMsg no(int n){
		return ( ZZKL0010_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZKL0010_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZKL0010_ABMsg();
	}
}