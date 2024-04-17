// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20090917193007000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZXL0050_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZXL0050;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZXL0050 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZXL0050_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZXL0050_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZXL0050_ABMsg
	 */
	public   ZZXL0050_ABMsg no(int n){
		return ( ZZXL0050_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZXL0050_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZXL0050_ABMsg();
	}
}
