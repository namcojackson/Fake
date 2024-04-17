// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20181114193308000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0770F00FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFCL0770F00 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL0770F00FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL0770F00FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL0770F00FMsg
	 */
	public   NFCL0770F00FMsg no(int n){
		return ( NFCL0770F00FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL0770F00FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL0770F00FMsg();
	}
}
