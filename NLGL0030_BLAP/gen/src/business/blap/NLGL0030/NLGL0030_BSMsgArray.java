// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161005163333000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0030_BSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0030;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLGL0030 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLGL0030_BSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLGL0030_BSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLGL0030_BSMsg
	 */
	public   NLGL0030_BSMsg no(int n){
		return ( NLGL0030_BSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLGL0030_BSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLGL0030_BSMsg();
	}
}
