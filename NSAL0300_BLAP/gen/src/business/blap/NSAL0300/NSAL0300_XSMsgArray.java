// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220331113802000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0300_XSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0300;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL0300 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0300_XSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0300_XSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0300_XSMsg
	 */
	public   NSAL0300_XSMsg no(int n){
		return ( NSAL0300_XSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0300_XSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0300_XSMsg();
	}
}
