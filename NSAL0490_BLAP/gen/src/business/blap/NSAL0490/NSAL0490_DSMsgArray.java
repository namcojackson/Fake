// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190531153237000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0490_DSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0490;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL0490 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0490_DSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0490_DSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0490_DSMsg
	 */
	public   NSAL0490_DSMsg no(int n){
		return ( NSAL0490_DSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0490_DSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0490_DSMsg();
	}
}