// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160711144508000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3130_PSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3130;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL3130 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3130_PSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3130_PSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3130_PSMsg
	 */
	public   NFCL3130_PSMsg no(int n){
		return ( NFCL3130_PSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3130_PSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3130_PSMsg();
	}
}
