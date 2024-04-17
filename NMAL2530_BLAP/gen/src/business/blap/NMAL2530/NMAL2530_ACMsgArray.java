// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170223093655000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2530_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2530;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL2530 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2530_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2530_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2530_ACMsg
	 */
	public   NMAL2530_ACMsg no(int n){
		return ( NMAL2530_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2530_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2530_ACMsg();
	}
}
