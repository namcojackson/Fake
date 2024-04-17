// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091026144053000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0020_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZBL0020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZBL0020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0020_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZBL0020_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZBL0020_ABMsg
	 */
	public   ZZBL0020_ABMsg no(int n){
		return ( ZZBL0020_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZBL0020_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZBL0020_ABMsg();
	}
}