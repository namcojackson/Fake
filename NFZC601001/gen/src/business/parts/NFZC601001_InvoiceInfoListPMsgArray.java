// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170314104931000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC601001_InvoiceInfoListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFZC601001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFZC601001_InvoiceInfoListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFZC601001_InvoiceInfoListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFZC601001_InvoiceInfoListPMsg
	 */
	public   NFZC601001_InvoiceInfoListPMsg no(int n){
		return ( NFZC601001_InvoiceInfoListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFZC601001_InvoiceInfoListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFZC601001_InvoiceInfoListPMsg();
	}
}
