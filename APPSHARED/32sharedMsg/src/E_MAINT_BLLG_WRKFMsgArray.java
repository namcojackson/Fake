// This file was automatically generated by Logical Database Layout Definition Document and XLA200710010.
// Generated on    :20090907101702000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :LogicalDatabaseLayoutDefinition Document_AddIn_XLS200710010.xla XLA200710010
/*
 *E_MAINT_BLLG_WRKFMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is E_MAINT_BLLG_WRK Table Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class E_MAINT_BLLG_WRKFMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get E_MAINT_BLLG_WRKFMsg of the specified element number .
	 * @param n Index Number
	 * @return  E_MAINT_BLLG_WRKFMsg
	 */
	public   E_MAINT_BLLG_WRKFMsg no(int n){
		return ( E_MAINT_BLLG_WRKFMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  E_MAINT_BLLG_WRKFMsg
	 */
	public EZDMsg getMyComponent() {
		return new E_MAINT_BLLG_WRKFMsg();
	}
}
