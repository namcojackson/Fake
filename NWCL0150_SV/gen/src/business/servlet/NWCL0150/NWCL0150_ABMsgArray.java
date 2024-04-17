// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180104145220000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0150_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWCL0150;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWCL0150 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWCL0150_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWCL0150_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWCL0150_ABMsg
	 */
	public   NWCL0150_ABMsg no(int n){
		return ( NWCL0150_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWCL0150_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWCL0150_ABMsg();
	}
}
