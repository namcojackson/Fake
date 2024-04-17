// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090827114343000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZXL0030_DSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZXL0030;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZXL0030 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZXL0030_DSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZXL0030_DSMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZXL0030_DSMsg
	 */
	public   ZZXL0030_DSMsg no(int n){
		return ( ZZXL0030_DSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZXL0030_DSMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZXL0030_DSMsg();
	}
}
