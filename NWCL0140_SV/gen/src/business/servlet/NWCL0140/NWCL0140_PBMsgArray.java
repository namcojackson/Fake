// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160624110158000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0140_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWCL0140;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWCL0140 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWCL0140_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWCL0140_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWCL0140_PBMsg
	 */
	public   NWCL0140_PBMsg no(int n){
		return ( NWCL0140_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWCL0140_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWCL0140_PBMsg();
	}
}
