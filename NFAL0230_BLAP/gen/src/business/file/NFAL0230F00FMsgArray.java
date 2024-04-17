// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160628101438000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0230F00FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFAL0230F00 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0230F00FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0230F00FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0230F00FMsg
	 */
	public   NFAL0230F00FMsg no(int n){
		return ( NFAL0230F00FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0230F00FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0230F00FMsg();
	}
}
