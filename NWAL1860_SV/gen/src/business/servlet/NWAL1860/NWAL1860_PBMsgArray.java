// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151221153043000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1860_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1860;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1860 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1860_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1860_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1860_PBMsg
	 */
	public   NWAL1860_PBMsg no(int n){
		return ( NWAL1860_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1860_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1860_PBMsg();
	}
}
