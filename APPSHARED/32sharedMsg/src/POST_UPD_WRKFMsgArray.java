// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160628090853000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA263001TFMsgArray.java Copyright FUJITSU LIMITED 2016
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.db;

import parts.common.*;
import parts.common.EZDFMsgArray;

/**
 * It is POST_UPD_WRK File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class POST_UPD_WRKFMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get POST_UPD_WRKFMsg of the specified element number .
	 * @param n Index Number
	 * @return  POST_UPD_WRKFMsg
	 */
	public   POST_UPD_WRKFMsg no(int n){
		return ( POST_UPD_WRKFMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  POST_UPD_WRKFMsg
	 */
	public EZDMsg getMyComponent() {
		return new POST_UPD_WRKFMsg();
	}
}