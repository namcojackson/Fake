// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170901131729000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2460_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2460;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL2460 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2460_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2460_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2460_ASMsg
	 */
	public   NMAL2460_ASMsg no(int n){
		return ( NMAL2460_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2460_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2460_ASMsg();
	}
}
