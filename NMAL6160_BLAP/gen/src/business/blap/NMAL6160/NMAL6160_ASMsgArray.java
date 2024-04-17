// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160616181128000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6160_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6160;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6160 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6160_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6160_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6160_ASMsg
	 */
	public   NMAL6160_ASMsg no(int n){
		return ( NMAL6160_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6160_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6160_ASMsg();
	}
}
