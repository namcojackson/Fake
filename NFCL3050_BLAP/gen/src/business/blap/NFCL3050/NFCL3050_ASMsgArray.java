// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221130103015000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3050_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3050;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL3050 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3050_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3050_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3050_ASMsg
	 */
	public   NFCL3050_ASMsg no(int n){
		return ( NFCL3050_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3050_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3050_ASMsg();
	}
}
