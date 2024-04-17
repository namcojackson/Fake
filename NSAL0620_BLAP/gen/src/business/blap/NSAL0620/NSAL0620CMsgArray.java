// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240208110828000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0620CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0620;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0620 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0620CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0620CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0620CMsg
	 */
	public   NSAL0620CMsg no(int n){
		return ( NSAL0620CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0620CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0620CMsg();
	}
}