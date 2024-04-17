// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118110520000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_ZCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1330;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1330 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_ZCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1330_ZCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1330_ZCMsg
	 */
	public   NSAL1330_ZCMsg no(int n){
		return ( NSAL1330_ZCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1330_ZCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1330_ZCMsg();
	}
}
