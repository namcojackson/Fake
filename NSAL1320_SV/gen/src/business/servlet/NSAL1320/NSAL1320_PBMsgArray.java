// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325170156000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1320;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL1320 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1320_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1320_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1320_PBMsg
	 */
	public   NSAL1320_PBMsg no(int n){
		return ( NSAL1320_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1320_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1320_PBMsg();
	}
}
