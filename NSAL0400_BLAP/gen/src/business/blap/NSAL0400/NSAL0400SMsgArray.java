// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220912153615000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0400SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0400;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NSAL0400 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0400SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0400SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0400SMsg
	 */
	public   NSAL0400SMsg no(int n){
		return ( NSAL0400SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0400SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0400SMsg();
	}
}