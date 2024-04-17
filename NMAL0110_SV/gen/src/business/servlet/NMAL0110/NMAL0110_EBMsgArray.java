// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231219170400000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_EBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0110;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL0110 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_EBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0110_EBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0110_EBMsg
	 */
	public   NMAL0110_EBMsg no(int n){
		return ( NMAL0110_EBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0110_EBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0110_EBMsg();
	}
}
