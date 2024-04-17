// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240202111124000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2060_DCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFBL2060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2060_DCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2060_DCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2060_DCMsg
	 */
	public   NFBL2060_DCMsg no(int n){
		return ( NFBL2060_DCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2060_DCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2060_DCMsg();
	}
}
