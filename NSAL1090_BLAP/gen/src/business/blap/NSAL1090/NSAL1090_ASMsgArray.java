// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20210112120914000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1090_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1090;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL1090 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1090_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1090_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1090_ASMsg
	 */
	public   NSAL1090_ASMsg no(int n){
		return ( NSAL1090_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1090_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1090_ASMsg();
	}
}