// This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20110623223108000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *AR_PURGE_OFS_UPLD_WRKPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.db;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is AR_PURGE_OFS_UPLD_WRK Table Layout Message Array class.
 * @author
 * @version XLA200710010c
 */
public class AR_PURGE_OFS_UPLD_WRKPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get AR_PURGE_OFS_UPLD_WRKPMsg of the specified element number .
	 * @param n Index Number
	 * @return  AR_PURGE_OFS_UPLD_WRKPMsg
	 */
	public   AR_PURGE_OFS_UPLD_WRKPMsg no(int n){
		return ( AR_PURGE_OFS_UPLD_WRKPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  AR_PURGE_OFS_UPLD_WRKPMsg
	 */
	public EZDMsg getMyComponent() {
		return new AR_PURGE_OFS_UPLD_WRKPMsg();
	}
}