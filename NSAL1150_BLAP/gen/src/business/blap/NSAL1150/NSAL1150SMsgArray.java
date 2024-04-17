// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180702185956000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1150SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1150;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL1150 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1150SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1150SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1150SMsg
	 */
	public   NSAL1150SMsg no(int n){
		return ( NSAL1150SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1150SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1150SMsg();
	}
}
