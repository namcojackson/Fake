// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170601091007000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0060F00FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLBL0060F00 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL0060F00FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL0060F00FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL0060F00FMsg
	 */
	public   NLBL0060F00FMsg no(int n){
		return ( NLBL0060F00FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL0060F00FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL0060F00FMsg();
	}
}
