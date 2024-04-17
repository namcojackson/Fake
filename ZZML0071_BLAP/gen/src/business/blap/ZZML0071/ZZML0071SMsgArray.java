// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20131112120639000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0071SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0071;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is ZZML0071 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZML0071SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZML0071SMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZML0071SMsg
	 */
	public   ZZML0071SMsg no(int n){
		return ( ZZML0071SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZML0071SMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZML0071SMsg();
	}
}
