//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151201161009000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6870_RCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6870;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6870 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6870_RCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COL_VAL_TXT_0*/
	public final EZDCStringItem              xxComnScrColValTxt_0;

    /** XX_COMN_SCR_QUERY_COL_NM_0*/
	public final EZDCStringItem              xxComnScrQueryColNm_0;

    /** XX_COMN_SCR_COL_VAL_TXT_1*/
	public final EZDCStringItem              xxComnScrColValTxt_1;

    /** XX_COMN_SCR_QUERY_COL_NM_1*/
	public final EZDCStringItem              xxComnScrQueryColNm_1;

    /** XX_COMN_SCR_COL_VAL_TXT_2*/
	public final EZDCStringItem              xxComnScrColValTxt_2;

    /** XX_COMN_SCR_QUERY_COL_NM_2*/
	public final EZDCStringItem              xxComnScrQueryColNm_2;

    /** XX_COMN_SCR_COL_VAL_TXT_3*/
	public final EZDCStringItem              xxComnScrColValTxt_3;

    /** XX_COMN_SCR_QUERY_COL_NM_3*/
	public final EZDCStringItem              xxComnScrQueryColNm_3;

    /** XX_COMN_SCR_COL_VAL_TXT_4*/
	public final EZDCStringItem              xxComnScrColValTxt_4;

    /** XX_COMN_SCR_QUERY_COL_NM_4*/
	public final EZDCStringItem              xxComnScrQueryColNm_4;

    /** XX_COMN_SCR_COL_VAL_TXT_5*/
	public final EZDCStringItem              xxComnScrColValTxt_5;

    /** XX_COMN_SCR_QUERY_COL_NM_5*/
	public final EZDCStringItem              xxComnScrQueryColNm_5;

    /** XX_COMN_SCR_COL_VAL_TXT_6*/
	public final EZDCStringItem              xxComnScrColValTxt_6;

    /** XX_COMN_SCR_QUERY_COL_NM_6*/
	public final EZDCStringItem              xxComnScrQueryColNm_6;

    /** XX_COMN_SCR_COL_VAL_TXT_7*/
	public final EZDCStringItem              xxComnScrColValTxt_7;

    /** XX_COMN_SCR_QUERY_COL_NM_7*/
	public final EZDCStringItem              xxComnScrQueryColNm_7;

    /** XX_COMN_SCR_COL_VAL_TXT_8*/
	public final EZDCStringItem              xxComnScrColValTxt_8;

    /** XX_COMN_SCR_QUERY_COL_NM_8*/
	public final EZDCStringItem              xxComnScrQueryColNm_8;

    /** XX_COMN_SCR_COL_VAL_TXT_9*/
	public final EZDCStringItem              xxComnScrColValTxt_9;

    /** XX_COMN_SCR_QUERY_COL_NM_9*/
	public final EZDCStringItem              xxComnScrQueryColNm_9;


	/**
	 * NMAL6870_RCMsg is constructor.
	 * The initialization when the instance of NMAL6870_RCMsg is generated.
	 */
	public NMAL6870_RCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6870_RCMsg is constructor.
	 * The initialization when the instance of NMAL6870_RCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6870_RCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrColValTxt_0 = (EZDCStringItem)newItem("xxComnScrColValTxt_0");
		xxComnScrQueryColNm_0 = (EZDCStringItem)newItem("xxComnScrQueryColNm_0");
		xxComnScrColValTxt_1 = (EZDCStringItem)newItem("xxComnScrColValTxt_1");
		xxComnScrQueryColNm_1 = (EZDCStringItem)newItem("xxComnScrQueryColNm_1");
		xxComnScrColValTxt_2 = (EZDCStringItem)newItem("xxComnScrColValTxt_2");
		xxComnScrQueryColNm_2 = (EZDCStringItem)newItem("xxComnScrQueryColNm_2");
		xxComnScrColValTxt_3 = (EZDCStringItem)newItem("xxComnScrColValTxt_3");
		xxComnScrQueryColNm_3 = (EZDCStringItem)newItem("xxComnScrQueryColNm_3");
		xxComnScrColValTxt_4 = (EZDCStringItem)newItem("xxComnScrColValTxt_4");
		xxComnScrQueryColNm_4 = (EZDCStringItem)newItem("xxComnScrQueryColNm_4");
		xxComnScrColValTxt_5 = (EZDCStringItem)newItem("xxComnScrColValTxt_5");
		xxComnScrQueryColNm_5 = (EZDCStringItem)newItem("xxComnScrQueryColNm_5");
		xxComnScrColValTxt_6 = (EZDCStringItem)newItem("xxComnScrColValTxt_6");
		xxComnScrQueryColNm_6 = (EZDCStringItem)newItem("xxComnScrQueryColNm_6");
		xxComnScrColValTxt_7 = (EZDCStringItem)newItem("xxComnScrColValTxt_7");
		xxComnScrQueryColNm_7 = (EZDCStringItem)newItem("xxComnScrQueryColNm_7");
		xxComnScrColValTxt_8 = (EZDCStringItem)newItem("xxComnScrColValTxt_8");
		xxComnScrQueryColNm_8 = (EZDCStringItem)newItem("xxComnScrQueryColNm_8");
		xxComnScrColValTxt_9 = (EZDCStringItem)newItem("xxComnScrColValTxt_9");
		xxComnScrQueryColNm_9 = (EZDCStringItem)newItem("xxComnScrQueryColNm_9");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6870_RCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6870_RCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrColValTxt_0", "xxComnScrColValTxt_0", "0", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_0", "xxComnScrQueryColNm_0", "0", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_1", "xxComnScrColValTxt_1", "1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_1", "xxComnScrQueryColNm_1", "1", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_2", "xxComnScrColValTxt_2", "2", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_2", "xxComnScrQueryColNm_2", "2", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_3", "xxComnScrColValTxt_3", "3", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_3", "xxComnScrQueryColNm_3", "3", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_4", "xxComnScrColValTxt_4", "4", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_4", "xxComnScrQueryColNm_4", "4", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_5", "xxComnScrColValTxt_5", "5", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_5", "xxComnScrQueryColNm_5", "5", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_6", "xxComnScrColValTxt_6", "6", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_6", "xxComnScrQueryColNm_6", "6", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_7", "xxComnScrColValTxt_7", "7", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_7", "xxComnScrQueryColNm_7", "7", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_8", "xxComnScrColValTxt_8", "8", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_8", "xxComnScrQueryColNm_8", "8", null, TYPE_HANKAKUEISU, "27", null},
	{"xxComnScrColValTxt_9", "xxComnScrColValTxt_9", "9", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrQueryColNm_9", "xxComnScrQueryColNm_9", "9", null, TYPE_HANKAKUEISU, "27", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_0
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_0
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_1
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_1
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_2
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_2
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_3
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_3
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_4
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_4
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_5
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_5
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_6
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_6
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_7
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_7
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_8
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_8
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_9
        {"XX_COMN_SCR_QUERY_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryColNm_9
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
