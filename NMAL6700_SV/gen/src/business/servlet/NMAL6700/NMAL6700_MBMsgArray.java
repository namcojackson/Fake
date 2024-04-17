// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231106144517000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_MBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6700;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6700 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_MBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6700_MBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6700_MBMsg
	 */
	public   NMAL6700_MBMsg no(int n){
		return ( NMAL6700_MBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6700_MBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6700_MBMsg();
	}
}
