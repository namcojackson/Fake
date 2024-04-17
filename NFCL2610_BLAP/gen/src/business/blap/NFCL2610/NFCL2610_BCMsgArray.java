// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220727142342000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2610_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2610;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL2610 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL2610_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL2610_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL2610_BCMsg
	 */
	public   NFCL2610_BCMsg no(int n){
		return ( NFCL2610_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL2610_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL2610_BCMsg();
	}
}
