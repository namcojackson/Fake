// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200226172517000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0030CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0030;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZML0030 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0030CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0030CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0030CMsg
	 */
	public   ZZML0030CMsg no(int n){
		return ( ZZML0030CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0030CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0030CMsg();
	}
}
