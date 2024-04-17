// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20191001215250000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0330SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0330;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSBL0330 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0330SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0330SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0330SMsg
	 */
	public   NSBL0330SMsg no(int n){
		return ( NSBL0330SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0330SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0330SMsg();
	}
}
