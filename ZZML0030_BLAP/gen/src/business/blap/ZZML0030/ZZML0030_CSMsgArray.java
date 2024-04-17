// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200226172518000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0030_CSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0030;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZML0030 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0030_CSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0030_CSMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0030_CSMsg
	 */
	public   ZZML0030_CSMsg no(int n){
		return ( ZZML0030_CSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0030_CSMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0030_CSMsg();
	}
}
