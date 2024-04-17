// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230522174235000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1060SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1060;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLCL1060 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1060SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1060SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1060SMsg
	 */
	public   NLCL1060SMsg no(int n){
		return ( NLCL1060SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1060SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1060SMsg();
	}
}
