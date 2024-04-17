// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160616135507000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6160_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6160;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6160 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6160_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6160_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6160_CBMsg
	 */
	public   NMAL6160_CBMsg no(int n){
		return ( NMAL6160_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6160_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6160_CBMsg();
	}
}
