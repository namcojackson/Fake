// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160519111728000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6460_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6460;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL6460 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6460_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6460_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6460_ACMsg
	 */
	public   NMAL6460_ACMsg no(int n){
		return ( NMAL6460_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6460_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6460_ACMsg();
	}
}
