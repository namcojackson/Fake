//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160128105012000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1160_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1160 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1160_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ABUSE_OVWRT_RSN_DESC_TXT*/
	public final EZDCStringItem              abuseOvwrtRsnDescTxt;

    /** CRAT_DT*/
	public final EZDCDateItem              cratDt;

    /** XX_SCR_ITEM_61_TXT_C*/
	public final EZDCStringItem              xxScrItem61Txt_C;

    /** SVC_SPLY_EXPR_DT*/
	public final EZDCDateItem              svcSplyExprDt;


	/**
	 * NSAL1160_CCMsg is constructor.
	 * The initialization when the instance of NSAL1160_CCMsg is generated.
	 */
	public NSAL1160_CCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1160_CCMsg is constructor.
	 * The initialization when the instance of NSAL1160_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1160_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		abuseOvwrtRsnDescTxt = (EZDCStringItem)newItem("abuseOvwrtRsnDescTxt");
		cratDt = (EZDCDateItem)newItem("cratDt");
		xxScrItem61Txt_C = (EZDCStringItem)newItem("xxScrItem61Txt_C");
		svcSplyExprDt = (EZDCDateItem)newItem("svcSplyExprDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1160_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1160_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"abuseOvwrtRsnDescTxt", "abuseOvwrtRsnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"cratDt", "cratDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxScrItem61Txt_C", "xxScrItem61Txt_C", "C", null, TYPE_HANKAKUEISU, "61", null},
	{"svcSplyExprDt", "svcSplyExprDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ABUSE_OVWRT_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseOvwrtRsnDescTxt
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_C
        {"SVC_SPLY_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSplyExprDt
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

