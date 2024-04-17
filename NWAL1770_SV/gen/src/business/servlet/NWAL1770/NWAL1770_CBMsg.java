//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230808112258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_LINE_NUM_C*/
	public final EZDBStringItem              xxLineNum_C;

    /** MDSE_CD_C*/
	public final EZDBStringItem              mdseCd_C;

    /** MDSE_DESC_SHORT_TXT_C*/
	public final EZDBStringItem              mdseDescShortTxt_C;

    /** SPLY_QUOTE_STS_DESC_TXT_C*/
	public final EZDBStringItem              splyQuoteStsDescTxt_C;

    /** UNIT_NET_WT_C*/
	public final EZDBBigDecimalItem              unitNetWt_C;

    /** ORD_QTY_C*/
	public final EZDBBigDecimalItem              ordQty_C;

    /** XX_TOT_UNIT_NET_WT_C*/
	public final EZDBBigDecimalItem              xxTotUnitNetWt_C;

    /** COA_MDSE_TP_DESC_TXT_C*/
	public final EZDBStringItem              coaMdseTpDescTxt_C;

    /** COA_PROD_DESC_TXT_C*/
	public final EZDBStringItem              coaProdDescTxt_C;

    /** ZEROTH_PROD_CTRL_NM_C*/
	public final EZDBStringItem              zerothProdCtrlNm_C;

    /** FIRST_PROD_CTRL_NM_C*/
	public final EZDBStringItem              firstProdCtrlNm_C;

    /** SCD_PROD_CTRL_NM_C*/
	public final EZDBStringItem              scdProdCtrlNm_C;

    /** THIRD_PROD_CTRL_NM_C*/
	public final EZDBStringItem              thirdProdCtrlNm_C;

    /** FRTH_PROD_CTRL_NM_C*/
	public final EZDBStringItem              frthProdCtrlNm_C;


	/**
	 * NWAL1770_CBMsg is constructor.
	 * The initialization when the instance of NWAL1770_CBMsg is generated.
	 */
	public NWAL1770_CBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1770_CBMsg is constructor.
	 * The initialization when the instance of NWAL1770_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1770_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxLineNum_C = (EZDBStringItem)newItem("xxLineNum_C");
		mdseCd_C = (EZDBStringItem)newItem("mdseCd_C");
		mdseDescShortTxt_C = (EZDBStringItem)newItem("mdseDescShortTxt_C");
		splyQuoteStsDescTxt_C = (EZDBStringItem)newItem("splyQuoteStsDescTxt_C");
		unitNetWt_C = (EZDBBigDecimalItem)newItem("unitNetWt_C");
		ordQty_C = (EZDBBigDecimalItem)newItem("ordQty_C");
		xxTotUnitNetWt_C = (EZDBBigDecimalItem)newItem("xxTotUnitNetWt_C");
		coaMdseTpDescTxt_C = (EZDBStringItem)newItem("coaMdseTpDescTxt_C");
		coaProdDescTxt_C = (EZDBStringItem)newItem("coaProdDescTxt_C");
		zerothProdCtrlNm_C = (EZDBStringItem)newItem("zerothProdCtrlNm_C");
		firstProdCtrlNm_C = (EZDBStringItem)newItem("firstProdCtrlNm_C");
		scdProdCtrlNm_C = (EZDBStringItem)newItem("scdProdCtrlNm_C");
		thirdProdCtrlNm_C = (EZDBStringItem)newItem("thirdProdCtrlNm_C");
		frthProdCtrlNm_C = (EZDBStringItem)newItem("frthProdCtrlNm_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1770_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1770_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxLineNum_C", "xxLineNum_C", "C", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_C", "mdseCd_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_C", "mdseDescShortTxt_C", "C", null, TYPE_HANKAKUEISU, "250", null},
	{"splyQuoteStsDescTxt_C", "splyQuoteStsDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"unitNetWt_C", "unitNetWt_C", "C", null, TYPE_SEISU_SYOSU, "17", "6"},
	{"ordQty_C", "ordQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxTotUnitNetWt_C", "xxTotUnitNetWt_C", "C", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"coaMdseTpDescTxt_C", "coaMdseTpDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"coaProdDescTxt_C", "coaProdDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"zerothProdCtrlNm_C", "zerothProdCtrlNm_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"firstProdCtrlNm_C", "firstProdCtrlNm_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"scdProdCtrlNm_C", "scdProdCtrlNm_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"thirdProdCtrlNm_C", "thirdProdCtrlNm_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"frthProdCtrlNm_C", "frthProdCtrlNm_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_C
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_C
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_C
        {"SPLY_QUOTE_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteStsDescTxt_C
        {"UNIT_NET_WT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitNetWt_C
        {"ORD_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_C
        {"XX_TOT_UNIT_NET_WT",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotUnitNetWt_C
        {"COA_MDSE_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpDescTxt_C
        {"COA_PROD_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdDescTxt_C
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_C
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_C
        {"SCD_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_C
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_C
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_C
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

