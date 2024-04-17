//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160914094206000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0030_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWWL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0030_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_HDR_ID_B0*/
	public final EZDSStringItem              ntfyHdrId_B0;

    /** NTFY_RUN_JOB_ID_B0*/
	public final EZDSStringItem              ntfyRunJobId_B0;

    /** NTFY_ACT_ID_B0*/
	public final EZDSStringItem              ntfyActId_B0;

    /** NTFY_ACT_NM_B0*/
	public final EZDSStringItem              ntfyActNm_B0;

    /** NTFY_ACT_DTL_HIST_ID_B0*/
	public final EZDSStringItem              ntfyActDtlHistId_B0;


	/**
	 * NWWL0030_BSMsg is constructor.
	 * The initialization when the instance of NWWL0030_BSMsg is generated.
	 */
	public NWWL0030_BSMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0030_BSMsg is constructor.
	 * The initialization when the instance of NWWL0030_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0030_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyHdrId_B0 = (EZDSStringItem)newItem("ntfyHdrId_B0");
		ntfyRunJobId_B0 = (EZDSStringItem)newItem("ntfyRunJobId_B0");
		ntfyActId_B0 = (EZDSStringItem)newItem("ntfyActId_B0");
		ntfyActNm_B0 = (EZDSStringItem)newItem("ntfyActNm_B0");
		ntfyActDtlHistId_B0 = (EZDSStringItem)newItem("ntfyActDtlHistId_B0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0030_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0030_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyHdrId_B0", "ntfyHdrId_B0", "B0", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyRunJobId_B0", "ntfyRunJobId_B0", "B0", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyActId_B0", "ntfyActId_B0", "B0", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyActNm_B0", "ntfyActNm_B0", "B0", null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyActDtlHistId_B0", "ntfyActDtlHistId_B0", "B0", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_HDR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrId_B0
        {"NTFY_RUN_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunJobId_B0
        {"NTFY_ACT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActId_B0
        {"NTFY_ACT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActNm_B0
        {"NTFY_ACT_DTL_HIST_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlHistId_B0
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

