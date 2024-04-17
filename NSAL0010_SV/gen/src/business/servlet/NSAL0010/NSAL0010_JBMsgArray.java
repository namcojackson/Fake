// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231031100058000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_JBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0010;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0010 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_JBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0010_JBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0010_JBMsg
	 */
	public   NSAL0010_JBMsg no(int n){
		return ( NSAL0010_JBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0010_JBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0010_JBMsg();
	}
}
