// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200229164558000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6850_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6850;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6850 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6850_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6850_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6850_ABMsg
	 */
	public   NMAL6850_ABMsg no(int n){
		return ( NMAL6850_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6850_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6850_ABMsg();
	}
}