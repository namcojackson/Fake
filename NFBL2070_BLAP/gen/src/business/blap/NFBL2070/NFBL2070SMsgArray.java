// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161201095243000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2070SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2070;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFBL2070 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2070SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2070SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2070SMsg
	 */
	public   NFBL2070SMsg no(int n){
		return ( NFBL2070SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2070SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2070SMsg();
	}
}