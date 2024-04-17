// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170120105443000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1050_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1050;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1050 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1050_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1050_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1050_ACMsg
	 */
	public   NSAL1050_ACMsg no(int n){
		return ( NSAL1050_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1050_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1050_ACMsg();
	}
}