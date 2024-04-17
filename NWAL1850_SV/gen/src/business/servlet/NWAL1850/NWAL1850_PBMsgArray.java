// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220603143922000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1850_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1850;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1850 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1850_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1850_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1850_PBMsg
	 */
	public   NWAL1850_PBMsg no(int n){
		return ( NWAL1850_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1850_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1850_PBMsg();
	}
}
