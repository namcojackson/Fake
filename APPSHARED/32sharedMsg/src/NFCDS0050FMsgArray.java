// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20110613201326000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCDS0050FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFCDS0050 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCDS0050FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCDS0050FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCDS0050FMsg
	 */
	public   NFCDS0050FMsg no(int n){
		return ( NFCDS0050FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCDS0050FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCDS0050FMsg();
	}
}
