// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230704192423000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0510_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0510;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL0510 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL0510_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL0510_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL0510_BCMsg
	 */
	public   NFCL0510_BCMsg no(int n){
		return ( NFCL0510_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL0510_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL0510_BCMsg();
	}
}
