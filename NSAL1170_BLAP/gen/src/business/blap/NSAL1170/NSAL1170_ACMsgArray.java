// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161202170822000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1170_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1170;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1170 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1170_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1170_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1170_ACMsg
	 */
	public   NSAL1170_ACMsg no(int n){
		return ( NSAL1170_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1170_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1170_ACMsg();
	}
}
