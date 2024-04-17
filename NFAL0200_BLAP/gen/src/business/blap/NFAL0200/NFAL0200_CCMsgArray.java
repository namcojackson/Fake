// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161102103447000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0200_CCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0200;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFAL0200 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0200_CCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0200_CCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0200_CCMsg
	 */
	public   NFAL0200_CCMsg no(int n){
		return ( NFAL0200_CCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0200_CCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0200_CCMsg();
	}
}
