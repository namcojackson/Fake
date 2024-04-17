// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20091006193108000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0051CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0051;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZOL0051 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0051CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZOL0051CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZOL0051CMsg
	 */
	public   ZZOL0051CMsg no(int n){
		return ( ZZOL0051CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZOL0051CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZOL0051CMsg();
	}
}