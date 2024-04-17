// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20171218171627000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0030SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0030;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZIL0030 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0030SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZIL0030SMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZIL0030SMsg
	 */
	public   ZZIL0030SMsg no(int n){
		return ( ZZIL0030SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZIL0030SMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZIL0030SMsg();
	}
}
