// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230905122331000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8810_QBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8810;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NYEL8810 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8810_QBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8810_QBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8810_QBMsg
	 */
	public   NYEL8810_QBMsg no(int n){
		return ( NYEL8810_QBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8810_QBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8810_QBMsg();
	}
}