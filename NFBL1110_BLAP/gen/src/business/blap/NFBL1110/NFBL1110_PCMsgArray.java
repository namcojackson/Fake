// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160930131141000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1110_PCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL1110;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFBL1110 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL1110_PCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL1110_PCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL1110_PCMsg
	 */
	public   NFBL1110_PCMsg no(int n){
		return ( NFBL1110_PCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL1110_PCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL1110_PCMsg();
	}
}
