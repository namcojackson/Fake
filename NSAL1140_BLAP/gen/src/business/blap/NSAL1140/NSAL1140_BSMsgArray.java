// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160119091443000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1140_BSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1140;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL1140 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1140_BSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1140_BSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1140_BSMsg
	 */
	public   NSAL1140_BSMsg no(int n){
		return ( NSAL1140_BSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1140_BSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1140_BSMsg();
	}
}
