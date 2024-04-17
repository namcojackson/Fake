// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160630133651000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0200_GSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0200;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFAL0200 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0200_GSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0200_GSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0200_GSMsg
	 */
	public   NFAL0200_GSMsg no(int n){
		return ( NFAL0200_GSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0200_GSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0200_GSMsg();
	}
}
