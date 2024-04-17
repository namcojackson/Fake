// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230807173646000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0300_VBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0300;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0300 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0300_VBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0300_VBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0300_VBMsg
	 */
	public   NSAL0300_VBMsg no(int n){
		return ( NSAL0300_VBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0300_VBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0300_VBMsg();
	}
}
