// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181114091505000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL5140_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL5140;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL5140 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL5140_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL5140_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL5140_ASMsg
	 */
	public   NFCL5140_ASMsg no(int n){
		return ( NFCL5140_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL5140_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL5140_ASMsg();
	}
}
