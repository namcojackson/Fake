// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171214105924000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0050BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZIL0050;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is ZZIL0050 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0050BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZIL0050BMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZIL0050BMsg
	 */
	public   ZZIL0050BMsg no(int n){
		return ( ZZIL0050BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZIL0050BMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZIL0050BMsg();
	}
}
