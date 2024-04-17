// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160125093954000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_VSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1770;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL1770 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_VSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1770_VSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1770_VSMsg
	 */
	public   NWAL1770_VSMsg no(int n){
		return ( NWAL1770_VSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1770_VSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1770_VSMsg();
	}
}