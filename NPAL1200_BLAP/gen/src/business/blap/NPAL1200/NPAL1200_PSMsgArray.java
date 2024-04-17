// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150414104324000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_PSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1200;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPAL1200 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_PSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1200_PSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1200_PSMsg
	 */
	public   NPAL1200_PSMsg no(int n){
		return ( NPAL1200_PSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1200_PSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1200_PSMsg();
	}
}
