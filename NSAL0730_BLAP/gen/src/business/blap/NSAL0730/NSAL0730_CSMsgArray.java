// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190118170853000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730_CSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0730;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL0730 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730_CSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0730_CSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0730_CSMsg
	 */
	public   NSAL0730_CSMsg no(int n){
		return ( NSAL0730_CSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0730_CSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0730_CSMsg();
	}
}