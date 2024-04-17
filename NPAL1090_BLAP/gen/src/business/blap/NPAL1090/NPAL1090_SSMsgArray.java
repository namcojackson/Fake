// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150317101142000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1090_SSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1090;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPAL1090 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1090_SSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1090_SSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1090_SSMsg
	 */
	public   NPAL1090_SSMsg no(int n){
		return ( NPAL1090_SSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1090_SSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1090_SSMsg();
	}
}
