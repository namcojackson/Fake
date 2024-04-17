// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100922170743000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0120_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0120;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZOL0120 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0120_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZOL0120_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZOL0120_ABMsg
	 */
	public   ZZOL0120_ABMsg no(int n){
		return ( ZZOL0120_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZOL0120_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZOL0120_ABMsg();
	}
}