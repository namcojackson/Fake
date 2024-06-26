// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230208094935000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6730_BSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6730;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6730 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6730_BSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6730_BSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6730_BSMsg
	 */
	public   NMAL6730_BSMsg no(int n){
		return ( NMAL6730_BSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6730_BSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6730_BSMsg();
	}
}
