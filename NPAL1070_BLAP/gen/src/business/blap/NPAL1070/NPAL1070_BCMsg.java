//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230419132643000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1070_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1070_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** GLBL_CMPY_CD_B1*/
	public final EZDCStringItem              glblCmpyCd_B1;

    /** MRP_INFO_PK_B1*/
	public final EZDCBigDecimalItem              mrpInfoPk_B1;

    /** XX_RQST_TS_B1*/
	public final EZDCStringItem              xxRqstTs_B1;

    /** XX_RQST_TZ_B1*/
	public final EZDCStringItem              xxRqstTz_B1;

    /** MRP_PLN_NM_B1*/
	public final EZDCStringItem              mrpPlnNm_B1;

    /** RTL_WH_CATG_CD_B1*/
	public final EZDCStringItem              rtlWhCatgCd_B1;

    /** RTL_WH_CATG_DESC_TXT_B1*/
	public final EZDCStringItem              rtlWhCatgDescTxt_B1;

    /** INVTY_LOC_CD_B1*/
	public final EZDCStringItem              invtyLocCd_B1;

    /** RTL_WH_CD_B1*/
	public final EZDCStringItem              rtlWhCd_B1;

    /** RTL_WH_NM_B1*/
	public final EZDCStringItem              rtlWhNm_B1;

    /** RTL_SWH_CD_B1*/
	public final EZDCStringItem              rtlSwhCd_B1;

    /** RTL_SWH_NM_B1*/
	public final EZDCStringItem              rtlSwhNm_B1;

    /** MDSE_CD_B1*/
	public final EZDCStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDCStringItem              mdseDescShortTxt_B1;

    /** MDSE_TP_CD_B1*/
	public final EZDCStringItem              mdseTpCd_B1;

    /** COA_MDSE_TP_CD_B1*/
	public final EZDCStringItem              coaMdseTpCd_B1;

    /** COA_PROD_CD_B1*/
	public final EZDCStringItem              coaProdCd_B1;

    /** ROP_QTY_B1*/
	public final EZDCBigDecimalItem              ropQty_B1;

    /** MAX_INVTY_QTY_B1*/
	public final EZDCBigDecimalItem              maxInvtyQty_B1;

    /** OVRD_MAX_INVTY_QTY_B1*/
	public final EZDCBigDecimalItem              ovrdMaxInvtyQty_B1;

    /** MRP_ENBL_FLG_B1*/
	public final EZDCStringItem              mrpEnblFlg_B1;

    /** CALC_ORD_PROC_CD_B1*/
	public final EZDCStringItem              calcOrdProcCd_B1;

    /** PROCR_TP_CD_BS*/
	public final EZDCStringItem              procrTpCd_BS;

    /** PROCR_TP_DESC_TXT_B1*/
	public final EZDCStringItem              procrTpDescTxt_B1;

    /** SRC_LOC_CD_B1*/
	public final EZDCStringItem              srcLocCd_B1;

    /** SRC_RTL_WH_CD_B1*/
	public final EZDCStringItem              srcRtlWhCd_B1;

    /** RTL_WH_NM_B2*/
	public final EZDCStringItem              rtlWhNm_B2;

    /** SRC_RTL_SWH_CD_B1*/
	public final EZDCStringItem              srcRtlSwhCd_B1;

    /** RTL_SWH_NM_B2*/
	public final EZDCStringItem              rtlSwhNm_B2;

    /** RPLSH_DLY_FLG_B1*/
	public final EZDCStringItem              rplshDlyFlg_B1;

    /** RPLSH_MON_FLG_B1*/
	public final EZDCStringItem              rplshMonFlg_B1;

    /** RPLSH_TUE_FLG_B1*/
	public final EZDCStringItem              rplshTueFlg_B1;

    /** RPLSH_WED_FLG_B1*/
	public final EZDCStringItem              rplshWedFlg_B1;

    /** RPLSH_THU_FLG_B1*/
	public final EZDCStringItem              rplshThuFlg_B1;

    /** RPLSH_FRI_FLG_B1*/
	public final EZDCStringItem              rplshFriFlg_B1;

    /** SUPD_FLG_B1*/
	public final EZDCStringItem              supdFlg_B1;

    /** MRP_INFO_CMNT_TXT_B1*/
	public final EZDCStringItem              mrpInfoCmntTxt_B1;

    /** MRP_INFO_RGTN_STS_CD_B1*/
	public final EZDCStringItem              mrpInfoRgtnStsCd_B1;

    /** XX_RSLT_FLG_B1*/
	public final EZDCStringItem              xxRsltFlg_B1;


	/**
	 * NPAL1070_BCMsg is constructor.
	 * The initialization when the instance of NPAL1070_BCMsg is generated.
	 */
	public NPAL1070_BCMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1070_BCMsg is constructor.
	 * The initialization when the instance of NPAL1070_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1070_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		glblCmpyCd_B1 = (EZDCStringItem)newItem("glblCmpyCd_B1");
		mrpInfoPk_B1 = (EZDCBigDecimalItem)newItem("mrpInfoPk_B1");
		xxRqstTs_B1 = (EZDCStringItem)newItem("xxRqstTs_B1");
		xxRqstTz_B1 = (EZDCStringItem)newItem("xxRqstTz_B1");
		mrpPlnNm_B1 = (EZDCStringItem)newItem("mrpPlnNm_B1");
		rtlWhCatgCd_B1 = (EZDCStringItem)newItem("rtlWhCatgCd_B1");
		rtlWhCatgDescTxt_B1 = (EZDCStringItem)newItem("rtlWhCatgDescTxt_B1");
		invtyLocCd_B1 = (EZDCStringItem)newItem("invtyLocCd_B1");
		rtlWhCd_B1 = (EZDCStringItem)newItem("rtlWhCd_B1");
		rtlWhNm_B1 = (EZDCStringItem)newItem("rtlWhNm_B1");
		rtlSwhCd_B1 = (EZDCStringItem)newItem("rtlSwhCd_B1");
		rtlSwhNm_B1 = (EZDCStringItem)newItem("rtlSwhNm_B1");
		mdseCd_B1 = (EZDCStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDCStringItem)newItem("mdseDescShortTxt_B1");
		mdseTpCd_B1 = (EZDCStringItem)newItem("mdseTpCd_B1");
		coaMdseTpCd_B1 = (EZDCStringItem)newItem("coaMdseTpCd_B1");
		coaProdCd_B1 = (EZDCStringItem)newItem("coaProdCd_B1");
		ropQty_B1 = (EZDCBigDecimalItem)newItem("ropQty_B1");
		maxInvtyQty_B1 = (EZDCBigDecimalItem)newItem("maxInvtyQty_B1");
		ovrdMaxInvtyQty_B1 = (EZDCBigDecimalItem)newItem("ovrdMaxInvtyQty_B1");
		mrpEnblFlg_B1 = (EZDCStringItem)newItem("mrpEnblFlg_B1");
		calcOrdProcCd_B1 = (EZDCStringItem)newItem("calcOrdProcCd_B1");
		procrTpCd_BS = (EZDCStringItem)newItem("procrTpCd_BS");
		procrTpDescTxt_B1 = (EZDCStringItem)newItem("procrTpDescTxt_B1");
		srcLocCd_B1 = (EZDCStringItem)newItem("srcLocCd_B1");
		srcRtlWhCd_B1 = (EZDCStringItem)newItem("srcRtlWhCd_B1");
		rtlWhNm_B2 = (EZDCStringItem)newItem("rtlWhNm_B2");
		srcRtlSwhCd_B1 = (EZDCStringItem)newItem("srcRtlSwhCd_B1");
		rtlSwhNm_B2 = (EZDCStringItem)newItem("rtlSwhNm_B2");
		rplshDlyFlg_B1 = (EZDCStringItem)newItem("rplshDlyFlg_B1");
		rplshMonFlg_B1 = (EZDCStringItem)newItem("rplshMonFlg_B1");
		rplshTueFlg_B1 = (EZDCStringItem)newItem("rplshTueFlg_B1");
		rplshWedFlg_B1 = (EZDCStringItem)newItem("rplshWedFlg_B1");
		rplshThuFlg_B1 = (EZDCStringItem)newItem("rplshThuFlg_B1");
		rplshFriFlg_B1 = (EZDCStringItem)newItem("rplshFriFlg_B1");
		supdFlg_B1 = (EZDCStringItem)newItem("supdFlg_B1");
		mrpInfoCmntTxt_B1 = (EZDCStringItem)newItem("mrpInfoCmntTxt_B1");
		mrpInfoRgtnStsCd_B1 = (EZDCStringItem)newItem("mrpInfoRgtnStsCd_B1");
		xxRsltFlg_B1 = (EZDCStringItem)newItem("xxRsltFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1070_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1070_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_B1", "glblCmpyCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"mrpInfoPk_B1", "mrpInfoPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRqstTs_B1", "xxRqstTs_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRqstTz_B1", "xxRqstTz_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"mrpPlnNm_B1", "mrpPlnNm_B1", "B1", null, TYPE_HANKAKUEISU, "70", null},
	{"rtlWhCatgCd_B1", "rtlWhCatgCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgDescTxt_B1", "rtlWhCatgDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"invtyLocCd_B1", "invtyLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_B1", "rtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B1", "rtlSwhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseTpCd_B1", "mdseTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"coaMdseTpCd_B1", "coaMdseTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd_B1", "coaProdCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"ropQty_B1", "ropQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"maxInvtyQty_B1", "maxInvtyQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ovrdMaxInvtyQty_B1", "ovrdMaxInvtyQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mrpEnblFlg_B1", "mrpEnblFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"calcOrdProcCd_B1", "calcOrdProcCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"procrTpCd_BS", "procrTpCd_BS", "BS", null, TYPE_HANKAKUEISU, "2", null},
	{"procrTpDescTxt_B1", "procrTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"srcLocCd_B1", "srcLocCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlWhCd_B1", "srcRtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B2", "rtlWhNm_B2", "B2", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd_B1", "srcRtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B2", "rtlSwhNm_B2", "B2", null, TYPE_HANKAKUEISU, "30", null},
	{"rplshDlyFlg_B1", "rplshDlyFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshMonFlg_B1", "rplshMonFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshTueFlg_B1", "rplshTueFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshWedFlg_B1", "rplshWedFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshThuFlg_B1", "rplshThuFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshFriFlg_B1", "rplshFriFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"supdFlg_B1", "supdFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"mrpInfoCmntTxt_B1", "mrpInfoCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "120", null},
	{"mrpInfoRgtnStsCd_B1", "mrpInfoRgtnStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRsltFlg_B1", "xxRsltFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_B1
        {"MRP_INFO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoPk_B1
        {"XX_RQST_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTs_B1
        {"XX_RQST_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTz_B1
        {"MRP_PLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm_B1
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_B1
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_B1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseTpCd_B1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_B1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_B1
        {"ROP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ropQty_B1
        {"MAX_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxInvtyQty_B1
        {"OVRD_MAX_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdMaxInvtyQty_B1
        {"MRP_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg_B1
        {"CALC_ORD_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd_B1
        {"PROCR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_BS
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_B1
        {"SRC_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcLocCd_B1
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B2
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_B1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B2
        {"RPLSH_DLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshDlyFlg_B1
        {"RPLSH_MON_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshMonFlg_B1
        {"RPLSH_TUE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshTueFlg_B1
        {"RPLSH_WED_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshWedFlg_B1
        {"RPLSH_THU_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshThuFlg_B1
        {"RPLSH_FRI_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshFriFlg_B1
        {"SUPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFlg_B1
        {"MRP_INFO_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoCmntTxt_B1
        {"MRP_INFO_RGTN_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoRgtnStsCd_B1
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_B1
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

