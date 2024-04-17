// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090902153846000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC135001_ParameterListPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NWZC135001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC135001_ParameterListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC135001_ParameterListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC135001_ParameterListPMsg
	 */
	public   NWZC135001_ParameterListPMsg no(int n){
		return ( NWZC135001_ParameterListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC135001_ParameterListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC135001_ParameterListPMsg();
	}
}
