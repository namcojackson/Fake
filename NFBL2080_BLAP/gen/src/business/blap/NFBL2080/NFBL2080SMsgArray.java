// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200519135117000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2080SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2080;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFBL2080 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBL2080SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBL2080SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBL2080SMsg
	 */
	public   NFBL2080SMsg no(int n){
		return ( NFBL2080SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBL2080SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBL2080SMsg();
	}
}
