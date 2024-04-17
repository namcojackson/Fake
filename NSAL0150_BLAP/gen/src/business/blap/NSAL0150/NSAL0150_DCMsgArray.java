// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220324142845000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0150_DCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0150;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0150 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0150_DCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0150_DCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0150_DCMsg
	 */
	public   NSAL0150_DCMsg no(int n){
		return ( NSAL0150_DCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0150_DCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0150_DCMsg();
	}
}
