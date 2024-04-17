// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160906145051000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0060_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWWL0060;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWWL0060 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWWL0060_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWWL0060_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWWL0060_ASMsg
	 */
	public   NWWL0060_ASMsg no(int n){
		return ( NWWL0060_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWWL0060_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWWL0060_ASMsg();
	}
}