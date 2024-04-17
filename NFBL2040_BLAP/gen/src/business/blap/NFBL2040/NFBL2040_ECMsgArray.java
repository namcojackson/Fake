// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220221152922000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2040_ECMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2040;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFBL2040 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2040_ECMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2040_ECMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2040_ECMsg
	 */
	public   NFBL2040_ECMsg no(int n){
		return ( NFBL2040_ECMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2040_ECMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2040_ECMsg();
	}
}