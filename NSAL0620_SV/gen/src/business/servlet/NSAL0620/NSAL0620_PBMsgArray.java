// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240208102635000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0620_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0620;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0620 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0620_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0620_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0620_PBMsg
	 */
	public   NSAL0620_PBMsg no(int n){
		return ( NSAL0620_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0620_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0620_PBMsg();
	}
}
