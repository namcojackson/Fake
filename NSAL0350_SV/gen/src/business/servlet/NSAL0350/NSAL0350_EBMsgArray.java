// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221012082348000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_EBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0350;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0350 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0350_EBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0350_EBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0350_EBMsg
	 */
	public   NSAL0350_EBMsg no(int n){
		return ( NSAL0350_EBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0350_EBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0350_EBMsg();
	}
}
