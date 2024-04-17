// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20131108141510000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0100CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0100;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZIL0100 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0100CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZIL0100CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZIL0100CMsg
	 */
	public   ZZIL0100CMsg no(int n){
		return ( ZZIL0100CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZIL0100CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZIL0100CMsg();
	}
}