// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160222213223000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC271003_territoryRuleListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMZC271003 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMZC271003_territoryRuleListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMZC271003_territoryRuleListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMZC271003_territoryRuleListPMsg
	 */
	public   NMZC271003_territoryRuleListPMsg no(int n){
		return ( NMZC271003_territoryRuleListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMZC271003_territoryRuleListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMZC271003_territoryRuleListPMsg();
	}
}
