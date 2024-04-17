// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240325170413000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1350_OCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1350;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1350 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1350_OCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1350_OCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1350_OCMsg
	 */
	public   NSAL1350_OCMsg no(int n){
		return ( NSAL1350_OCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1350_OCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1350_OCMsg();
	}
}
