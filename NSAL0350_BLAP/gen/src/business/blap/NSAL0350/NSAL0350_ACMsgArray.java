// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221012084141000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0350;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0350 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0350_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0350_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0350_ACMsg
	 */
	public   NSAL0350_ACMsg no(int n){
		return ( NSAL0350_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0350_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0350_ACMsg();
	}
}