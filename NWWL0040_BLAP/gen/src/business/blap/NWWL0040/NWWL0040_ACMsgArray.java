// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160727130938000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0040_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWWL0040;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;

/**
 * It is NWWL0040 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWWL0040_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWWL0040_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWWL0040_ACMsg
	 */
	public   NWWL0040_ACMsg no(int n){
		return ( NWWL0040_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWWL0040_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWWL0040_ACMsg();
	}
}