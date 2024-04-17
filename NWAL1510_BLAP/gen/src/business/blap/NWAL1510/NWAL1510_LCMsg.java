//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200210145613000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1510_LCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1510 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1510_LCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_CUST_REF_TP_CD_L*/
	public final EZDCStringItem              ctacCustRefTpCd_L;


	/**
	 * NWAL1510_LCMsg is constructor.
	 * The initialization when the instance of NWAL1510_LCMsg is generated.
	 */
	public NWAL1510_LCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1510_LCMsg is constructor.
	 * The initialization when the instance of NWAL1510_LCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1510_LCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacCustRefTpCd_L = (EZDCStringItem)newItem("ctacCustRefTpCd_L");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1510_LCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1510_LCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacCustRefTpCd_L", "ctacCustRefTpCd_L", "L", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_CUST_REF_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacCustRefTpCd_L
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}

