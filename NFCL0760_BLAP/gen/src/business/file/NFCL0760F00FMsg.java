//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170823085244000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0760F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0760F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0760F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT_1*/
	public final EZDFStringItem              xxComnScrColValTxt_1;

    /** XX_COMN_SCR_COL_VAL_TXT_2*/
	public final EZDFStringItem              xxComnScrColValTxt_2;

    /** XX_COMN_SCR_COL_VAL_TXT_3*/
	public final EZDFStringItem              xxComnScrColValTxt_3;

    /** XX_COMN_SCR_COL_VAL_TXT_4*/
	public final EZDFStringItem              xxComnScrColValTxt_4;

    /** XX_COMN_SCR_COL_VAL_TXT_5*/
	public final EZDFStringItem              xxComnScrColValTxt_5;

    /** XX_COMN_SCR_COL_VAL_TXT_6*/
	public final EZDFStringItem              xxComnScrColValTxt_6;

    /** XX_COMN_SCR_COL_VAL_TXT_7*/
	public final EZDFStringItem              xxComnScrColValTxt_7;

    /** XX_COMN_SCR_COL_VAL_TXT_8*/
	public final EZDFStringItem              xxComnScrColValTxt_8;

    /** XX_COMN_SCR_COL_VAL_TXT_9*/
	public final EZDFStringItem              xxComnScrColValTxt_9;

    /** XX_COMN_SCR_COL_VAL_TXT_10*/
	public final EZDFStringItem              xxComnScrColValTxt_10;

    /** XX_COMN_SCR_COL_VAL_TXT_11*/
	public final EZDFStringItem              xxComnScrColValTxt_11;

    /** XX_COMN_SCR_COL_VAL_TXT_12*/
	public final EZDFStringItem              xxComnScrColValTxt_12;

    /** XX_COMN_SCR_COL_VAL_TXT_13*/
	public final EZDFStringItem              xxComnScrColValTxt_13;

    /** XX_COMN_SCR_COL_VAL_TXT_14*/
	public final EZDFStringItem              xxComnScrColValTxt_14;

    /** XX_COMN_SCR_COL_VAL_TXT_15*/
	public final EZDFStringItem              xxComnScrColValTxt_15;


	/**
	 * NFCL0760F00FMsg is constructor.
	 * The initialization when the instance of NFCL0760F00FMsg is generated.
	 */
	public NFCL0760F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0760F00FMsg is constructor.
	 * The initialization when the instance of NFCL0760F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0760F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt_1 = (EZDFStringItem)newItem("xxComnScrColValTxt_1");
		xxComnScrColValTxt_2 = (EZDFStringItem)newItem("xxComnScrColValTxt_2");
		xxComnScrColValTxt_3 = (EZDFStringItem)newItem("xxComnScrColValTxt_3");
		xxComnScrColValTxt_4 = (EZDFStringItem)newItem("xxComnScrColValTxt_4");
		xxComnScrColValTxt_5 = (EZDFStringItem)newItem("xxComnScrColValTxt_5");
		xxComnScrColValTxt_6 = (EZDFStringItem)newItem("xxComnScrColValTxt_6");
		xxComnScrColValTxt_7 = (EZDFStringItem)newItem("xxComnScrColValTxt_7");
		xxComnScrColValTxt_8 = (EZDFStringItem)newItem("xxComnScrColValTxt_8");
		xxComnScrColValTxt_9 = (EZDFStringItem)newItem("xxComnScrColValTxt_9");
		xxComnScrColValTxt_10 = (EZDFStringItem)newItem("xxComnScrColValTxt_10");
		xxComnScrColValTxt_11 = (EZDFStringItem)newItem("xxComnScrColValTxt_11");
		xxComnScrColValTxt_12 = (EZDFStringItem)newItem("xxComnScrColValTxt_12");
		xxComnScrColValTxt_13 = (EZDFStringItem)newItem("xxComnScrColValTxt_13");
		xxComnScrColValTxt_14 = (EZDFStringItem)newItem("xxComnScrColValTxt_14");
		xxComnScrColValTxt_15 = (EZDFStringItem)newItem("xxComnScrColValTxt_15");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0760F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0760F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColValTxt_1", "xxComnScrColValTxt_1", "1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_2", "xxComnScrColValTxt_2", "2", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_3", "xxComnScrColValTxt_3", "3", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_4", "xxComnScrColValTxt_4", "4", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_5", "xxComnScrColValTxt_5", "5", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_6", "xxComnScrColValTxt_6", "6", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_7", "xxComnScrColValTxt_7", "7", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_8", "xxComnScrColValTxt_8", "8", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_9", "xxComnScrColValTxt_9", "9", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_10", "xxComnScrColValTxt_10", "10", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_11", "xxComnScrColValTxt_11", "11", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_12", "xxComnScrColValTxt_12", "12", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_13", "xxComnScrColValTxt_13", "13", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_14", "xxComnScrColValTxt_14", "14", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_15", "xxComnScrColValTxt_15", "15", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_1
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_2
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_3
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_4
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_5
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_6
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_7
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_8
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_9
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_10
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_11
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_12
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_13
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_14
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_15
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
