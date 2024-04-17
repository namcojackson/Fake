//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221020072621000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1520_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1520 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1520_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MRP_INFO_PK_A1*/
	public final EZDSBigDecimalItem              mrpInfoPk_A1;

    /** MRP_PLN_NM_A1*/
	public final EZDSStringItem              mrpPlnNm_A1;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** RTL_WH_CATG_DESC_TXT_A1*/
	public final EZDSStringItem              rtlWhCatgDescTxt_A1;

    /** RTL_WH_CD_A1*/
	public final EZDSStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDSStringItem              rtlWhNm_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDSStringItem              rtlSwhCd_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDSStringItem              rtlSwhNm_A1;

    /** WH_MGR_PSN_CD_A1*/
	public final EZDSStringItem              whMgrPsnCd_A1;

    /** FULL_PSN_NM_A1*/
	public final EZDSStringItem              fullPsnNm_A1;

    /** CALC_ORD_PROC_CD_A1*/
	public final EZDSStringItem              calcOrdProcCd_A1;

    /** ROP_QTY_A1*/
	public final EZDSBigDecimalItem              ropQty_A1;

    /** MAX_INVTY_QTY_A1*/
	public final EZDSBigDecimalItem              maxInvtyQty_A1;

    /** MRP_ENBL_FLG_A1*/
	public final EZDSStringItem              mrpEnblFlg_A1;

    /** PROCR_TP_DESC_TXT_A1*/
	public final EZDSStringItem              procrTpDescTxt_A1;

    /** SRC_RTL_WH_CD_A1*/
	public final EZDSStringItem              srcRtlWhCd_A1;

    /** RTL_WH_NM_A2*/
	public final EZDSStringItem              rtlWhNm_A2;

    /** SRC_RTL_SWH_CD_A1*/
	public final EZDSStringItem              srcRtlSwhCd_A1;

    /** RTL_SWH_NM_A2*/
	public final EZDSStringItem              rtlSwhNm_A2;


	/**
	 * NPAL1520_ASMsg is constructor.
	 * The initialization when the instance of NPAL1520_ASMsg is generated.
	 */
	public NPAL1520_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1520_ASMsg is constructor.
	 * The initialization when the instance of NPAL1520_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1520_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mrpInfoPk_A1 = (EZDSBigDecimalItem)newItem("mrpInfoPk_A1");
		mrpPlnNm_A1 = (EZDSStringItem)newItem("mrpPlnNm_A1");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		rtlWhCatgDescTxt_A1 = (EZDSStringItem)newItem("rtlWhCatgDescTxt_A1");
		rtlWhCd_A1 = (EZDSStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDSStringItem)newItem("rtlWhNm_A1");
		rtlSwhCd_A1 = (EZDSStringItem)newItem("rtlSwhCd_A1");
		rtlSwhNm_A1 = (EZDSStringItem)newItem("rtlSwhNm_A1");
		whMgrPsnCd_A1 = (EZDSStringItem)newItem("whMgrPsnCd_A1");
		fullPsnNm_A1 = (EZDSStringItem)newItem("fullPsnNm_A1");
		calcOrdProcCd_A1 = (EZDSStringItem)newItem("calcOrdProcCd_A1");
		ropQty_A1 = (EZDSBigDecimalItem)newItem("ropQty_A1");
		maxInvtyQty_A1 = (EZDSBigDecimalItem)newItem("maxInvtyQty_A1");
		mrpEnblFlg_A1 = (EZDSStringItem)newItem("mrpEnblFlg_A1");
		procrTpDescTxt_A1 = (EZDSStringItem)newItem("procrTpDescTxt_A1");
		srcRtlWhCd_A1 = (EZDSStringItem)newItem("srcRtlWhCd_A1");
		rtlWhNm_A2 = (EZDSStringItem)newItem("rtlWhNm_A2");
		srcRtlSwhCd_A1 = (EZDSStringItem)newItem("srcRtlSwhCd_A1");
		rtlSwhNm_A2 = (EZDSStringItem)newItem("rtlSwhNm_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1520_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1520_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mrpInfoPk_A1", "mrpInfoPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mrpPlnNm_A1", "mrpPlnNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCatgDescTxt_A1", "rtlWhCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"whMgrPsnCd_A1", "whMgrPsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"fullPsnNm_A1", "fullPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"calcOrdProcCd_A1", "calcOrdProcCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"ropQty_A1", "ropQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"maxInvtyQty_A1", "maxInvtyQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mrpEnblFlg_A1", "mrpEnblFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"procrTpDescTxt_A1", "procrTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"srcRtlWhCd_A1", "srcRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A2", "rtlWhNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd_A1", "srcRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A2", "rtlSwhNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MRP_INFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoPk_A1
        {"MRP_PLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"WH_MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whMgrPsnCd_A1
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_A1
        {"CALC_ORD_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd_A1
        {"ROP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ropQty_A1
        {"MAX_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxInvtyQty_A1
        {"MRP_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg_A1
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_A1
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A2
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A2
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

