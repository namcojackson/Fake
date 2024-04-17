//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161025022717000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0300_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0300_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** INVTY_ORD_NUM_A*/
	public final EZDBStringItem              invtyOrdNum_A;

    /** INVTY_ORD_LINE_NUM_A*/
	public final EZDBStringItem              invtyOrdLineNum_A;

    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** STK_STS_CD_A*/
	public final EZDBStringItem              stkStsCd_A;

    /** RTL_SWH_CD_A*/
	public final EZDBStringItem              rtlSwhCd_A;

    /** ORD_QTY_A*/
	public final EZDBBigDecimalItem              ordQty_A;

    /** INVTY_AVAL_QTY_A*/
	public final EZDBBigDecimalItem              invtyAvalQty_A;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;

    /** RMV_CONFIG_FLG_A*/
	public final EZDBStringItem              rmvConfigFlg_A;

    /** INVTY_LOC_CD_A*/
	public final EZDBStringItem              invtyLocCd_A;


	/**
	 * NLCL0300_ABMsg is constructor.
	 * The initialization when the instance of NLCL0300_ABMsg is generated.
	 */
	public NLCL0300_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0300_ABMsg is constructor.
	 * The initialization when the instance of NLCL0300_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0300_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		invtyOrdNum_A = (EZDBStringItem)newItem("invtyOrdNum_A");
		invtyOrdLineNum_A = (EZDBStringItem)newItem("invtyOrdLineNum_A");
		svcConfigMstrPk_A = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		stkStsCd_A = (EZDBStringItem)newItem("stkStsCd_A");
		rtlSwhCd_A = (EZDBStringItem)newItem("rtlSwhCd_A");
		ordQty_A = (EZDBBigDecimalItem)newItem("ordQty_A");
		invtyAvalQty_A = (EZDBBigDecimalItem)newItem("invtyAvalQty_A");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
		rmvConfigFlg_A = (EZDBStringItem)newItem("rmvConfigFlg_A");
		invtyLocCd_A = (EZDBStringItem)newItem("invtyLocCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0300_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0300_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"invtyOrdNum_A", "invtyOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyOrdLineNum_A", "invtyOrdLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"stkStsCd_A", "stkStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ordQty_A", "ordQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAvalQty_A", "invtyAvalQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rmvConfigFlg_A", "rmvConfigFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyLocCd_A", "invtyLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"INVTY_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum_A
        {"INVTY_ORD_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineNum_A
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"MDSE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"ORD_QTY", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A
        {"INVTY_AVAL_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_A
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"RMV_CONFIG_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvConfigFlg_A
        {"INVTY_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A
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
