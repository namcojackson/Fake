// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190205162146000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0090_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0090;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFDL0090 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0090_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0090_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0090_ASMsg
	 */
	public   NFDL0090_ASMsg no(int n){
		return ( NFDL0090_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0090_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0090_ASMsg();
	}
}
