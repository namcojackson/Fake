// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231106120032000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_QSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6700;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6700 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_QSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6700_QSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6700_QSMsg
	 */
	public   NMAL6700_QSMsg no(int n){
		return ( NMAL6700_QSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6700_QSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6700_QSMsg();
	}
}
