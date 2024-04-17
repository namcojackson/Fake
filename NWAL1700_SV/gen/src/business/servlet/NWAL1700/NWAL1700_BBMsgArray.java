// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230427160329000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_BBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1700;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1700 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1700_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1700_BBMsg
	 */
	public   NWAL1700_BBMsg no(int n){
		return ( NWAL1700_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1700_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1700_BBMsg();
	}
}
