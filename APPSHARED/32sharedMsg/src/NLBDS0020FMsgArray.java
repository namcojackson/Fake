// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20090908120326000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBDS0020FMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDFMsgArray;

/**
 * It is NLBDS0020 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBDS0020FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBDS0020FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBDS0020FMsg
	 */
	public   NLBDS0020FMsg no(int n){
		return ( NLBDS0020FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBDS0020FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBDS0020FMsg();
	}
}