// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180515140156000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2650CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2650;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL2650 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL2650CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL2650CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL2650CMsg
	 */
	public   NFCL2650CMsg no(int n){
		return ( NFCL2650CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL2650CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL2650CMsg();
	}
}
