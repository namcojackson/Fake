// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180515100051000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6710_CSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6710;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6710 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6710_CSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6710_CSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6710_CSMsg
	 */
	public   NMAL6710_CSMsg no(int n){
		return ( NMAL6710_CSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6710_CSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6710_CSMsg();
	}
}