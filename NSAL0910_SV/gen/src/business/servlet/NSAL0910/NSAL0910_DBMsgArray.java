// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160616091432000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0910_DBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0910;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSAL0910 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0910_DBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0910_DBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0910_DBMsg
	 */
	public   NSAL0910_DBMsg no(int n){
		return ( NSAL0910_DBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0910_DBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0910_DBMsg();
	}
}