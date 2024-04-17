// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20100406085620000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBDS0100FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFBDS0100 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBDS0100FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBDS0100FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBDS0100FMsg
	 */
	public   NFBDS0100FMsg no(int n){
		return ( NFBDS0100FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBDS0100FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBDS0100FMsg();
	}
}
