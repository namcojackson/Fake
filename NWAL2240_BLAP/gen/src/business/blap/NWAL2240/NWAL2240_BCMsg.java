//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20191109152745000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2240_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2240 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2240_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_B0*/
	public final EZDCStringItem              mdseCd_B0;

    /** MDSE_DESC_SHORT_TXT_B0*/
	public final EZDCStringItem              mdseDescShortTxt_B0;

    /** IN_POUND_WT_B0*/
	public final EZDCBigDecimalItem              inPoundWt_B0;

    /** IN_INCH_LG_B0*/
	public final EZDCBigDecimalItem              inInchLg_B0;

    /** IN_INCH_WDT_B0*/
	public final EZDCBigDecimalItem              inInchWdt_B0;

    /** IN_INCH_HGT_B0*/
	public final EZDCBigDecimalItem              inInchHgt_B0;

    /** XX_IN_INCH_DGNL_NUM_B0*/
	public final EZDCBigDecimalItem              xxInInchDgnlNum_B0;


	/**
	 * NWAL2240_BCMsg is constructor.
	 * The initialization when the instance of NWAL2240_BCMsg is generated.
	 */
	public NWAL2240_BCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2240_BCMsg is constructor.
	 * The initialization when the instance of NWAL2240_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2240_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_B0 = (EZDCStringItem)newItem("mdseCd_B0");
		mdseDescShortTxt_B0 = (EZDCStringItem)newItem("mdseDescShortTxt_B0");
		inPoundWt_B0 = (EZDCBigDecimalItem)newItem("inPoundWt_B0");
		inInchLg_B0 = (EZDCBigDecimalItem)newItem("inInchLg_B0");
		inInchWdt_B0 = (EZDCBigDecimalItem)newItem("inInchWdt_B0");
		inInchHgt_B0 = (EZDCBigDecimalItem)newItem("inInchHgt_B0");
		xxInInchDgnlNum_B0 = (EZDCBigDecimalItem)newItem("xxInInchDgnlNum_B0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2240_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2240_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_B0", "mdseCd_B0", "B0", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B0", "mdseDescShortTxt_B0", "B0", null, TYPE_HANKAKUEISU, "250", null},
	{"inPoundWt_B0", "inPoundWt_B0", "B0", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchLg_B0", "inInchLg_B0", "B0", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchWdt_B0", "inInchWdt_B0", "B0", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"inInchHgt_B0", "inInchHgt_B0", "B0", null, TYPE_SEISU_SYOSU, "9", "4"},
	{"xxInInchDgnlNum_B0", "xxInInchDgnlNum_B0", "B0", null, TYPE_SEISU_SYOSU, "9", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B0
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B0
        {"IN_POUND_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inPoundWt_B0
        {"IN_INCH_LG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchLg_B0
        {"IN_INCH_WDT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchWdt_B0
        {"IN_INCH_HGT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchHgt_B0
        {"XX_IN_INCH_DGNL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInInchDgnlNum_B0
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
