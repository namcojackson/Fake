// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230522174233000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1060_QCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLCL1060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1060_QCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1060_QCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1060_QCMsg
	 */
	public   NLCL1060_QCMsg no(int n){
		return ( NLCL1060_QCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1060_QCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1060_QCMsg();
	}
}
