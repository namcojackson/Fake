// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230427160255000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_GSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1700;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL1700 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_GSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1700_GSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1700_GSMsg
	 */
	public   NWAL1700_GSMsg no(int n){
		return ( NWAL1700_GSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1700_GSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1700_GSMsg();
	}
}
