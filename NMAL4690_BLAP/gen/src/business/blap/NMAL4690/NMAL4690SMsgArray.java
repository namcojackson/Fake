// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20100526173835000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4690SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL4690;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL4690 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL4690SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL4690SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL4690SMsg
	 */
	public   NMAL4690SMsg no(int n){
		return ( NMAL4690SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL4690SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL4690SMsg();
	}
}
