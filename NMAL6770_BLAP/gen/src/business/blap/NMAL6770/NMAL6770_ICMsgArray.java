// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180228111329000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6770_ICMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6770;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL6770 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6770_ICMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6770_ICMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6770_ICMsg
	 */
	public   NMAL6770_ICMsg no(int n){
		return ( NMAL6770_ICMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6770_ICMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6770_ICMsg();
	}
}
