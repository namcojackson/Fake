// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160407214450000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0090SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0090;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLBL0090 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL0090SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL0090SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL0090SMsg
	 */
	public   NLBL0090SMsg no(int n){
		return ( NLBL0090SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL0090SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL0090SMsg();
	}
}