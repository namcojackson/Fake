// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200229144705000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6860_ZSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6860;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6860 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6860_ZSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6860_ZSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6860_ZSMsg
	 */
	public   NMAL6860_ZSMsg no(int n){
		return ( NMAL6860_ZSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6860_ZSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6860_ZSMsg();
	}
}
