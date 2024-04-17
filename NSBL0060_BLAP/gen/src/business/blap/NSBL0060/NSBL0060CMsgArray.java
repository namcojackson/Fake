// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190816113110000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0060CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSBL0060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0060CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0060CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0060CMsg
	 */
	public   NSBL0060CMsg no(int n){
		return ( NSBL0060CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0060CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0060CMsg();
	}
}
