// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180911141245000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0760_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0760;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL0760 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL0760_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL0760_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL0760_ASMsg
	 */
	public   NFCL0760_ASMsg no(int n){
		return ( NFCL0760_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL0760_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL0760_ASMsg();
	}
}
