// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130814173827000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0010CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZML0010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0010CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0010CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0010CMsg
	 */
	public   ZZML0010CMsg no(int n){
		return ( ZZML0010CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0010CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0010CMsg();
	}
}