// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160122113603000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1080CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1080;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1080 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1080CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1080CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1080CMsg
	 */
	public   NSAL1080CMsg no(int n){
		return ( NSAL1080CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1080CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1080CMsg();
	}
}
