// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160729102339000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0040_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0040;

import parts.common.EZDBMsgArray;
import parts.common.EZDMsg;

/**
 * It is NWWL0040 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWWL0040_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWWL0040_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWWL0040_ABMsg
	 */
	public   NWWL0040_ABMsg no(int n){
		return ( NWWL0040_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWWL0040_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWWL0040_ABMsg();
	}
}
