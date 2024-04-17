// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151009115344000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2090SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2090;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL2090 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2090SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2090SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2090SMsg
	 */
	public   NWAL2090SMsg no(int n){
		return ( NWAL2090SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2090SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2090SMsg();
	}
}