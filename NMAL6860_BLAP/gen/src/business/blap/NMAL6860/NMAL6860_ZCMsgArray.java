// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200229144646000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6860_ZCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6860;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL6860 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6860_ZCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6860_ZCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6860_ZCMsg
	 */
	public   NMAL6860_ZCMsg no(int n){
		return ( NMAL6860_ZCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6860_ZCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6860_ZCMsg();
	}
}
