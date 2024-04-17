// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090527181940000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZYPL0220_CCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZYPL0220;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZYPL0220 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZYPL0220_CCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZYPL0220_CCMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZYPL0220_CCMsg
	 */
	public   ZYPL0220_CCMsg no(int n){
		return ( ZYPL0220_CCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZYPL0220_CCMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZYPL0220_CCMsg();
	}
}
