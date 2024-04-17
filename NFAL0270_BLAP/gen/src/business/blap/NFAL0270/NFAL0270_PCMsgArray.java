// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230530103730000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0270_PCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0270;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFAL0270 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFAL0270_PCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFAL0270_PCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFAL0270_PCMsg
	 */
	public   NFAL0270_PCMsg no(int n){
		return ( NFAL0270_PCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFAL0270_PCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFAL0270_PCMsg();
	}
}
