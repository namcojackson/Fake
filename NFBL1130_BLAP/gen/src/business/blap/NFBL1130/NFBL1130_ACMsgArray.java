// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220812103814000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1130_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL1130;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFBL1130 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL1130_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL1130_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL1130_ACMsg
	 */
	public   NFBL1130_ACMsg no(int n){
		return ( NFBL1130_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL1130_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL1130_ACMsg();
	}
}
