// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160427144052000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2620_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2620;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL2620 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2620_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2620_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2620_ABMsg
	 */
	public   NMAL2620_ABMsg no(int n){
		return ( NMAL2620_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2620_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2620_ABMsg();
	}
}