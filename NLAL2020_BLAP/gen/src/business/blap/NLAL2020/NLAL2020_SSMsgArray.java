// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230831165136000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2020_SSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2020;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLAL2020 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLAL2020_SSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLAL2020_SSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLAL2020_SSMsg
	 */
	public   NLAL2020_SSMsg no(int n){
		return ( NLAL2020_SSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLAL2020_SSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLAL2020_SSMsg();
	}
}