// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_svcDtlPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC225001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_svcDtlPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC225001_svcDtlPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC225001_svcDtlPMsg
	 */
	public   NWZC225001_svcDtlPMsg no(int n){
		return ( NWZC225001_svcDtlPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC225001_svcDtlPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC225001_svcDtlPMsg();
	}
}
