// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180821154633000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7060_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL7060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7060_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7060_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7060_BCMsg
	 */
	public   NMAL7060_BCMsg no(int n){
		return ( NMAL7060_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7060_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7060_BCMsg();
	}
}