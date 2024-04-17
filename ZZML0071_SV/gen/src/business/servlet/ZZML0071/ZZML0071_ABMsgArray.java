// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20131112122711000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0071_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZML0071;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZML0071 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0071_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0071_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0071_ABMsg
	 */
	public   ZZML0071_ABMsg no(int n){
		return ( ZZML0071_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0071_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0071_ABMsg();
	}
}