// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230228115551000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_DCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1200;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1200 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_DCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1200_DCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1200_DCMsg
	 */
	public   NPAL1200_DCMsg no(int n){
		return ( NPAL1200_DCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1200_DCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1200_DCMsg();
	}
}
