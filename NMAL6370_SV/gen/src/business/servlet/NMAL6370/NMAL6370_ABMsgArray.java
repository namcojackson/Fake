// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100702133417000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6370_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6370;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6370 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6370_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6370_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6370_ABMsg
	 */
	public   NMAL6370_ABMsg no(int n){
		return ( NMAL6370_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6370_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6370_ABMsg();
	}
}