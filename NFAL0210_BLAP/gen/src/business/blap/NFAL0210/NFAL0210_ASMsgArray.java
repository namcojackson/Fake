// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160615113719000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0210_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0210;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFAL0210 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0210_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0210_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0210_ASMsg
	 */
	public   NFAL0210_ASMsg no(int n){
		return ( NFAL0210_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0210_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0210_ASMsg();
	}
}
