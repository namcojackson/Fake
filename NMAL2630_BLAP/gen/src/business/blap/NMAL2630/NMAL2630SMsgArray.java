// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180327102528000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2630SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2630;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL2630 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2630SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2630SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2630SMsg
	 */
	public   NMAL2630SMsg no(int n){
		return ( NMAL2630SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2630SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2630SMsg();
	}
}
