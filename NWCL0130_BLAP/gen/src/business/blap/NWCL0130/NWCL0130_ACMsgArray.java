// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170217125930000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0130_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0130;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWCL0130 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWCL0130_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWCL0130_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWCL0130_ACMsg
	 */
	public   NWCL0130_ACMsg no(int n){
		return ( NWCL0130_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWCL0130_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWCL0130_ACMsg();
	}
}