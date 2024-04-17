// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160914093253000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0020_BBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWWL0020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWWL0020_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWWL0020_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWWL0020_BBMsg
	 */
	public   NWWL0020_BBMsg no(int n){
		return ( NWWL0020_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWWL0020_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWWL0020_BBMsg();
	}
}
