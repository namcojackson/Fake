// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140618174623000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0600_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0600;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZOL0600 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0600_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZOL0600_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZOL0600_ACMsg
	 */
	public   ZZOL0600_ACMsg no(int n){
		return ( ZZOL0600_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZOL0600_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZOL0600_ACMsg();
	}
}
