// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180322162724000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3010_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL3010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3010_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3010_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3010_BCMsg
	 */
	public   NFCL3010_BCMsg no(int n){
		return ( NFCL3010_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3010_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3010_BCMsg();
	}
}
