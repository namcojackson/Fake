// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230511170929000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640_TSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0640;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLCL0640 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640_TSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0640_TSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0640_TSMsg
	 */
	public   NLCL0640_TSMsg no(int n){
		return ( NLCL0640_TSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0640_TSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0640_TSMsg();
	}
}
