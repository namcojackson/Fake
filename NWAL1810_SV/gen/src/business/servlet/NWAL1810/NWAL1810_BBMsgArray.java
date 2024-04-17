// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221104102624000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1810_BBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1810;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL1810 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1810_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1810_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1810_BBMsg
	 */
	public   NWAL1810_BBMsg no(int n){
		return ( NWAL1810_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1810_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1810_BBMsg();
	}
}