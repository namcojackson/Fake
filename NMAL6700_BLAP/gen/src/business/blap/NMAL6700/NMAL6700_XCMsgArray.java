// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150420142305000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_XCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6700;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;

/**
 * It is NMAL6700 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_XCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6700_XCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6700_XCMsg
	 */
	public   NMAL6700_XCMsg no(int n){
		return ( NMAL6700_XCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6700_XCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6700_XCMsg();
	}
}