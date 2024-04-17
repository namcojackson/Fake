// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150708192740000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8820_HCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8820;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NYEL8820 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8820_HCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8820_HCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8820_HCMsg
	 */
	public   NYEL8820_HCMsg no(int n){
		return ( NYEL8820_HCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8820_HCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8820_HCMsg();
	}
}
