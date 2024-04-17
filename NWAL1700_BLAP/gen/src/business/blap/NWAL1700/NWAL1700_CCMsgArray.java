// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230427160253000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_CCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1700;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1700 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_CCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1700_CCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1700_CCMsg
	 */
	public   NWAL1700_CCMsg no(int n){
		return ( NWAL1700_CCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1700_CCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1700_CCMsg();
	}
}
