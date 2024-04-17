// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20100520113140000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0050_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0050;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZOL0050 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0050_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZOL0050_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZOL0050_ASMsg
	 */
	public   ZZOL0050_ASMsg no(int n){
		return ( ZZOL0050_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZOL0050_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZOL0050_ASMsg();
	}
}
