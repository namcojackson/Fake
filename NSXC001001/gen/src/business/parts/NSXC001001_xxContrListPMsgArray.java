// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190117112345000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSXC001001_xxContrListPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NSXC001001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSXC001001_xxContrListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSXC001001_xxContrListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSXC001001_xxContrListPMsg
	 */
	public   NSXC001001_xxContrListPMsg no(int n){
		return ( NSXC001001_xxContrListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSXC001001_xxContrListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSXC001001_xxContrListPMsg();
	}
}
