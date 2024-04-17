// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090504125859000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZYPL0210_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZYPL0210;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZYPL0210 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZYPL0210_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZYPL0210_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZYPL0210_BCMsg
	 */
	public   ZYPL0210_BCMsg no(int n){
		return ( ZYPL0210_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZYPL0210_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZYPL0210_BCMsg();
	}
}