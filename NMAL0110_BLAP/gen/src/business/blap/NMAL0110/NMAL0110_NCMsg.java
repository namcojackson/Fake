//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231219170257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_NCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_NCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_N1*/
	public final EZDCStringItem              xxChkBox_N1;

    /** MDSE_TERM_COND_OPT_PK_N1*/
	public final EZDCBigDecimalItem              mdseTermCondOptPk_N1;

    /** TERM_COND_OPT_TP_CD_N1*/
	public final EZDCStringItem              termCondOptTpCd_N1;

    /** TERM_COND_OPT_VAL_TXT_N1*/
	public final EZDCStringItem              termCondOptValTxt_N1;

    /** _EZUpdateDateTime_N1*/
	public final EZDCStringItem              ezUpTime_N1;

    /** _EZUpTimeZone_N1*/
	public final EZDCStringItem              ezUpTimeZone_N1;


	/**
	 * NMAL0110_NCMsg is constructor.
	 * The initialization when the instance of NMAL0110_NCMsg is generated.
	 */
	public NMAL0110_NCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0110_NCMsg is constructor.
	 * The initialization when the instance of NMAL0110_NCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0110_NCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_N1 = (EZDCStringItem)newItem("xxChkBox_N1");
		mdseTermCondOptPk_N1 = (EZDCBigDecimalItem)newItem("mdseTermCondOptPk_N1");
		termCondOptTpCd_N1 = (EZDCStringItem)newItem("termCondOptTpCd_N1");
		termCondOptValTxt_N1 = (EZDCStringItem)newItem("termCondOptValTxt_N1");
		ezUpTime_N1 = (EZDCStringItem)newItem("ezUpTime_N1");
		ezUpTimeZone_N1 = (EZDCStringItem)newItem("ezUpTimeZone_N1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0110_NCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0110_NCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_N1", "xxChkBox_N1", "N1", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseTermCondOptPk_N1", "mdseTermCondOptPk_N1", "N1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"termCondOptTpCd_N1", "termCondOptTpCd_N1", "N1", null, TYPE_HANKAKUEISU, "2", null},
	{"termCondOptValTxt_N1", "termCondOptValTxt_N1", "N1", null, TYPE_HANKAKUEISU, "10", null},
	{"ezUpTime_N1", "ezUpTime_N1", "N1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_N1", "ezUpTimeZone_N1", "N1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_N1
        {"MDSE_TERM_COND_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseTermCondOptPk_N1
        {"TERM_COND_OPT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termCondOptTpCd_N1
        {"TERM_COND_OPT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termCondOptValTxt_N1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_N1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_N1
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
