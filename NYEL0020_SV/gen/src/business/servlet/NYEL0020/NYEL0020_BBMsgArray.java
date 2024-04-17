// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20090818183758000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL0020_BBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL0020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NYEL0020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL0020_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL0020_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL0020_BBMsg
	 */
	public   NYEL0020_BBMsg no(int n){
		return ( NYEL0020_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL0020_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL0020_BBMsg();
	}
}