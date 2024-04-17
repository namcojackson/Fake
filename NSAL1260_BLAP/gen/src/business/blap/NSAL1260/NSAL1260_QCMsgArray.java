// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160225081056000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1260_QCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1260;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL1260 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL1260_QCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL1260_QCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL1260_QCMsg
	 */
	public   NSAL1260_QCMsg no(int n){
		return ( NSAL1260_QCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL1260_QCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL1260_QCMsg();
	}
}
