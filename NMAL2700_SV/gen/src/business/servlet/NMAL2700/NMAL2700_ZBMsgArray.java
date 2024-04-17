// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531092317000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2700_ZBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2700;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL2700 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2700_ZBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2700_ZBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2700_ZBMsg
	 */
	public   NMAL2700_ZBMsg no(int n){
		return ( NMAL2700_ZBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2700_ZBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2700_ZBMsg();
	}
}
