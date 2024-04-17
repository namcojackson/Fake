// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20091026153721000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0020CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZBL0020;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZBL0020 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0020CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZBL0020CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZBL0020CMsg
	 */
	public   ZZBL0020CMsg no(int n){
		return ( ZZBL0020CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZBL0020CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZBL0020CMsg();
	}
}