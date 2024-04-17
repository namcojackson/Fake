// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170412114844000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0270SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0270;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSBL0270 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0270SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0270SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0270SMsg
	 */
	public   NSBL0270SMsg no(int n){
		return ( NSBL0270SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0270SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0270SMsg();
	}
}
