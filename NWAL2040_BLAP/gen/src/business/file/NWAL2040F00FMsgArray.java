// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170914152533000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2040F00FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWAL2040F00 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2040F00FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2040F00FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2040F00FMsg
	 */
	public   NWAL2040F00FMsg no(int n){
		return ( NWAL2040F00FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2040F00FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2040F00FMsg();
	}
}
