// This file was automatically generated by Logical Database Layout Definition Document and XLA200710010c.
// Generated on    :20231215164759000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010c
/*
 *SVC_MACH_UPLD_WRKFMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is SVC_MACH_UPLD_WRK Table Layout Message Array class.
 * @author
 * @version XLA200710010c
 */
public class SVC_MACH_UPLD_WRKFMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get SVC_MACH_UPLD_WRKFMsg of the specified element number .
	 * @param n Index Number
	 * @return  SVC_MACH_UPLD_WRKFMsg
	 */
	public   SVC_MACH_UPLD_WRKFMsg no(int n){
		return ( SVC_MACH_UPLD_WRKFMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  SVC_MACH_UPLD_WRKFMsg
	 */
	public EZDMsg getMyComponent() {
		return new SVC_MACH_UPLD_WRKFMsg();
	}
}
