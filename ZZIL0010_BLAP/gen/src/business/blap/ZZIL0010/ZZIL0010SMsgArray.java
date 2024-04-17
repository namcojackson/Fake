// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090820204705000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0010SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0010;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZIL0010 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0010SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZIL0010SMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZIL0010SMsg
	 */
	public   ZZIL0010SMsg no(int n){
		return ( ZZIL0010SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZIL0010SMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZIL0010SMsg();
	}
}
