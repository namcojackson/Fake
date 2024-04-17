// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221128153648000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2760_CSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2760;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL2760 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL2760_CSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL2760_CSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL2760_CSMsg
	 */
	public   NFCL2760_CSMsg no(int n){
		return ( NFCL2760_CSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL2760_CSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL2760_CSMsg();
	}
}
