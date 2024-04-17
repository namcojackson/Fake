//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220804152301000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0440_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0440 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0440_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TERM_COND_CATG_PK_A*/
	public final EZDSBigDecimalItem              svcTermCondCatgPk_A;

    /** SVC_TERM_COND_CATG_DESC_TXT_A*/
	public final EZDSStringItem              svcTermCondCatgDescTxt_A;

    /** SVC_TERM_ATTRB_DESC_TXT_A*/
	public final EZDSStringItem              svcTermAttrbDescTxt_A;

    /** SVC_TERM_COND_ATTRB_PK_A*/
	public final EZDSBigDecimalItem              svcTermCondAttrbPk_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_A*/
	public final EZDSStringItem              svcTermAttrbMapValCd_A;

    /** SVC_TERM_COND_CATG_SORT_NUM_A*/
	public final EZDSBigDecimalItem              svcTermCondCatgSortNum_A;

    /** SVC_TERM_ATTRB_SORT_NUM_A*/
	public final EZDSBigDecimalItem              svcTermAttrbSortNum_A;

    /** DS_CONTR_DTL_PK_A*/
	public final EZDSBigDecimalItem              dsContrDtlPk_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** SVC_TERM_COND_DATA_SRC_CD_A*/
	public final EZDSStringItem              svcTermCondDataSrcCd_A;

    /** XX_TRX_DT_A*/
	public final EZDSDateItem              xxTrxDt_A;

    /** COND_VAL_NUM_A*/
	public final EZDSBigDecimalItem              condValNum_A;

    /** SVC_TERM_DATA_TP_CD_A*/
	public final EZDSStringItem              svcTermDataTpCd_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_M*/
	public final EZDSStringItem              svcTermAttrbMapValCd_M;

    /** SVC_TERM_DATA_TP_CD_M*/
	public final EZDSStringItem              svcTermDataTpCd_M;

    /** XX_TRX_DT_M*/
	public final EZDSDateItem              xxTrxDt_M;

    /** COND_VAL_NUM_M*/
	public final EZDSBigDecimalItem              condValNum_M;

    /** SVC_TERM_COND_DATA_VAL_CD_PS*/
	public final EZDSStringItem              svcTermCondDataValCd_PS;

    /** SVC_TERM_COND_DATA_VAL_CD_MS*/
	public final EZDSStringItem              svcTermCondDataValCd_MS;

    /** XX_ROW_NUM_A*/
	public final EZDSBigDecimalItem              xxRowNum_A;

    /** XX_LIST_NUM_A*/
	public final EZDSBigDecimalItem              xxListNum_A;

    /** XX_BTN_FLG_A*/
	public final EZDSStringItem              xxBtnFlg_A;

    /** XX_DPLY_CTRL_FLG_A*/
	public final EZDSStringItem              xxDplyCtrlFlg_A;

    /** SVC_TERM_COND_PK*/
	public final EZDSBigDecimalItem              svcTermCondPk;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;

    /** DS_CONTR_CTRL_STS_CD_A*/
	public final EZDSStringItem              dsContrCtrlStsCd_A;

    /** DS_CONTR_STS_CD_A*/
	public final EZDSStringItem              dsContrStsCd_A;

    /** DS_CONTR_DTL_STS_CD_A*/
	public final EZDSStringItem              dsContrDtlStsCd_A;

    /** ATTRB_UPD_AVAL_FLG_A*/
	public final EZDSStringItem              attrbUpdAvalFlg_A;

    /** SVC_PGM_MDSE_CD_A*/
	public final EZDSStringItem              svcPgmMdseCd_A;

    /** PHYS_MAINT_TRGT_TBL_NM_A*/
	public final EZDSStringItem              physMaintTrgtTblNm_A;

    /** LOGIC_MAINT_TRGT_TBL_NM_A*/
	public final EZDSStringItem              logicMaintTrgtTblNm_A;

    /** PHYS_MAINT_TRGT_COL_NM_A*/
	public final EZDSStringItem              physMaintTrgtColNm_A;

    /** LOGIC_MAINT_TRGT_COL_NM_A*/
	public final EZDSStringItem              logicMaintTrgtColNm_A;

    /** PHYS_DPLY_TRGT_COL_NM_A*/
	public final EZDSStringItem              physDplyTrgtColNm_A;

    /** LOGIC_DPLY_TRGT_COL_NM_A*/
	public final EZDSStringItem              logicDplyTrgtColNm_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0440_BSMsg is constructor.
	 * The initialization when the instance of NSAL0440_BSMsg is generated.
	 */
	public NSAL0440_BSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0440_BSMsg is constructor.
	 * The initialization when the instance of NSAL0440_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0440_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTermCondCatgPk_A = (EZDSBigDecimalItem)newItem("svcTermCondCatgPk_A");
		svcTermCondCatgDescTxt_A = (EZDSStringItem)newItem("svcTermCondCatgDescTxt_A");
		svcTermAttrbDescTxt_A = (EZDSStringItem)newItem("svcTermAttrbDescTxt_A");
		svcTermCondAttrbPk_A = (EZDSBigDecimalItem)newItem("svcTermCondAttrbPk_A");
		svcTermAttrbMapValCd_A = (EZDSStringItem)newItem("svcTermAttrbMapValCd_A");
		svcTermCondCatgSortNum_A = (EZDSBigDecimalItem)newItem("svcTermCondCatgSortNum_A");
		svcTermAttrbSortNum_A = (EZDSBigDecimalItem)newItem("svcTermAttrbSortNum_A");
		dsContrDtlPk_A = (EZDSBigDecimalItem)newItem("dsContrDtlPk_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		svcTermCondDataSrcCd_A = (EZDSStringItem)newItem("svcTermCondDataSrcCd_A");
		xxTrxDt_A = (EZDSDateItem)newItem("xxTrxDt_A");
		condValNum_A = (EZDSBigDecimalItem)newItem("condValNum_A");
		svcTermDataTpCd_A = (EZDSStringItem)newItem("svcTermDataTpCd_A");
		svcTermAttrbMapValCd_M = (EZDSStringItem)newItem("svcTermAttrbMapValCd_M");
		svcTermDataTpCd_M = (EZDSStringItem)newItem("svcTermDataTpCd_M");
		xxTrxDt_M = (EZDSDateItem)newItem("xxTrxDt_M");
		condValNum_M = (EZDSBigDecimalItem)newItem("condValNum_M");
		svcTermCondDataValCd_PS = (EZDSStringItem)newItem("svcTermCondDataValCd_PS");
		svcTermCondDataValCd_MS = (EZDSStringItem)newItem("svcTermCondDataValCd_MS");
		xxRowNum_A = (EZDSBigDecimalItem)newItem("xxRowNum_A");
		xxListNum_A = (EZDSBigDecimalItem)newItem("xxListNum_A");
		xxBtnFlg_A = (EZDSStringItem)newItem("xxBtnFlg_A");
		xxDplyCtrlFlg_A = (EZDSStringItem)newItem("xxDplyCtrlFlg_A");
		svcTermCondPk = (EZDSBigDecimalItem)newItem("svcTermCondPk");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
		dsContrCtrlStsCd_A = (EZDSStringItem)newItem("dsContrCtrlStsCd_A");
		dsContrStsCd_A = (EZDSStringItem)newItem("dsContrStsCd_A");
		dsContrDtlStsCd_A = (EZDSStringItem)newItem("dsContrDtlStsCd_A");
		attrbUpdAvalFlg_A = (EZDSStringItem)newItem("attrbUpdAvalFlg_A");
		svcPgmMdseCd_A = (EZDSStringItem)newItem("svcPgmMdseCd_A");
		physMaintTrgtTblNm_A = (EZDSStringItem)newItem("physMaintTrgtTblNm_A");
		logicMaintTrgtTblNm_A = (EZDSStringItem)newItem("logicMaintTrgtTblNm_A");
		physMaintTrgtColNm_A = (EZDSStringItem)newItem("physMaintTrgtColNm_A");
		logicMaintTrgtColNm_A = (EZDSStringItem)newItem("logicMaintTrgtColNm_A");
		physDplyTrgtColNm_A = (EZDSStringItem)newItem("physDplyTrgtColNm_A");
		logicDplyTrgtColNm_A = (EZDSStringItem)newItem("logicDplyTrgtColNm_A");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0440_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0440_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTermCondCatgPk_A", "svcTermCondCatgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTermCondCatgDescTxt_A", "svcTermCondCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermAttrbDescTxt_A", "svcTermAttrbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondAttrbPk_A", "svcTermCondAttrbPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTermAttrbMapValCd_A", "svcTermAttrbMapValCd_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondCatgSortNum_A", "svcTermCondCatgSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcTermAttrbSortNum_A", "svcTermAttrbSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsContrDtlPk_A", "dsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcTermCondDataSrcCd_A", "svcTermCondDataSrcCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTrxDt_A", "xxTrxDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"condValNum_A", "condValNum_A", "A", null, TYPE_SEISU_SYOSU, "28", "8"},
	{"svcTermDataTpCd_A", "svcTermDataTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"svcTermAttrbMapValCd_M", "svcTermAttrbMapValCd_M", "M", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermDataTpCd_M", "svcTermDataTpCd_M", "M", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTrxDt_M", "xxTrxDt_M", "M", null, TYPE_NENTSUKIHI, "8", null},
	{"condValNum_M", "condValNum_M", "M", null, TYPE_SEISU_SYOSU, "28", "8"},
	{"svcTermCondDataValCd_PS", "svcTermCondDataValCd_PS", "PS", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataValCd_MS", "svcTermCondDataValCd_MS", "MS", null, TYPE_HANKAKUEISU, "50", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxListNum_A", "xxListNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxBtnFlg_A", "xxBtnFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A", "xxDplyCtrlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"svcTermCondPk", "svcTermCondPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrCtrlStsCd_A", "dsContrCtrlStsCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrStsCd_A", "dsContrStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrDtlStsCd_A", "dsContrDtlStsCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"attrbUpdAvalFlg_A", "attrbUpdAvalFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"svcPgmMdseCd_A", "svcPgmMdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"physMaintTrgtTblNm_A", "physMaintTrgtTblNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicMaintTrgtTblNm_A", "logicMaintTrgtTblNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"physMaintTrgtColNm_A", "physMaintTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicMaintTrgtColNm_A", "logicMaintTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"physDplyTrgtColNm_A", "physDplyTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicDplyTrgtColNm_A", "logicDplyTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TERM_COND_CATG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgPk_A
        {"SVC_TERM_COND_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgDescTxt_A
        {"SVC_TERM_ATTRB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbDescTxt_A
        {"SVC_TERM_COND_ATTRB_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondAttrbPk_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_A
        {"SVC_TERM_COND_CATG_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgSortNum_A
        {"SVC_TERM_ATTRB_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbSortNum_A
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_TERM_COND_DATA_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataSrcCd_A
        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_A
        {"COND_VAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_A
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_M
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_M
        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_M
        {"COND_VAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_M
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_PS
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_MS
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_LIST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum_A
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A
        {"SVC_TERM_COND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondPk
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_A
        {"DS_CONTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_A
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd_A
        {"ATTRB_UPD_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attrbUpdAvalFlg_A
        {"SVC_PGM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd_A
        {"PHYS_MAINT_TRGT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtTblNm_A
        {"LOGIC_MAINT_TRGT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtTblNm_A
        {"PHYS_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtColNm_A
        {"LOGIC_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtColNm_A
        {"PHYS_DPLY_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physDplyTrgtColNm_A
        {"LOGIC_DPLY_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicDplyTrgtColNm_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

