// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160719193042000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0120_DCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0120;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFDL0120 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0120_DCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0120_DCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0120_DCMsg
	 */
	public   NFDL0120_DCMsg no(int n){
		return ( NFDL0120_DCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0120_DCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0120_DCMsg();
	}
}
