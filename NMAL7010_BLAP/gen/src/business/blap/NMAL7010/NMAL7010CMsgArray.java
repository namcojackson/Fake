// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL7010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7010CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7010CMsg
	 */
	public   NMAL7010CMsg no(int n){
		return ( NMAL7010CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7010CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7010CMsg();
	}
}
