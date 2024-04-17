//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160324120119000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0060_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0060_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AJE_PTRN_ACTL_CD_C*/
	public final EZDCStringItem              ajePtrnActlCd_C;

    /** AJE_PTRN_ACTL_NM_C*/
	public final EZDCStringItem              ajePtrnActlNm_C;


	/**
	 * NFAL0060_CCMsg is constructor.
	 * The initialization when the instance of NFAL0060_CCMsg is generated.
	 */
	public NFAL0060_CCMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0060_CCMsg is constructor.
	 * The initialization when the instance of NFAL0060_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0060_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ajePtrnActlCd_C = (EZDCStringItem)newItem("ajePtrnActlCd_C");
		ajePtrnActlNm_C = (EZDCStringItem)newItem("ajePtrnActlNm_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0060_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0060_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ajePtrnActlCd_C", "ajePtrnActlCd_C", "C", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm_C", "ajePtrnActlNm_C", "C", null, TYPE_HANKAKUEISU, "70", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_C
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_C
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
