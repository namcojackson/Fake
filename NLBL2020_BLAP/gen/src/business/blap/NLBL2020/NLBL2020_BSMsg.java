//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230808180407000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL2020_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL2020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL2020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL2020_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRX_HDR_NUM_B1*/
	public final EZDSStringItem              trxHdrNum_B1;

    /** DPLY_LINE_NUM_B1*/
	public final EZDSStringItem              dplyLineNum_B1;

    /** TRX_LINE_NUM_B1*/
	public final EZDSStringItem              trxLineNum_B1;

    /** TRX_LINE_SUB_NUM_B1*/
	public final EZDSStringItem              trxLineSubNum_B1;

    /** SO_NUM_B1*/
	public final EZDSStringItem              soNum_B1;

    /** SO_SLP_NUM_B1*/
	public final EZDSStringItem              soSlpNum_B1;

    /** RTL_WH_NM_B1*/
	public final EZDSStringItem              rtlWhNm_B1;

    /** SHIP_FROM_RTL_SWH_CD_B1*/
	public final EZDSStringItem              shipFromRtlSwhCd_B1;

    /** PICK_SVC_CONFIG_MSTR_PK_B1*/
	public final EZDSBigDecimalItem              pickSvcConfigMstrPk_B1;

    /** MDSE_CD_B1*/
	public final EZDSStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDSStringItem              mdseDescShortTxt_B1;

    /** SHPG_QTY_B1*/
	public final EZDSBigDecimalItem              shpgQty_B1;

    /** SHIP_QTY_B1*/
	public final EZDSBigDecimalItem              shipQty_B1;

    /** PICK_CONF_QTY_B1*/
	public final EZDSBigDecimalItem              pickConfQty_B1;

    /** PICK_CPLT_QTY_B1*/
	public final EZDSBigDecimalItem              pickCpltQty_B1;

    /** SER_NUM_B1*/
	public final EZDSStringItem              serNum_B1;

    /** SER_NUM_TAKE_FLG_B1*/
	public final EZDSStringItem              serNumTakeFlg_B1;

    /** SO_LINE_OPEN_FLG_B1*/
	public final EZDSStringItem              soLineOpenFlg_B1;

    /** TRX_HDR_NUM_HD*/
	public final EZDSStringItem              trxHdrNum_HD;

    /** DPLY_LINE_NUM_HD*/
	public final EZDSStringItem              dplyLineNum_HD;

    /** SO_NUM_HD*/
	public final EZDSStringItem              soNum_HD;

    /** SO_SLP_NUM_HD*/
	public final EZDSStringItem              soSlpNum_HD;

    /** SHIP_FROM_RTL_WH_CD_HD*/
	public final EZDSStringItem              shipFromRtlWhCd_HD;

    /** INVTY_LOC_CD_HD*/
	public final EZDSStringItem              invtyLocCd_HD;

    /** PICK_CONF_QTY_HD*/
	public final EZDSBigDecimalItem              pickConfQty_HD;

    /** SER_NUM_HD*/
	public final EZDSStringItem              serNum_HD;

    /** TRX_SRC_TP_CD_HD*/
	public final EZDSStringItem              trxSrcTpCd_HD;

    /** _EZUpdateDateTime_H1*/
	public final EZDSStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDSStringItem              ezUpTimeZone_H1;

    /** _EZUpdateDateTime_H2*/
	public final EZDSStringItem              ezUpTime_H2;

    /** _EZUpTimeZone_H2*/
	public final EZDSStringItem              ezUpTimeZone_H2;

    /** SCE_ORD_TP_CD_B1*/
	public final EZDSStringItem              sceOrdTpCd_B1;

    /** MDL_ID_B1*/
	public final EZDSBigDecimalItem              mdlId_B1;

    /** XX_MSG_ID_B1*/
	public final EZDSStringItem              xxMsgId_B1;

    /** INVTY_ACCT_CD_B1*/
	public final EZDSStringItem              invtyAcctCd_B1;

    /** TRX_HDR_NUM_HT*/
	public final EZDSStringItem              trxHdrNum_HT;

    /** TRX_LINE_NUM_HT*/
	public final EZDSStringItem              trxLineNum_HT;

    /** TRX_LINE_SUB_NUM_HT*/
	public final EZDSStringItem              trxLineSubNum_HT;

    /** PICK_CONF_QTY_DB*/
	public final EZDSBigDecimalItem              pickConfQty_DB;

    /** SHPG_BAL_QTY_HD*/
	public final EZDSBigDecimalItem              shpgBalQty_HD;

    /** SER_NUM_BB*/
	public final EZDSStringItem              serNum_BB;

    /** SHIP_FLG_HD*/
	public final EZDSStringItem              shipFlg_HD;

    /** XX_REC_HIST_CRAT_TS_B1*/
	public final EZDSStringItem              xxRecHistCratTs_B1;

    /** XX_REC_HIST_CRAT_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistCratByNm_B1;

    /** XX_REC_HIST_UPD_TS_B1*/
	public final EZDSStringItem              xxRecHistUpdTs_B1;

    /** XX_REC_HIST_UPD_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistUpdByNm_B1;

    /** XX_REC_HIST_TBL_NM_B1*/
	public final EZDSStringItem              xxRecHistTblNm_B1;


	/**
	 * NLBL2020_BSMsg is constructor.
	 * The initialization when the instance of NLBL2020_BSMsg is generated.
	 */
	public NLBL2020_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL2020_BSMsg is constructor.
	 * The initialization when the instance of NLBL2020_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL2020_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trxHdrNum_B1 = (EZDSStringItem)newItem("trxHdrNum_B1");
		dplyLineNum_B1 = (EZDSStringItem)newItem("dplyLineNum_B1");
		trxLineNum_B1 = (EZDSStringItem)newItem("trxLineNum_B1");
		trxLineSubNum_B1 = (EZDSStringItem)newItem("trxLineSubNum_B1");
		soNum_B1 = (EZDSStringItem)newItem("soNum_B1");
		soSlpNum_B1 = (EZDSStringItem)newItem("soSlpNum_B1");
		rtlWhNm_B1 = (EZDSStringItem)newItem("rtlWhNm_B1");
		shipFromRtlSwhCd_B1 = (EZDSStringItem)newItem("shipFromRtlSwhCd_B1");
		pickSvcConfigMstrPk_B1 = (EZDSBigDecimalItem)newItem("pickSvcConfigMstrPk_B1");
		mdseCd_B1 = (EZDSStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDSStringItem)newItem("mdseDescShortTxt_B1");
		shpgQty_B1 = (EZDSBigDecimalItem)newItem("shpgQty_B1");
		shipQty_B1 = (EZDSBigDecimalItem)newItem("shipQty_B1");
		pickConfQty_B1 = (EZDSBigDecimalItem)newItem("pickConfQty_B1");
		pickCpltQty_B1 = (EZDSBigDecimalItem)newItem("pickCpltQty_B1");
		serNum_B1 = (EZDSStringItem)newItem("serNum_B1");
		serNumTakeFlg_B1 = (EZDSStringItem)newItem("serNumTakeFlg_B1");
		soLineOpenFlg_B1 = (EZDSStringItem)newItem("soLineOpenFlg_B1");
		trxHdrNum_HD = (EZDSStringItem)newItem("trxHdrNum_HD");
		dplyLineNum_HD = (EZDSStringItem)newItem("dplyLineNum_HD");
		soNum_HD = (EZDSStringItem)newItem("soNum_HD");
		soSlpNum_HD = (EZDSStringItem)newItem("soSlpNum_HD");
		shipFromRtlWhCd_HD = (EZDSStringItem)newItem("shipFromRtlWhCd_HD");
		invtyLocCd_HD = (EZDSStringItem)newItem("invtyLocCd_HD");
		pickConfQty_HD = (EZDSBigDecimalItem)newItem("pickConfQty_HD");
		serNum_HD = (EZDSStringItem)newItem("serNum_HD");
		trxSrcTpCd_HD = (EZDSStringItem)newItem("trxSrcTpCd_HD");
		ezUpTime_H1 = (EZDSStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDSStringItem)newItem("ezUpTimeZone_H1");
		ezUpTime_H2 = (EZDSStringItem)newItem("ezUpTime_H2");
		ezUpTimeZone_H2 = (EZDSStringItem)newItem("ezUpTimeZone_H2");
		sceOrdTpCd_B1 = (EZDSStringItem)newItem("sceOrdTpCd_B1");
		mdlId_B1 = (EZDSBigDecimalItem)newItem("mdlId_B1");
		xxMsgId_B1 = (EZDSStringItem)newItem("xxMsgId_B1");
		invtyAcctCd_B1 = (EZDSStringItem)newItem("invtyAcctCd_B1");
		trxHdrNum_HT = (EZDSStringItem)newItem("trxHdrNum_HT");
		trxLineNum_HT = (EZDSStringItem)newItem("trxLineNum_HT");
		trxLineSubNum_HT = (EZDSStringItem)newItem("trxLineSubNum_HT");
		pickConfQty_DB = (EZDSBigDecimalItem)newItem("pickConfQty_DB");
		shpgBalQty_HD = (EZDSBigDecimalItem)newItem("shpgBalQty_HD");
		serNum_BB = (EZDSStringItem)newItem("serNum_BB");
		shipFlg_HD = (EZDSStringItem)newItem("shipFlg_HD");
		xxRecHistCratTs_B1 = (EZDSStringItem)newItem("xxRecHistCratTs_B1");
		xxRecHistCratByNm_B1 = (EZDSStringItem)newItem("xxRecHistCratByNm_B1");
		xxRecHistUpdTs_B1 = (EZDSStringItem)newItem("xxRecHistUpdTs_B1");
		xxRecHistUpdByNm_B1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_B1");
		xxRecHistTblNm_B1 = (EZDSStringItem)newItem("xxRecHistTblNm_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL2020_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL2020_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trxHdrNum_B1", "trxHdrNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum_B1", "dplyLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"trxLineNum_B1", "trxLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_B1", "trxLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"soNum_B1", "soNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum_B1", "soSlpNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"shipFromRtlSwhCd_B1", "shipFromRtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"pickSvcConfigMstrPk_B1", "pickSvcConfigMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"shpgQty_B1", "shpgQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty_B1", "shipQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"pickConfQty_B1", "pickConfQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"pickCpltQty_B1", "pickCpltQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNumTakeFlg_B1", "serNumTakeFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"soLineOpenFlg_B1", "soLineOpenFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"trxHdrNum_HD", "trxHdrNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum_HD", "dplyLineNum_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	{"soNum_HD", "soNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum_HD", "soSlpNum_HD", "HD", null, TYPE_HANKAKUEISU, "3", null},
	{"shipFromRtlWhCd_HD", "shipFromRtlWhCd_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocCd_HD", "invtyLocCd_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	{"pickConfQty_HD", "pickConfQty_HD", "HD", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_HD", "serNum_HD", "HD", null, TYPE_HANKAKUEISU, "30", null},
	{"trxSrcTpCd_HD", "trxSrcTpCd_HD", "HD", null, TYPE_HANKAKUEISU, "3", null},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_H2", "ezUpTime_H2", "H2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H2", "ezUpTimeZone_H2", "H2", null, TYPE_HANKAKUEISU, "5", null},
	{"sceOrdTpCd_B1", "sceOrdTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdlId_B1", "mdlId_B1", "B1", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"xxMsgId_B1", "xxMsgId_B1", "B1", null, TYPE_HANKAKUEISU, "9", null},
	{"invtyAcctCd_B1", "invtyAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"trxHdrNum_HT", "trxHdrNum_HT", "HT", null, TYPE_HANKAKUEISU, "8", null},
	{"trxLineNum_HT", "trxLineNum_HT", "HT", null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum_HT", "trxLineSubNum_HT", "HT", null, TYPE_HANKAKUEISU, "3", null},
	{"pickConfQty_DB", "pickConfQty_DB", "DB", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shpgBalQty_HD", "shpgBalQty_HD", "HD", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum_BB", "serNum_BB", "BB", null, TYPE_HANKAKUEISU, "30", null},
	{"shipFlg_HD", "shipFlg_HD", "HD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_B1", "xxRecHistCratTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B1", "xxRecHistCratByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B1", "xxRecHistUpdTs_B1", "B1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B1", "xxRecHistUpdByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B1", "xxRecHistTblNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_B1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B1
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_B1
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_B1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_B1
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"SHIP_FROM_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromRtlSwhCd_B1
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"SHPG_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgQty_B1
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty_B1
        {"PICK_CONF_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickConfQty_B1
        {"PICK_CPLT_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickCpltQty_B1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_B1
        {"SO_LINE_OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soLineOpenFlg_B1
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_HD
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_HD
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_HD
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum_HD
        {"SHIP_FROM_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromRtlWhCd_HD
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_HD
        {"PICK_CONF_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickConfQty_HD
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_HD
        {"TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxSrcTpCd_HD
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H2
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_B1
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_B1
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId_B1
        {"INVTY_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAcctCd_B1
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_HT
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum_HT
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum_HT
        {"PICK_CONF_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickConfQty_DB
        {"SHPG_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgBalQty_HD
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_BB
        {"SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFlg_HD
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B1
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

