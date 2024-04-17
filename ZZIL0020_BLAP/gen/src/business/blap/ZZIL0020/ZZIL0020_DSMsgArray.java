// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20100204110010000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0020_DSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0020;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZIL0020 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0020_DSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZIL0020_DSMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZIL0020_DSMsg
	 */
	public   ZZIL0020_DSMsg no(int n){
		return ( ZZIL0020_DSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZIL0020_DSMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZIL0020_DSMsg();
	}
}
