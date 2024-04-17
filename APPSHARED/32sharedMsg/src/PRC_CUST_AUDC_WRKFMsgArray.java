// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160419103331000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *PRC_CUST_AUDC_WRKFMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is PRC_CUST_AUDC_WRK File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class PRC_CUST_AUDC_WRKFMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get PRC_CUST_AUDC_WRKFMsg of the specified element number .
	 * @param n Index Number
	 * @return  PRC_CUST_AUDC_WRKFMsg
	 */
	public   PRC_CUST_AUDC_WRKFMsg no(int n){
		return ( PRC_CUST_AUDC_WRKFMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  PRC_CUST_AUDC_WRKFMsg
	 */
	public EZDMsg getMyComponent() {
		return new PRC_CUST_AUDC_WRKFMsg();
	}
}
