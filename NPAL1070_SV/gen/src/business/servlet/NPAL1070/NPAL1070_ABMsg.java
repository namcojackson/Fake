//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230419132753000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1070_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1070 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1070_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** GLBL_CMPY_CD_A1*/
	public final EZDBStringItem              glblCmpyCd_A1;

    /** MRP_INFO_PK_A1*/
	public final EZDBBigDecimalItem              mrpInfoPk_A1;

    /** XX_RQST_TS_A1*/
	public final EZDBStringItem              xxRqstTs_A1;

    /** XX_RQST_TZ_A1*/
	public final EZDBStringItem              xxRqstTz_A1;

    /** MRP_PLN_NM_A1*/
	public final EZDBStringItem              mrpPlnNm_A1;

    /** RTL_WH_CATG_CD_A1*/
	public final EZDBStringItem              rtlWhCatgCd_A1;

    /** RTL_WH_CATG_DESC_TXT_A1*/
	public final EZDBStringItem              rtlWhCatgDescTxt_A1;

    /** INVTY_LOC_CD_A1*/
	public final EZDBStringItem              invtyLocCd_A1;

    /** RTL_WH_CD_A1*/
	public final EZDBStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDBStringItem              rtlWhNm_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDBStringItem              rtlSwhCd_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDBStringItem              rtlSwhNm_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** MDSE_TP_CD_A1*/
	public final EZDBStringItem              mdseTpCd_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDBStringItem              coaMdseTpCd_A1;

    /** COA_PROD_CD_A1*/
	public final EZDBStringItem              coaProdCd_A1;

    /** ROP_QTY_A1*/
	public final EZDBBigDecimalItem              ropQty_A1;

    /** MAX_INVTY_QTY_A1*/
	public final EZDBBigDecimalItem              maxInvtyQty_A1;

    /** OVRD_MAX_INVTY_QTY_A1*/
	public final EZDBBigDecimalItem              ovrdMaxInvtyQty_A1;

    /** MRP_ENBL_FLG_A1*/
	public final EZDBStringItem              mrpEnblFlg_A1;

    /** CALC_ORD_PROC_CD_A1*/
	public final EZDBStringItem              calcOrdProcCd_A1;

    /** PROCR_TP_CD_AS*/
	public final EZDBStringItem              procrTpCd_AS;

    /** PROCR_TP_DESC_TXT_A1*/
	public final EZDBStringItem              procrTpDescTxt_A1;

    /** SRC_LOC_CD_A1*/
	public final EZDBStringItem              srcLocCd_A1;

    /** SRC_RTL_WH_CD_A1*/
	public final EZDBStringItem              srcRtlWhCd_A1;

    /** RTL_WH_NM_A2*/
	public final EZDBStringItem              rtlWhNm_A2;

    /** SRC_RTL_SWH_CD_A1*/
	public final EZDBStringItem              srcRtlSwhCd_A1;

    /** RTL_SWH_NM_A2*/
	public final EZDBStringItem              rtlSwhNm_A2;

    /** RPLSH_DLY_FLG_A1*/
	public final EZDBStringItem              rplshDlyFlg_A1;

    /** RPLSH_MON_FLG_A1*/
	public final EZDBStringItem              rplshMonFlg_A1;

    /** RPLSH_TUE_FLG_A1*/
	public final EZDBStringItem              rplshTueFlg_A1;

    /** RPLSH_WED_FLG_A1*/
	public final EZDBStringItem              rplshWedFlg_A1;

    /** RPLSH_THU_FLG_A1*/
	public final EZDBStringItem              rplshThuFlg_A1;

    /** RPLSH_FRI_FLG_A1*/
	public final EZDBStringItem              rplshFriFlg_A1;

    /** SUPD_FLG_A1*/
	public final EZDBStringItem              supdFlg_A1;

    /** XX_SEL_FLG_A1*/
	public final EZDBStringItem              xxSelFlg_A1;

    /** MRP_INFO_CMNT_TXT_A1*/
	public final EZDBStringItem              mrpInfoCmntTxt_A1;

    /** MRP_INFO_RGTN_STS_CD_A1*/
	public final EZDBStringItem              mrpInfoRgtnStsCd_A1;

    /** XX_RSLT_FLG_A1*/
	public final EZDBStringItem              xxRsltFlg_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDBStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDBStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDBStringItem              xxRecHistTblNm_A1;


	/**
	 * NPAL1070_ABMsg is constructor.
	 * The initialization when the instance of NPAL1070_ABMsg is generated.
	 */
	public NPAL1070_ABMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1070_ABMsg is constructor.
	 * The initialization when the instance of NPAL1070_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1070_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		glblCmpyCd_A1 = (EZDBStringItem)newItem("glblCmpyCd_A1");
		mrpInfoPk_A1 = (EZDBBigDecimalItem)newItem("mrpInfoPk_A1");
		xxRqstTs_A1 = (EZDBStringItem)newItem("xxRqstTs_A1");
		xxRqstTz_A1 = (EZDBStringItem)newItem("xxRqstTz_A1");
		mrpPlnNm_A1 = (EZDBStringItem)newItem("mrpPlnNm_A1");
		rtlWhCatgCd_A1 = (EZDBStringItem)newItem("rtlWhCatgCd_A1");
		rtlWhCatgDescTxt_A1 = (EZDBStringItem)newItem("rtlWhCatgDescTxt_A1");
		invtyLocCd_A1 = (EZDBStringItem)newItem("invtyLocCd_A1");
		rtlWhCd_A1 = (EZDBStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDBStringItem)newItem("rtlWhNm_A1");
		rtlSwhCd_A1 = (EZDBStringItem)newItem("rtlSwhCd_A1");
		rtlSwhNm_A1 = (EZDBStringItem)newItem("rtlSwhNm_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		mdseTpCd_A1 = (EZDBStringItem)newItem("mdseTpCd_A1");
		coaMdseTpCd_A1 = (EZDBStringItem)newItem("coaMdseTpCd_A1");
		coaProdCd_A1 = (EZDBStringItem)newItem("coaProdCd_A1");
		ropQty_A1 = (EZDBBigDecimalItem)newItem("ropQty_A1");
		maxInvtyQty_A1 = (EZDBBigDecimalItem)newItem("maxInvtyQty_A1");
		ovrdMaxInvtyQty_A1 = (EZDBBigDecimalItem)newItem("ovrdMaxInvtyQty_A1");
		mrpEnblFlg_A1 = (EZDBStringItem)newItem("mrpEnblFlg_A1");
		calcOrdProcCd_A1 = (EZDBStringItem)newItem("calcOrdProcCd_A1");
		procrTpCd_AS = (EZDBStringItem)newItem("procrTpCd_AS");
		procrTpDescTxt_A1 = (EZDBStringItem)newItem("procrTpDescTxt_A1");
		srcLocCd_A1 = (EZDBStringItem)newItem("srcLocCd_A1");
		srcRtlWhCd_A1 = (EZDBStringItem)newItem("srcRtlWhCd_A1");
		rtlWhNm_A2 = (EZDBStringItem)newItem("rtlWhNm_A2");
		srcRtlSwhCd_A1 = (EZDBStringItem)newItem("srcRtlSwhCd_A1");
		rtlSwhNm_A2 = (EZDBStringItem)newItem("rtlSwhNm_A2");
		rplshDlyFlg_A1 = (EZDBStringItem)newItem("rplshDlyFlg_A1");
		rplshMonFlg_A1 = (EZDBStringItem)newItem("rplshMonFlg_A1");
		rplshTueFlg_A1 = (EZDBStringItem)newItem("rplshTueFlg_A1");
		rplshWedFlg_A1 = (EZDBStringItem)newItem("rplshWedFlg_A1");
		rplshThuFlg_A1 = (EZDBStringItem)newItem("rplshThuFlg_A1");
		rplshFriFlg_A1 = (EZDBStringItem)newItem("rplshFriFlg_A1");
		supdFlg_A1 = (EZDBStringItem)newItem("supdFlg_A1");
		xxSelFlg_A1 = (EZDBStringItem)newItem("xxSelFlg_A1");
		mrpInfoCmntTxt_A1 = (EZDBStringItem)newItem("mrpInfoCmntTxt_A1");
		mrpInfoRgtnStsCd_A1 = (EZDBStringItem)newItem("mrpInfoRgtnStsCd_A1");
		xxRsltFlg_A1 = (EZDBStringItem)newItem("xxRsltFlg_A1");
		xxRecHistCratTs_A1 = (EZDBStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDBStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDBStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDBStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1070_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1070_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"mrpInfoPk_A1", "mrpInfoPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRqstTs_A1", "xxRqstTs_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRqstTz_A1", "xxRqstTz_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"mrpPlnNm_A1", "mrpPlnNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"rtlWhCatgCd_A1", "rtlWhCatgCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgDescTxt_A1", "rtlWhCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"invtyLocCd_A1", "invtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseTpCd_A1", "mdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"ropQty_A1", "ropQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"maxInvtyQty_A1", "maxInvtyQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ovrdMaxInvtyQty_A1", "ovrdMaxInvtyQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mrpEnblFlg_A1", "mrpEnblFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"calcOrdProcCd_A1", "calcOrdProcCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"procrTpCd_AS", "procrTpCd_AS", "AS", null, TYPE_HANKAKUEISU, "2", null},
	{"procrTpDescTxt_A1", "procrTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"srcLocCd_A1", "srcLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlWhCd_A1", "srcRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A2", "rtlWhNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"srcRtlSwhCd_A1", "srcRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A2", "rtlSwhNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"rplshDlyFlg_A1", "rplshDlyFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshMonFlg_A1", "rplshMonFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshTueFlg_A1", "rplshTueFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshWedFlg_A1", "rplshWedFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshThuFlg_A1", "rplshThuFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"rplshFriFlg_A1", "rplshFriFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"supdFlg_A1", "supdFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSelFlg_A1", "xxSelFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"mrpInfoCmntTxt_A1", "mrpInfoCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "120", null},
	{"mrpInfoRgtnStsCd_A1", "mrpInfoRgtnStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRsltFlg_A1", "xxRsltFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"MRP_INFO_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoPk_A1
        {"XX_RQST_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTs_A1
        {"XX_RQST_TZ",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTz_A1
        {"MRP_PLN_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm_A1
        {"RTL_WH_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_A1
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt_A1
        {"INVTY_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A1
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseTpCd_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"ROP_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ropQty_A1
        {"MAX_INVTY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//maxInvtyQty_A1
        {"OVRD_MAX_INVTY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//ovrdMaxInvtyQty_A1
        {"MRP_ENBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg_A1
        {"CALC_ORD_PROC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calcOrdProcCd_A1
        {"PROCR_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd_AS
        {"PROCR_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt_A1
        {"SRC_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcLocCd_A1
        {"SRC_RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A2
        {"SRC_RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A2
        {"RPLSH_DLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshDlyFlg_A1
        {"RPLSH_MON_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshMonFlg_A1
        {"RPLSH_TUE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshTueFlg_A1
        {"RPLSH_WED_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshWedFlg_A1
        {"RPLSH_THU_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshThuFlg_A1
        {"RPLSH_FRI_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rplshFriFlg_A1
        {"SUPD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFlg_A1
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_A1
        {"MRP_INFO_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoCmntTxt_A1
        {"MRP_INFO_RGTN_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpInfoRgtnStsCd_A1
        {"XX_RSLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

