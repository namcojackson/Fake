// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170419140056000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0420_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0420;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0420 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0420_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0420_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0420_ACMsg
	 */
	public   NSAL0420_ACMsg no(int n){
		return ( NSAL0420_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0420_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0420_ACMsg();
	}
}
