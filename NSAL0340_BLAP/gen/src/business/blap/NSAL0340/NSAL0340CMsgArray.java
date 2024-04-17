// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530103809000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0340CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0340;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0340 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0340CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0340CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0340CMsg
	 */
	public   NSAL0340CMsg no(int n){
		return ( NSAL0340CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0340CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0340CMsg();
	}
}