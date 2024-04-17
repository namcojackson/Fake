// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161129191315000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0950CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0950;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0950 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0950CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0950CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0950CMsg
	 */
	public   NSAL0950CMsg no(int n){
		return ( NSAL0950CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0950CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0950CMsg();
	}
}
