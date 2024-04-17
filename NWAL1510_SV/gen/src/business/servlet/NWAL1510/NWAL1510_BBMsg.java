//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230728112937000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1510_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1510 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1510_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_B0*/
	public final EZDBStringItem              mdseCd_B0;

    /** MDSE_DESC_SHORT_TXT_B0*/
	public final EZDBStringItem              mdseDescShortTxt_B0;

    /** IN_POUND_WT_B0*/
	public final EZDBBigDecimalItem              inPoundWt_B0;

    /** IN_INCH_LG_B0*/
	public final EZDBBigDecimalItem              inInchLg_B0;

    /** IN_INCH_WDT_B0*/
	public final EZDBBigDecimalItem              inInchWdt_B0;

    /** IN_INCH_HGT_B0*/
	public final EZDBBigDecimalItem              inInchHgt_B0;

    /** XX_IN_INCH_DGNL_NUM_B0*/
	public final EZDBBigDecimalItem              xxInInchDgnlNum_B0;


	/**
	 * NWAL1510_BBMsg is constructor.
	 * The initialization when the instance of NWAL1510_BBMsg is generated.
	 */
	public NWAL1510_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1510_BBMsg is constructor.
	 * The initialization when the instance of NWAL1510_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1510_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_B0 = (EZDBStringItem)newItem("mdseCd_B0");
		mdseDescShortTxt_B0 = (EZDBStringItem)newItem("mdseDescShortTxt_B0");
		inPoundWt_B0 = (EZDBBigDecimalItem)newItem("inPoundWt_B0");
		inInchLg_B0 = (EZDBBigDecimalItem)newItem("inInchLg_B0");
		inInchWdt_B0 = (EZDBBigDecimalItem)newItem("inInchWdt_B0");
		inInchHgt_B0 = (EZDBBigDecimalItem)newItem("inInchHgt_B0");
		xxInInchDgnlNum_B0 = (EZDBBigDecimalItem)newItem("xxInInchDgnlNum_B0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1510_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1510_BBMsgArray();
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

        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B0
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B0
        {"IN_POUND_WT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inPoundWt_B0
        {"IN_INCH_LG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchLg_B0
        {"IN_INCH_WDT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchWdt_B0
        {"IN_INCH_HGT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inInchHgt_B0
        {"XX_IN_INCH_DGNL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInInchDgnlNum_B0
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

