// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160913203413000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8880CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8880;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NYEL8880 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8880CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8880CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8880CMsg
	 */
	public   NYEL8880CMsg no(int n){
		return ( NYEL8880CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8880CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8880CMsg();
	}
}
