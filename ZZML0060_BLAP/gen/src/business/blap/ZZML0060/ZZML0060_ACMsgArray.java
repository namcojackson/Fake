// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100128193324000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0060_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZML0060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0060_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0060_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0060_ACMsg
	 */
	public   ZZML0060_ACMsg no(int n){
		return ( ZZML0060_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0060_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0060_ACMsg();
	}
}
