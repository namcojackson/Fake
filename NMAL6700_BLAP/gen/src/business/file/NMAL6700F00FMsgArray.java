// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160302151714000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700F00FMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.EZDFMsgArray;
import parts.common.EZDMsg;

/**
 * It is NMAL6700F00 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700F00FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6700F00FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6700F00FMsg
	 */
	public   NMAL6700F00FMsg no(int n){
		return ( NMAL6700F00FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6700F00FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6700F00FMsg();
	}
}
