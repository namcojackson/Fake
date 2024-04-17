// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161202170823000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1170SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1170;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL1170 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1170SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1170SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1170SMsg
	 */
	public   NSAL1170SMsg no(int n){
		return ( NSAL1170SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1170SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1170SMsg();
	}
}
